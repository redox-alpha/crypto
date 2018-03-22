package fi.jyvsectec.crypto;

import fi.jyvsectec.exception.CryptoFailedException;
import fi.jyvsectec.interfaces.JSTCrypto;

public class Crypto1 implements JSTCrypto{

    private byte[] key = null;

    public void initializeCrypto(byte[] key) {
        this.key = key;
        return;
    }

    public void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException {
        throw new CryptoFailedException("I don't need IV");
    }

    public byte[] doEncrypt(byte[] plaintext) {

        byte[] ciphertext;

        //TODO: Implement the encryption!
        ciphertext = plaintext;

        return ciphertext;
    }

    public byte[] doDecrypt(byte[] ciphertext) {
        return ciphertext;
    }

    public byte[] doDecrypt(byte[] ciphertext, byte[] key) {
        return ciphertext;
    }
}
