package com.example.demo.util;

import com.example.demo.common.CommonConstant;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ExtendUtil {

    public static String encryptedFunction(int idPk) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        SecretKey key = CipherUtil.generateKey(128);
        IvParameterSpec ivParameterSpec = CipherUtil.generateIv();
        String algorithm = CommonConstant.ALGO_AES;
        String cipherText = CipherUtil.encrypt(algorithm, String.valueOf(idPk), key, ivParameterSpec);

        return cipherText;
    }

    public static String decryptedHelperFunction(String encryptedId) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey key = CipherUtil.generateKey(128);
        IvParameterSpec ivParameterSpec = CipherUtil.generateIv();
        String algorithm = CommonConstant.ALGO_AES;
        String cipherText = CipherUtil.decrypt(algorithm,encryptedId,key,ivParameterSpec);
        return cipherText;
    }

}
