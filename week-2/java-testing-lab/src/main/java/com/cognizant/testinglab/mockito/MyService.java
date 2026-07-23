package com.cognizant.testinglab.mockito;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchData(String key) {
        return externalApi.getData(key);
    }

    public void notify(String message) {
        externalApi.notify(message);
    }
}
