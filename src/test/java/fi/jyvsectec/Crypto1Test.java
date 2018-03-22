package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto1;
import fi.jyvsectec.exception.CryptoFailedException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Crypto1Test {


    Crypto1 crypto1;
    String  key = "CorrectHorseBatteryStaple"; /* https://xkcd.com/936/ */
    String  plaintext = "We attack at dawn!";


    @Before
    public void initializeCrypto(){
        crypto1 = new Crypto1();
        crypto1.initializeCrypto(key.getBytes());
    }

    @Test
    public void encryptionTest(){
        crypto1.doEncrypt(plaintext.getBytes());
    }

    @Test
    public void decryptionTest(){
        byte[] ciphertext = crypto1.doEncrypt(plaintext.getBytes());
        byte[] plain = crypto1.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(plaintext.getBytes(),plain));
    }

    @Test(expected = CryptoFailedException.class)
    public void testCFException() throws CryptoFailedException {
        Crypto1 crypto1b = new Crypto1();
        crypto1b.initializeCrypto(null,null);

    }

}
