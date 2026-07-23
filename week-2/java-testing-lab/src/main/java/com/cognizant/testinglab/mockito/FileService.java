package com.cognizant.testinglab.mockito;

public class FileService {
    private final LabFileReader reader;
    private final LabFileWriter writer;

    public FileService(LabFileReader reader, LabFileWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String processFile() {
        String result = "Processed " + reader.read();
        writer.write(result);
        return result;
    }
}
