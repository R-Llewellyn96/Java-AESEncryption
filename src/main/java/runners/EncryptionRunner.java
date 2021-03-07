package runners;

import model.EncryptionModel;
import services.EncryptDecryptService;
import services.GeneratorService;
import utils.UtilConverters;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionRunner {

    public static void encryptionRunner(String message, int bitLengthChoice) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        // Define AES bit length to choose from
        final int[] encryptionBitLength = {128, 256};

        // Define encryption model object
        EncryptionModel encryptionModel = new EncryptionModel();

        // Set object values
        encryptionModel.setEncryptionAlgorithm("AES/CBC/PKCS5Padding");
        encryptionModel.setKeyString(GeneratorService.generateKey("AES", encryptionBitLength[bitLengthChoice]));
        encryptionModel.setIvString(GeneratorService.generateIV());
        encryptionModel.setMessage(message);

        // Encrypt Message
        String ciphertext = EncryptDecryptService.encrypt(encryptionModel);

        // Get object values to print
        System.out.println(encryptionModel.getEncryptionAlgorithm());
        System.out.println(encryptionModel.getKeyString());
        System.out.println(encryptionModel.getIvString());
        System.out.println(encryptionModel.getMessage());

        // Print encrypted message
        System.out.println(ciphertext);

        // End of main method
        System.out.println("\nProgram Terminated.");
    }

    public static String encryptionRunnerWKey(String message, String key) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        // Define encryption model object
        EncryptionModel encryptionModel = new EncryptionModel();

        // Set object values
        encryptionModel.setEncryptionAlgorithm("AES/CBC/PKCS5Padding");
        encryptionModel.setKeyString(key);
        encryptionModel.setIvString(GeneratorService.generateIV());
        encryptionModel.setMessage(message);

        // Encrypt Message
        String ciphertext = EncryptDecryptService.encrypt(encryptionModel);

        // Return IV prepended to Ciphertext
        return (encryptionModel.getIvString() + ciphertext);
    }

    public static void decryptionRunner(String IvAndCiphertext, String encryptionKey) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        // Define encryption model object
        EncryptionModel encryptionModel = new EncryptionModel();

        // Set object values
        encryptionModel.setEncryptionAlgorithm("AES/CBC/PKCS5Padding");

        // split IV and Key, [0] = IV, [1] = Key
        String[] ivAndCiphertextArr = UtilConverters.stripIVFromCiphertext(IvAndCiphertext);

        // Set IV, Ciphertext and Key
        encryptionModel.setIvString(ivAndCiphertextArr[0]);
        encryptionModel.setMessage(ivAndCiphertextArr[1]);
        encryptionModel.setKeyString(encryptionKey);

        // Decrypt Message
        String plaintext = EncryptDecryptService.decrypt(encryptionModel);

        // Get object values to print
        System.out.println(encryptionModel.getEncryptionAlgorithm());
        System.out.println(encryptionModel.getKeyString());
        System.out.println(encryptionModel.getIvString());
        System.out.println(encryptionModel.getMessage());

        // Print decrypted message
        System.out.println(plaintext);

        // End of main method
        System.out.println("\nProgram Terminated.");
    }

    public static String decryptionRunnerWGUI(String IvAndCiphertext, String encryptionKey) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        // Define encryption model object
        EncryptionModel encryptionModel = new EncryptionModel();

        // Set object values
        encryptionModel.setEncryptionAlgorithm("AES/CBC/PKCS5Padding");

        // split IV and Key, [0] = IV, [1] = Key
        String[] ivAndCiphertextArr = UtilConverters.stripIVFromCiphertext(IvAndCiphertext);

        // Set IV, Ciphertext and Key
        encryptionModel.setIvString(ivAndCiphertextArr[0]);
        encryptionModel.setMessage(ivAndCiphertextArr[1]);
        encryptionModel.setKeyString(encryptionKey);

        // Decrypt Message
        String plaintext = EncryptDecryptService.decrypt(encryptionModel);

        // Return Decrypted Message
        return plaintext;
    }
}
