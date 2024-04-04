package com.cpa.ttsms.service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKey;


public class TokenHandler {

    private static final String AES = "AES";
    private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
   

    public static String decrypt(String encryptedToken) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedToken);
            Cipher cipher = Cipher.getInstance(AES);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error decrypting text: " + e.getMessage(), e);
        }
    }
    
    public static String generateRandomKey(int keyLength) {
        byte[] key = new byte[keyLength];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
