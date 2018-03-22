package fi.jyvsectec.crypto;

import fi.jyvsectec.exception.CryptoFailedException;
import fi.jyvsectec.interfaces.JSTCrypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto3 implements JSTCrypto {


    private String iv = "012345678abcdefghjklm";
    private byte[] key;

    public void initializeCrypto(byte[] key) {
        this.key = key;
    }

    public void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException {
        throw new CryptoFailedException("I don't need IV");
    }

    public byte[] doEncrypt(byte[] plaintext) throws CryptoFailedException {
        byte[] ciphertext = new byte[0];

        String ivp = iv + plaintext;

        try {
            SecretKeySpec skey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE,skey);
            ciphertext = cipher.doFinal(ivp.getBytes("UTF-8"));
        } catch (Exception e) {
            throw  new CryptoFailedException("Error :(");
        }

        return ciphertext;
    }

    public byte[] doDecrypt(byte[] ciphertext) throws CryptoFailedException {

        //TODO: Decryption fails for some reason, maybe stackoverflow will help...

        byte[] plaintext = new byte[0];

        try {
            SecretKeySpec skey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE,skey);
            plaintext = cipher.doFinal(ciphertext);
        } catch (Exception e) {
            throw  new CryptoFailedException("Error :(");
        }
        return plaintext;
    }

    public byte[] doDecrypt(byte[] ciphertext, byte[] key) throws CryptoFailedException {
        this.key = key;
        return doDecrypt(ciphertext);
    }
}
