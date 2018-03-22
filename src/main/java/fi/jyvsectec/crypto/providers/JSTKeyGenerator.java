package fi.jyvsectec.crypto.providers;

import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class JSTKeyGenerator extends KeyGeneratorSpi {


    @Override
    protected void engineInit(SecureRandom secureRandom) {

    }

    @Override
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {

    }

    @Override
    protected void engineInit(int i, SecureRandom secureRandom) {

    }

    @Override
    protected SecretKey engineGenerateKey() {
        return null;
    }
}
