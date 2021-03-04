package model;

public class EncryptionModel {

    // Define model values
    private String encryptionAlgorithm;
    private String keyString;
    private String ivString;
    private String message;

    // Define public access model
    public EncryptionModel(String keyString, String encryptionAlgorithm, String ivString, String message) {
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.keyString = keyString;
        this.ivString = ivString;
        this.message = message;
    }

    public EncryptionModel() {
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }
    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setKeyString(String keyString) { this.keyString = keyString; }
    public String getKeyString() {
        return keyString;
    }

    public void setIvString(String ivString) {
        this.ivString = ivString;
    }
    public String getIvString() {
        return ivString;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
