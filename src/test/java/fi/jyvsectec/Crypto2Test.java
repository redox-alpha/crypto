package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto2;
import fi.jyvsectec.exception.CryptoFailedException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class Crypto2Test {
    Crypto2 crypto2;
    String  key = "CorrectHorseBatteryStaple"; /* https://xkcd.com/936/ */
    String  plaintext = "We attack at dawn!";


    @Before
    public void initializeCrypto(){
        crypto2 = new Crypto2();
        crypto2.initializeCrypto(key.getBytes());
    }

    @Test
    public void encryptionTest() throws CryptoFailedException {
        crypto2.doEncrypt(plaintext.getBytes());
    }

    @Test
    public void decryptionTest() throws CryptoFailedException {
        byte[] ciphertext = crypto2.doEncrypt(plaintext.getBytes());
        byte[] plain = crypto2.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(plaintext.getBytes(),plain));
    }

}
