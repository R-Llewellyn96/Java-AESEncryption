package services;

import utils.UtilConverters;
import utils.UtilHexConv;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class GeneratorService {

    // algorithmInstance = "AES"
    // Generate Secret Key for AES, Symmetric key used for encryption and decryption
    public static String generateKey(String algorithmInstance, int keySize) throws NoSuchAlgorithmException {

        // Create key generator object and define instance as AES Symmetric Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmInstance);

        // Initialise key generator
        keyGenerator.init(keySize);

        // Generate key and assign value to key and set keyString of encryption model
        return UtilConverters.secretKeyToString(keyGenerator.generateKey());
    }

    // Generate Secret Key from user given password, note this is much less secure than random generation
    public static String getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        // Create key generator object and define instance as Password Based Encryption with SHA256 hashing
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        // Define key specifications for password based encryption, convert password to byte array,
        // apply salt and use SHA256 to hash the result
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);

        // Generate Secret Key
        SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        // Generate secret key using PBKDF2 algorithm and specifications defined above and
        // set keyString in encryption model
        return UtilConverters.secretKeyToString(key);
    }

    // Generate Initialisation Vector
    public static String generateIV() {

        // Define initialisation vector as a 16 bit byte
        byte[] iv = new byte[16];

        // Generate random byte string in a secure way
        new SecureRandom().nextBytes(iv);

        // Set initialisation vector
        return UtilHexConv.toHex(iv);
    }

    // AES Requires an initialisation vector in CBC mode,
    // this method generates an initialisation vector in a securely random way
    public static IvParameterSpec generateIvParamSpec(byte[] iv) {

        // Return initialisation vector to method caller
        return new IvParameterSpec(iv);
    }

}
