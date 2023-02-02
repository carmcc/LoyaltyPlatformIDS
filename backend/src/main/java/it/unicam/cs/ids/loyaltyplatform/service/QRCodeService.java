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
    public ResponseEntity<BufferedImage> qrCodeGenerator(String qrcode) throws Exception {
        return ResponseEntity.ok(QRCodeGenerator.generateQRCodeImage(qrcode));
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}