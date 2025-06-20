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

            // Pionowe przerywane linie po 1 i 2 kolumnie
            contentStream.setStrokingColor(192, 192, 192); // Jasnoszary
            contentStream.setLineWidth(2f);
            contentStream.setLineDashPattern(new float[]{5, 3}, 0); // Dash: 5 units, Gap: 3 units

// Oblicz pozycje X dla linii pionowych
            float x1 = MARGIN + QR_CODE_SIZE + SPACING / 2f;
            float x2 = x1 + QR_CODE_SIZE + SPACING;

            float yTop = page.getMediaBox().getHeight() - MARGIN + 5;
            float yBottom = MARGIN;

// Linia po 1 kolumnie
            contentStream.moveTo(x1, yBottom);
            contentStream.lineTo(x1, yTop);
            contentStream.stroke();

// Linia po 2 kolumnie
            contentStream.moveTo(x2, yBottom);
            contentStream.lineTo(x2, yTop);
            contentStream.stroke();

// Przywróć pełną linię (solid) do dalszego rysowania
            contentStream.setLineDashPattern(new float[]{}, 0);


            List<LotteryTicket> subList = lotteryTicketList.subList(i, Math.min(i + UUIDS_PER_PAGE, lotteryTicketList.size()));

            // ✅ Górna linia nad pierwszym rzędem
            float topY = page.getMediaBox().getHeight() - MARGIN + 2;
            contentStream.setLineWidth(2.0f);
            contentStream.setStrokingColor(64, 64, 64);
            contentStream.moveTo(0, topY);
            contentStream.lineTo(page.getMediaBox().getWidth(), topY);
            contentStream.stroke();

            for (int row = 0; row < subList.size(); row++) {
                LotteryTicket ticket = subList.get(row);
                String uuid = ticket.getId().toString();
                BufferedImage qrImage = generateQrCode(uuid);
                PDImageXObject pdImage = LosslessFactory.createFromImage(document, qrImage);

                for (int col = 0; col < 3; col++) {
                    float x = MARGIN + col * (QR_CODE_SIZE + SPACING);
                    float startY = page.getMediaBox().getHeight() - MARGIN - QR_CODE_SIZE;
                    float y = startY - row * (QR_CODE_SIZE + SPACING);

                    // Draw QR
                    contentStream.drawImage(pdImage, x, y, QR_CODE_SIZE, QR_CODE_SIZE);

                    // Labels
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    contentStream.newLineAtOffset(x + 5, y - 14);
                    contentStream.showText("Numer: " + ticket.getNumber());
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    contentStream.newLineAtOffset(x + 5, y - 28);
                    contentStream.showText("Kategoria: " + ticket.getCategory().getName());
                    contentStream.endText();
                }

                // ✅ Dolna linia pod każdym rzędem
                float yLine = page.getMediaBox().getHeight() - MARGIN - (row + 1) * (QR_CODE_SIZE + SPACING) + 2;
                contentStream.setStrokingColor(80, 80, 80);
                contentStream.setLineWidth(2.0f);
                contentStream.moveTo(0, yLine);
                contentStream.lineTo(page.getMediaBox().getWidth(), yLine);
                contentStream.stroke();
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
