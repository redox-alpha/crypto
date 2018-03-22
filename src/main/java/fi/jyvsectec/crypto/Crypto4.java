package fi.jyvsectec.crypto;

import fi.jyvsectec.exception.CryptoFailedException;
import fi.jyvsectec.interfaces.JSTCrypto;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class Crypto4 implements JSTCrypto {



    private byte[] key = null;
    private int seed = 0;

    public void initializeCrypto(byte[] key) {
        this.key = key;

        ByteBuffer wrapped = ByteBuffer.wrap(key);
        seed = wrapped.getInt()%255;
    }

    public int getSeed(){
         return seed;
    }

    public void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException {
        throw new CryptoFailedException("I don't need IV drugs, mang.");
    }

    public byte[] doEncrypt(byte[] plaintext) throws CryptoFailedException {

        if(key == null){
            throw new CryptoFailedException("No key set.");
        }

        if (seed == 0){
            throw new CryptoFailedException("Seed can't be null; it leaks the first byte. Choose a different password!");
        }

        byte[] ciphertext = new byte[plaintext.length];
        byte xor_key = (byte) seed;


        for(int i = 0;i < plaintext.length;i++){
            ciphertext[i] = (byte) (plaintext[i]^xor_key);
        }


        return ciphertext;
    }

    public byte[] doDecrypt(byte[] ciphertext) throws CryptoFailedException {
        return doEncrypt(ciphertext);
    }

    public byte[] doDecrypt(byte[] ciphertext, byte[] key) throws CryptoFailedException {
        this.key = key;
        ByteBuffer wrapped = ByteBuffer.wrap(key);
        try {
            seed = wrapped.getInt() % 255;
        }catch (BufferUnderflowException e){
            throw new CryptoFailedException("Sorry, your password seems kind of too short.");
        }
        return doEncrypt(ciphertext);
    }
}
