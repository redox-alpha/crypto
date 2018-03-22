package fi.jyvsectec.interfaces;

import fi.jyvsectec.exception.CryptoFailedException;

public interface JSTCrypto {

    public void initializeCrypto(byte[] key);

    public void initializeCrypto(byte[] key,byte[] iv) throws CryptoFailedException;

    public byte[] doEncrypt(byte[] plaintext);

    public byte[] doDecrypt(byte[] ciphertext);

    public byte[] doDecrypt(byte[] ciphertext,byte[] key);


}
