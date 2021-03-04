package services;

import model.EncryptionModel;
import utils.UtilConverters;
import utils.UtilHexConv;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptDecryptService {

    // Begin AES Encryption using algorithm, message, key and initialisation vector
    public static String encrypt(EncryptionModel model)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        // Generate iv param spec
        IvParameterSpec ivParamSpec = GeneratorService.generateIvParamSpec(UtilHexConv.hexStringToByteArray(model.getIvString()));

        // Define cipher being used
        Cipher cipher = Cipher.getInstance(model.getEncryptionAlgorithm());

        // Initialise cipher for encryption, set Cipher mode, pass key and pass iv param spec
        cipher.init(Cipher.ENCRYPT_MODE, UtilConverters.stringToSecretKey(model.getKeyString()), ivParamSpec);

        // Define cipher-text as encrypted input string
        // Encode byte array of cipher-text to a string and return cipher-text as
        // a string object to method caller
        return Base64.getEncoder().encodeToString((cipher.doFinal(model.getMessage().getBytes())));
    }

    // Begin AES Decryption using algorithm, cipher-text, key and initialisation vector
    public static String decrypt(EncryptionModel model) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        // Generate iv param spec
        IvParameterSpec ivParamSpec = GeneratorService.generateIvParamSpec(UtilHexConv.hexStringToByteArray(model.getIvString()));

        // Define cipher being used
        Cipher cipher = Cipher.getInstance(model.getEncryptionAlgorithm());

        // Initialise cipher for decryption, set Cipher mode, pass key and pass iv param spec
        cipher.init(Cipher.DECRYPT_MODE, UtilConverters.stringToSecretKey(model.getKeyString()), ivParamSpec);

        // Define plain-text as decrypted byte array of cipher-text
        // Convert byte array to a standard string object and return plain text as
        // a string object to method caller
        return new String((cipher.doFinal(Base64.getDecoder().decode(model.getMessage()))));
    }
}
