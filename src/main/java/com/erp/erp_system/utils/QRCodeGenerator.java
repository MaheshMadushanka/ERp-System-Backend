package com.erp.erp_system.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.nio.file.Path;

public class QRCodeGenerator {

    public static void generateQRCode(String text, String filePath) throws Exception {
        BitMatrix matrix = new MultiFormatWriter()
                .encode(text, BarcodeFormat.QR_CODE, 300, 300);

        Path path = Path.of(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }
}
