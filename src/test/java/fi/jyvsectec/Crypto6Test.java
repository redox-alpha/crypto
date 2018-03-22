package fi.jyvsectec;

import fi.jyvsectec.crypto.Crypto6;
import fi.jyvsectec.exception.CryptoFailedException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Crypto6Test {


    Crypto6 crypto6;
    File file;


    @Before
    public void initializeCrypto() throws CryptoFailedException {
        crypto6 = new Crypto6();
        crypto6.initializeCrypto(null);

        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("test.bmp").getFile());
    }

    @Test
    public void JSTProviderEnabledTest() {
        assertEquals(crypto6.getProviderName(),"JST");

    }


    @Test
    public void getKeyTest(){
        assertTrue(crypto6.getKey().getEncoded()!=null);

    }
    @Test
    public void fileEncryptionTest() throws CryptoFailedException, IOException {
        byte[] array = Files.readAllBytes(file.toPath());
        byte[] ciphertext = crypto6.doEncrypt(array);
        /* dd if=test.bmp of=test_ecrypted.bmp bs=1 count=54 conv=notrunc  to view.*/
        FileUtils.writeByteArrayToFile(new File("test_ecrypted6.bmp"), ciphertext);
        byte[] plain = crypto6.doDecrypt(ciphertext);
        assertTrue(Arrays.equals(array,plain));
    }


}
