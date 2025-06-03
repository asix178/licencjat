package com.app.service.utility;

import com.app.model.LotteryTicket;
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
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class PdfGenerator {

    private static final int QR_CODE_SIZE = 150;
    private static final int MARGIN = 10;
    private static final int SPACING = 50;


    public static byte[] generatePdfWithQrCodes(List<LotteryTicket> lotteryTicketList, String outputPath) throws IOException, WriterException {
        PDDocument document = new PDDocument();
        final int UUIDS_PER_PAGE = 4;

        for (int i = 0; i < lotteryTicketList.size(); i += UUIDS_PER_PAGE) {
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight()));
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            List<LotteryTicket> subList = lotteryTicketList.subList(i, Math.min(i + UUIDS_PER_PAGE, lotteryTicketList.size()));

            for (int row = 0; row < subList.size(); row++) {
                LotteryTicket ticket = subList.get(row);
                String uuid = ticket.getId().toString();
                BufferedImage qrImage = generateQrCode(uuid);
                PDImageXObject pdImage = LosslessFactory.createFromImage(document, qrImage);

                for (int col = 0; col < 3; col++) {
                    float x = MARGIN + col * (QR_CODE_SIZE + SPACING);

                    float startY = page.getMediaBox().getHeight() - MARGIN - QR_CODE_SIZE;
                    float y = startY - row * (QR_CODE_SIZE + SPACING);

                    // Rysuj QR
                    contentStream.drawImage(pdImage, x, y, QR_CODE_SIZE, QR_CODE_SIZE);

                    // Podpis 1 — numer losu
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    contentStream.newLineAtOffset(x + 5, y - 14);
                    contentStream.showText("Numer: " + ticket.getNumber());
                    contentStream.endText();

                    // Podpis 2 — kategoria
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(x + 5, y - 28);
                    contentStream.showText("Kategoria: " + ticket.getCategory().getName());
                    contentStream.endText();
                }

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
