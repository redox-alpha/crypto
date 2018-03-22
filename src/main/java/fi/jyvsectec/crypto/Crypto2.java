package fi.jyvsectec.crypto;

import fi.jyvsectec.exception.CryptoFailedException;
import fi.jyvsectec.interfaces.JSTCrypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto2 implements JSTCrypto {




    //Use a testing key for now...
    private String key = "aaaaaaaaaaaaaaaa";


    public void initializeCrypto(byte[] key) {
        return;
    }

    public void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException {
        throw new CryptoFailedException("I don't need IV");
    }

    public byte[] doEncrypt(byte[] plaintext) throws CryptoFailedException {

        SecretKeySpec skey;
        try {
            skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,skey);
            byte[] ciphertext = cipher.doFinal(plaintext);
            return  ciphertext;
        } catch (Exception e) {
            throw  new CryptoFailedException("Error :(");
        }
    }

    public byte[] doDecrypt(byte[] ciphertext) throws CryptoFailedException {

        SecretKeySpec skey;
        try {
            skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,skey);
            byte[] plaintext = cipher.doFinal(ciphertext);
            return  plaintext;
        } catch (Exception e) {
            throw  new CryptoFailedException("Error :(");
        }
    }

    public byte[] doDecrypt(byte[] ciphertext, byte[] key) {
        return new byte[0];
    }
}
