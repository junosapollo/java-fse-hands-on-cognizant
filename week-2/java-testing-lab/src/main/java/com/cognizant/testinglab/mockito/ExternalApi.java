package com.cognizant.testinglab.mockito;

public interface ExternalApi {
    String getData();
    String getData(String key);
    void notify(String message);
}
