package com.cpa.ttsms.service;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenHandler {

    private static final String AES = "AES";
    private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public static String encrypt(String plainText) throws Exception {
        try {
            String key = generateRandomKey(16); // Use 16 bytes for AES-128

            // Create a secret key object from the provided key
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), AES);

            // Create a cipher and initialize it for encryption
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Encrypt the plaintext
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            // Encode the encrypted bytes to Base64
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error encrypting text: " + e.getMessage(), e);
        }
    }
    
    
    public static String generateRandomKey(int keyLength) {
        byte[] key = new byte[keyLength];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

}