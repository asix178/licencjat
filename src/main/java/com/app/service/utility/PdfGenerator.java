package com.app.service.utility;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class PdfGenerator {

    private static final int QR_CODE_SIZE = 150;
    private static final int QR_PER_PAGE = 12;
    private static final int COLS = 4;
    private static final int MARGIN = 50;
    private static final int SPACING = 20;


    public static byte[] generatePdfWithQrCodes(List<String> uuids, String outputPath) throws IOException, WriterException {
        PDDocument document = new PDDocument();

        for (int i = 0; i < Math.ceil((double) uuids.size() /QR_PER_PAGE)*QR_PER_PAGE; i += QR_PER_PAGE) {
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            List<String> subList = uuids.subList(i, Math.min(i + QR_PER_PAGE, uuids.size()));

            for (int j = 0; j < subList.size(); j++) {
                String uuid = subList.get(j);
                BufferedImage qrImage = generateQrCode(uuid);

                int row = j / COLS;
                int col = j % COLS;

                float x = MARGIN + col * (QR_CODE_SIZE + SPACING);
                float y = page.getMediaBox().getHeight() - MARGIN - (row + 1) * (QR_CODE_SIZE + SPACING);

                PDImageXObject pdImage = LosslessFactory.createFromImage(document, qrImage);
                contentStream.drawImage(pdImage, x, y, QR_CODE_SIZE, QR_CODE_SIZE);
            }

            contentStream.close();
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        document.save(outputPath + ".pdf");
        document.save(outputStream);
        document.close();
        return outputStream.toByteArray();
    }

    private static BufferedImage generateQrCode(String text) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
