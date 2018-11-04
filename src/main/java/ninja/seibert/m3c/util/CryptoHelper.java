package ninja.seibert.m3c.util;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.stream.Stream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptoHelper {

    /**
     * ISO_8859_1.
     */
    public static final Charset charSet = Charset.forName("ISO_8859_1");

    /**
     * Generate a new shared secret AES key from a secure random source.
     */
    public static SecretKey createNewSharedKey() {
        CipherKeyGenerator gen = new CipherKeyGenerator();
        gen.init(new KeyGenerationParameters(new SecureRandom(), 128));
        return new SecretKeySpec(gen.generateKey(), "AES");
    }

    public static KeyPair createNewKeyPair() {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024);
            return gen.generateKeyPair();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            System.err.println("Key pair generation failed!");
            return null;
        }
    }

    /**
     * Compute a serverId hash for use by sendSessionRequest().
     */
    public static String getServerIdHash(String serverId, PublicKey pubKey, SecretKey secretKey) {
        try {
            byte[] hash = digestOperation("SHA-1", serverId.getBytes("ISO_8859_1"),
                    secretKey.getEncoded(), pubKey.getEncoded());
            boolean negative = (hash[0] & 0x80) == 0x80;
            if (negative) {
                hash = twosCompliment(hash);
            }
            String result = getHexString(hash);
            if (result.startsWith("0")) {
                result = result.replaceFirst("0", result);
            }
            if (negative) {
                result = "-" + result;
            }
            return result.toLowerCase();
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            return null;
        }
    }

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String getHexString(byte[] hash) {
        char[] hexChars = new char[hash.length * 2];
        int unsigned;
        for (int j = 0; j < hash.length; j++) {
            unsigned = hash[j] & 0xFF;
            hexChars[j * 2] = hexArray[unsigned >>> 4];
            hexChars[j * 2 + 1] = hexArray[unsigned & 0x0F];
        }
        return new String(hexChars);
    }

    private static byte[] twosCompliment(byte[] input) {
        boolean carry = true;
        for (int i = input.length - 1; i >= 0; i--) {
            input[i] = (byte) ~input[i];
            if (carry) {
                carry = input[i] == 0xFF;
                input[i]++;
            }
        }
        return input;
    }

    /**
     * Compute a message digest on arbitrary byte[] data
     */
    private static byte[] digestOperation(String algorithm, byte[]... data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            Stream.of(data).forEach(messageDigest::update);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create a new PublicKey from encoded X.509 data
     */
    public static PublicKey decodePublicKey(byte[] pubKeyBytes) {
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKeyBytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.err.println("Public key reconstitute failed!");
        return null;
    }

    /**
     * Decrypt shared secret AES key using RSA private key
     */
    public static SecretKey decryptSharedKey(PrivateKey privateKey, byte[] bytes) {
        return new SecretKeySpec(decryptData(privateKey, bytes), "AES");
    }

    /**
     * Encrypt byte[] data with RSA public key
     */
    public static byte[] encryptData(Key publicKey, byte[] data) {
        return cipherOperation(Cipher.ENCRYPT_MODE, publicKey, data);
    }

    /**
     * Decrypt byte[] data with RSA private key
     */
    public static byte[] decryptData(Key privateKey, byte[] data) {
        return cipherOperation(Cipher.DECRYPT_MODE, privateKey, data);
    }

    /**
     * Encrypt or decrypt byte[] data using the specified key
     */
    private static byte[] cipherOperation(int opMode, Key key, byte[] data) {
        try {
            return createTheCipherInstance(opMode, key.getAlgorithm(), key).doFinal(data);
        } catch (IllegalBlockSizeException | BadPaddingException exception) {
            exception.printStackTrace();
        }

        System.err.println("Cipher data failed!");
        return null;
    }

    /**
     * Creates the Cipher Instance.
     */
    private static Cipher createTheCipherInstance(int opMode, String transformation, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(opMode, key);
            return cipher;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException exception) {
            exception.printStackTrace();
        }

        System.err.println("Cipher creation failed!");
        return null;
    }

    /**
     * Create a new BufferedBlockCipher instance
     */
    private static BufferedBlockCipher createBufferedBlockCipher(boolean encrypt, Key key) {
        BufferedBlockCipher cipher = new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 8));
        cipher.init(encrypt, new ParametersWithIV(new KeyParameter(key.getEncoded()), key.getEncoded(), 0, 16));
        return cipher;
    }

    public static OutputStream encryptOuputStream(SecretKey key, OutputStream outputStream) {
        return new CipherOutputStream(outputStream, createBufferedBlockCipher(true, key));
    }

    public static InputStream decryptInputStream(SecretKey key, InputStream inputStream) {
        return new CipherInputStream(inputStream, createBufferedBlockCipher(false, key));
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}