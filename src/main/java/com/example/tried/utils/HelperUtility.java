package com.example.tried.utils;


import com.example.tried.services.AuthApiImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperUtility {

    static Logger log = LogManager.getLogger(HelperUtility.class);

    // To Base64 Function
    public static String toBase64String(String value){
        byte[] data = value.getBytes(StandardCharsets.ISO_8859_1);
        return Base64.encodeBase64String(data);
    }


    // To JSON Function
    public static String toJSON(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }


    // Get Security Credentials
    public static String getSecurityCredentials(String initiatorPassword){
        String encryptedPassword;

        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            byte[] input = initiatorPassword.getBytes();

            Resource resource = new ClassPathResource("ProductionCertificate.cer");
            InputStream inputStream = resource.getInputStream();

            FileInputStream file = new FileInputStream(resource.getFile());
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(file);
            PublicKey publicKey = certificate.getPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] cipherText = cipher.doFinal(input);

            //
            encryptedPassword = Base64.encodeBase64String(cipherText).trim();
            return encryptedPassword;
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException |
                 CertificateException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }

    // Random String Generator
    public static String getTransactionUniqueNumber(){
        RandomStringGenerator stringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        String transactionNumber = stringGenerator.generate(12).toUpperCase();
        log.info(String.format("Generated Transaction Number: %s", transactionNumber));

        return transactionNumber;
    }

    // Get the STK Push Password
    public static String getStkPushPassword(String shortCode,String password,
                                            String timestamp) {
        String concatenated = String.format("%s%s%s",shortCode,password,timestamp);
        return toBase64String(concatenated);
    }

    // Get the Transaction Timestamp
    public static String getTransactionTimestamp() {
        // yyyyMMddHHmmss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
