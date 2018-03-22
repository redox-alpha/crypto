package fi.jyvsectec.crypto;

import fi.jyvsectec.crypto.providers.JSTSecurityProvider;
import fi.jyvsectec.exception.CryptoFailedException;
import fi.jyvsectec.interfaces.JSTCrypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.security.Security;

public class Crypto6 implements JSTCrypto{

    SecureRandom random;
    SecretKey secretKey;
    IvParameterSpec iv;
    Cipher cipher;

    @Override
    public void initializeCrypto(byte[] key) throws CryptoFailedException {

        if(key != null){
            throw new CryptoFailedException("This encryption service generates a strong key using JAVA crypto API.");
        }

        try {
            Security.insertProviderAt(new JSTSecurityProvider(), 0);
            random = SecureRandom.getInstance("JST");
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(random);
            secretKey = keyGen.generateKey();

            byte[] iv_array = new byte[16];
            random.nextBytes(iv_array);
            iv = new IvParameterSpec(iv_array); //we also need random IV

            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        }catch (Exception e){
            throw new CryptoFailedException(String.format("Init failed: %s",e.getMessage()));
        }

    }


    public SecretKey getKey(){
        return secretKey;
    }
    public IvParameterSpec getIV(){
        return iv;
    }


    public String getProviderName(){
        return random.getProvider().getName();
    }

    @Override
    public void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException {
        throw new CryptoFailedException("This encryption service generates a strong IV using JAVA crypto API.");
    }

    @Override
    public byte[] doEncrypt(byte[] plaintext) throws CryptoFailedException {
        try {

            byte[] ciphertext = cipher.doFinal(plaintext);
            byte[] encryptedIVAndText = new byte[iv.getIV().length + ciphertext.length];
            System.arraycopy(iv.getIV(), 0, encryptedIVAndText, 0, iv.getIV().length);
            System.arraycopy(ciphertext, 0, encryptedIVAndText, iv.getIV().length, ciphertext.length);

            return encryptedIVAndText;
        }catch (Exception e){
            throw new CryptoFailedException(String.format("Can't: %s",e.getMessage()));
        }
    }

    @Override
    public byte[] doDecrypt(byte[] ciphertext) {
        return new byte[0];
    }

    @Override
    public byte[] doDecrypt(byte[] ciphertext, byte[] key) {
        return new byte[0];
    }
}
