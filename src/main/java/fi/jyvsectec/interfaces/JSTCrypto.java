package fi.jyvsectec.interfaces;

import fi.jyvsectec.exception.CryptoFailedException;

public interface JSTCrypto {

    void initializeCrypto(byte[] key);

    void initializeCrypto(byte[] key, byte[] iv) throws CryptoFailedException;

    byte[] doEncrypt(byte[] plaintext) throws CryptoFailedException;

    byte[] doDecrypt(byte[] ciphertext) throws CryptoFailedException;

    byte[] doDecrypt(byte[] ciphertext, byte[] key) throws CryptoFailedException;


}
