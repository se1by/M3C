package com.bitbyterstudios.m3c.util;

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

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class CryptoHelper {
    /**
     * ISO_8859_1
     */
    public static final Charset charSet = Charset.forName("ISO_8859_1");

    /**
     * Generate a new shared secret AES key from a secure random source
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println("Key pair generation failed!");
            return null;
        }
    }

    /**
     * Compute a serverId hash for use by sendSessionRequest()
     */
    public static String getServerIdHash(String serverId, PublicKey pubKey, SecretKey secretKey) {
        try {
            byte[] hash = digestOperation("SHA-1", new byte[][]{serverId.getBytes("ISO_8859_1"), secretKey.getEncoded(), pubKey.getEncoded()});
            boolean negative = (hash[0] & 0x80) == 0x80;
            if (negative) { hash = twosCompliment(hash); }
            String result = getHexString(hash);
            if (result.startsWith("0")) { result.replaceFirst("0", result); }
            if (negative) { result = "-" + result; }
            return result.toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String getHexString(byte[] hash) {
        char[] hexChars = new char[hash.length * 2];
        int v;
        for ( int j = 0; j < hash.length; j++ ) {
            v = hash[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static byte[] twosCompliment(byte[] p) {
        int i;
        boolean carry = true;
        for (i = p.length - 1; i >= 0; i--) {
            p[i] = (byte) ~p[i];
            if (carry) {
                carry = p[i] == 0xFF;
                p[i]++;
            }
        }
        return p;
    }

    /**
     * Compute a message digest on arbitrary byte[] data
     */
    private static byte[] digestOperation(String s, byte[]... data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(s);
            byte[][] var3 = data;
            int var4 = data.length;

            for (int i = 0; i < var4; ++i) {
                byte[] var6 = var3[i];
                messageDigest.update(var6);
            }

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.err.println("Public key reconstitute failed!");
        return null;
    }

    /**
     * Decrypt shared secret AES key using RSA private key
     */
    public static SecretKey decryptSharedKey(PrivateKey privKey, byte[] bytes) {
        return new SecretKeySpec(decryptData(privKey, bytes), "AES");
    }

    /**
     * Encrypt byte[] data with RSA public key
     */
    public static byte[] encryptData(Key pubKey, byte[] data) {
        return cipherOperation(1, pubKey, data);
    }

    /**
     * Decrypt byte[] data with RSA private key
     */
    public static byte[] decryptData(Key privKey, byte[] data) {
        return cipherOperation(2, privKey, data);
    }

    /**
     * Encrypt or decrypt byte[] data using the specified key
     */
    private static byte[] cipherOperation(int par0, Key key, byte[] data) {
        try {
            return createTheCipherInstance(par0, key.getAlgorithm(), key).doFinal(data);
        } catch (IllegalBlockSizeException var4) {
            var4.printStackTrace();
        } catch (BadPaddingException var5) {
            var5.printStackTrace();
        }

        System.err.println("Cipher data failed!");
        return null;
    }

    /**
     * Creates the Cipher Instance.
     */
    private static Cipher createTheCipherInstance(int par0, String par1Str, Key par2Key) {
        try {
            Cipher var3 = Cipher.getInstance(par1Str);
            var3.init(par0, par2Key);
            return var3;
        } catch (InvalidKeyException var4) {
            var4.printStackTrace();
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        } catch (NoSuchPaddingException var6) {
            var6.printStackTrace();
        }

        System.err.println("Cipher creation failed!");
        return null;
    }

    /**
     * Create a new BufferedBlockCipher instance
     */
    private static BufferedBlockCipher createBufferedBlockCipher(boolean par0, Key par1Key) {
        BufferedBlockCipher var2 = new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 8));
        var2.init(par0, new ParametersWithIV(new KeyParameter(par1Key.getEncoded()), par1Key.getEncoded(), 0, 16));
        return var2;
    }

    public static OutputStream encryptOuputStream(SecretKey par0SecretKey, OutputStream par1OutputStream) {
        return new CipherOutputStream(par1OutputStream, createBufferedBlockCipher(true, par0SecretKey));
    }

    public static InputStream decryptInputStream(SecretKey par0SecretKey, InputStream par1InputStream) {
        return new CipherInputStream(par1InputStream, createBufferedBlockCipher(false, par0SecretKey));
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}