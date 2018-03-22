package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto4;
import fi.jyvsectec.exception.CryptoFailedException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Crypto4Test {

    Crypto4 crypto3;
    String  key = "correcthorsebatt";
    String  plaintext = "We attack at dawn!";

    Logger logger = Logger.getLogger("Crypto4Test");

    File file;

    @Before
    public void initializeCrypto() throws UnsupportedEncodingException {
        crypto3 = new Crypto4();
        crypto3.initializeCrypto(key.getBytes("UTF-8"));
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("test.bmp").getFile());
    }

    @Test
    public void testSeed(){
        assertEquals(crypto3.getSeed(),183);
    }


    @Test
    public void encryptionTest() throws UnsupportedEncodingException, CryptoFailedException {
        String rslt = DatatypeConverter.printHexBinary(crypto3.doEncrypt(plaintext.getBytes("UTF-8")));
        assertTrue(rslt.equals("E0DD99DBCFC8DCDDD4E0A0B6E3A0A4B1A9E9"));
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

    @Test
    public void fileEncryptionTest() throws CryptoFailedException, IOException {
        byte[] array = Files.readAllBytes(file.toPath());
        byte[] ciphertext = crypto3.doEncrypt(array);
        /* dd if=test.bmp of=test_ecrypted.bmp bs=1 count=54 conv=notrunc  to view.*/
        FileUtils.writeByteArrayToFile(new File("test_ecrypted4.bmp"), ciphertext);
        byte[] plain = crypto3.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(array,plain));
    }
}
