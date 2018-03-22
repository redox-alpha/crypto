package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto3;
import fi.jyvsectec.exception.CryptoFailedException;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class Crypto3Test {
    Crypto3 crypto3;
    String  key = "correcthorsebatt";
    String  plaintext = "We attack at tenWe attack at ten";

    File file;


    @Before
    public void initializeCrypto() throws UnsupportedEncodingException {
        crypto3 = new Crypto3();
        crypto3.initializeCrypto(key.getBytes("UTF-8"));


    }

    @Test
    public void encryptionTest() throws UnsupportedEncodingException, CryptoFailedException {
        String rslt = DatatypeConverter.printHexBinary(crypto3.doEncrypt(plaintext.getBytes("UTF-8")));
        assertNotNull(rslt);
    }

    @Test
    public void decryptionTest() throws CryptoFailedException {
        byte[] ciphertext = crypto3.doEncrypt(plaintext.getBytes());
        byte[] plain = crypto3.doDecrypt(ciphertext);
        assertFalse(Arrays.equals(plaintext.getBytes(),plain));
    }


}
