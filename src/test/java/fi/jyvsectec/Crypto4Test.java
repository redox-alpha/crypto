package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto4;
import fi.jyvsectec.exception.CryptoFailedException;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class Crypto4Test {

    Crypto4 crypto3;
    String  key = "correcthorsebatt";
    String  plaintext = "We attack at dawn!";

    Logger logger = Logger.getLogger("Crypto4Test");

    @Before
    public void initializeCrypto() throws UnsupportedEncodingException {
        crypto3 = new Crypto4();
        crypto3.initializeCrypto(key.getBytes("UTF-8"));
    }

    @Test
    public void testSeed(){
        logger.info(String.format("Seed is: %d",crypto3.getSeed()));
    }


    @Test
    public void encryptionTest() throws UnsupportedEncodingException, CryptoFailedException {
        String rslt = DatatypeConverter.printHexBinary(crypto3.doEncrypt(plaintext.getBytes("UTF-8")));
        assertTrue(rslt.equals("E0D297D6C3C3D6D4DC97D6C397D3D6C0D996"));
    }

    @Test
    public void decryptionTest() throws CryptoFailedException {
        byte[] ciphertext = crypto3.doEncrypt(plaintext.getBytes());
        byte[] plain = crypto3.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(plaintext.getBytes(),plain));
    }

    @Test(expected = CryptoFailedException.class)
    public void nullEncryptionFailsTest() throws CryptoFailedException {
        byte[] ciphertext = crypto3.doEncrypt(plaintext.getBytes());
        byte[]  b = new byte[1];
        b[0]= 0;
        crypto3.doDecrypt(ciphertext,b);
    }

}
