package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.utilities.QRCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QRCodeService
{
    /**
     * Genera un QRCode
     * @param qrcode Stringa da codificare
     * @return ResponseEntity<BufferedImage> Immagine del QRCode
     * @throws Exception
     */
    public ResponseEntity<BufferedImage> qrCodeGenerator(String qrcode) throws Exception {
        return ResponseEntity.ok(QRCodeGenerator.generateQRCodeImage(qrcode));
    }

    /**
     * Permette di convertire l'immagine in un formato leggibile
     * @return BufferedImageHttpMessageConverter
     */
    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}