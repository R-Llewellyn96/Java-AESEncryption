package model;

import java.io.File;

public class EncryptionFileModel {

    // Define model values
    private String encryptionAlgorithm;
    private String keyString;
    private String ivString;
    private File file;

    // Define public access model
    public EncryptionFileModel(String keyString, String encryptionAlgorithm, String ivString, File file) {
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.keyString = keyString;
        this.ivString = ivString;
        this.file = file;
    }

    public EncryptionFileModel() {
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

    public void setFile(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }
}
