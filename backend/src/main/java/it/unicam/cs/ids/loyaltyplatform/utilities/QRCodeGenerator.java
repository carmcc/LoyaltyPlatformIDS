package it.unicam.cs.ids.loyaltyplatform.utilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class QRCodeGenerator {  //TODO discutere se rendere "QRCodeGenerator" astratto
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter toConvertImage = new QRCodeWriter();
        BitMatrix bitMatrix = toConvertImage.encode(barcodeText, BarcodeFormat.QR_CODE, 300, 300);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}