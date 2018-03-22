package fi.jyvsectec.crypto.providers;

import java.security.SecureRandomSpi;

public class JSTSecureRandom extends SecureRandomSpi {


    /**
     * No need for seeding, we're using time here
     * @param seed
     */

    @Override
    protected void engineSetSeed(byte[] seed) {

    }

    /**
     * Generate bytes based on time randomness. According to Stephen Hawking it's the next best thing
     * after black holes for randomness.
     * @param bytes The array we're going to fill.
     */


    @Override
    protected void engineNextBytes(byte[] bytes) {

        for (int i=0;i<bytes.length;i++){
            try {
                Thread.sleep((int)(Math.random()*3));
                int z = ((int) (System.currentTimeMillis() & 0xFF))%2; //Guaranteed random by fair coin flip
                bytes[i]= (byte) z;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * No need for seeding, we're using time here
     * @param numBytes
     */


    @Override
    protected byte[] engineGenerateSeed(int numBytes) {
        return new byte[0];
    }


}
