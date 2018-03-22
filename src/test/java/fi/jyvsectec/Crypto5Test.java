package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto5;
import fi.jyvsectec.exception.CryptoFailedException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class Crypto5Test {
    Crypto5 crypto5;
    String  key = "correcthorsebatt";
    String  plaintext = "We attack at dawn!";

    Logger logger = Logger.getLogger("Crypto4Test");

    File file;

    @Before
    public void initializeCrypto() throws UnsupportedEncodingException, CryptoFailedException {
        crypto5 = new Crypto5();
        crypto5.initializeCrypto(key.getBytes("UTF-8"));

        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("test.bmp").getFile());
    }

    @Test
    public void encryptionDecryptionTest() throws CryptoFailedException {
        byte[] ciphertext = crypto5.doEncrypt(plaintext.getBytes());
        byte[] plain = crypto5.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(plaintext.getBytes(),plain));
    }

    @Test
    public void fileEncryptionTest() throws CryptoFailedException, IOException {
        byte[] array = Files.readAllBytes(file.toPath());
        byte[] ciphertext = crypto5.doEncrypt(array);
        /* dd if=test.bmp of=test_ecrypted.bmp bs=1 count=54 conv=notrunc  to view.*/
        FileUtils.writeByteArrayToFile(new File("test_ecrypted5.bmp"), ciphertext);
        byte[] plain = crypto5.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(array,plain));
    }

}
