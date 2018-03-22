package fi.jyvsectec.crypto;

import fi.jyvsectec.exception.CryptoFailedException;
import fi.jyvsectec.interfaces.JSTCrypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class Crypto5 implements JSTCrypto {


    Cipher cipher;
    SecretKey skey;

    @Override
    public void initializeCrypto(byte[] key) throws CryptoFailedException {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mdkey = md.digest(key);
            skey = new SecretKeySpec(mdkey, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,skey);

        }catch (Exception e){
            throw new CryptoFailedException(String.format("Could not init the crypto: %s",e.getMessage()));
        }

    }

    @Override
    public void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException {
        throw new CryptoFailedException("IV not needed!");
    }

    @Override
    public byte[] doEncrypt(byte[] plaintext) throws CryptoFailedException {

        byte[] ciphertext;

        try {
            ciphertext = cipher.doFinal(plaintext);

        }catch (Exception e){
            throw new CryptoFailedException("Could not crypt");
        }
        return ciphertext;
    }

    @Override
    public byte[] doDecrypt(byte[] ciphertext) throws CryptoFailedException {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            return cipher.doFinal(ciphertext);

        }catch (Exception e){
            throw new CryptoFailedException(String.format("Can't do nothing: %s",e.getMessage()));
        }
    }

    @Override
    public byte[] doDecrypt(byte[] ciphertext, byte[] key) throws CryptoFailedException {
        throw new CryptoFailedException("Initialize first.");
    }
}
