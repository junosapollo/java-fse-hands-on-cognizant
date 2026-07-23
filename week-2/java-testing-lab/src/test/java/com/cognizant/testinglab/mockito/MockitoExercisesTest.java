package com.cognizant.testinglab.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

class MockitoExercisesTest {
    @Test
    void mocksAndStubsExternalApi() {
        ExternalApi api = mock(ExternalApi.class);
        when(api.getData()).thenReturn("Mock Data");
        assertEquals("Mock Data", new MyService(api).fetchData());
    }

    @Test
    void verifiesArgumentsMatchersVoidCallsAndOrder() {
        ExternalApi api = mock(ExternalApi.class);
        when(api.getData(anyString())).thenReturn("value");
        MyService service = new MyService(api);
        assertEquals("value", service.fetchData("key"));
        service.notify("message");
        verify(api).getData(eq("key"));
        verify(api).notify(contains("mess"));
        InOrder order = inOrder(api);
        order.verify(api).getData("key");
        order.verify(api).notify("message");
    }

    @Test
    void handlesConsecutiveReturnsAndVoidFailures() {
        Repository repo = mock(Repository.class);
        when(repo.getData()).thenReturn("First Mock Data", "Second Mock Data");
        Service service = new Service(repo);
        assertEquals("Processed First Mock Data", service.processData());
        assertEquals("Processed Second Mock Data", service.processData());

        LabFileReader reader = mock(LabFileReader.class);
        LabFileWriter writer = mock(LabFileWriter.class);
        when(reader.read()).thenReturn("content");
        assertEquals("Processed content", new FileService(reader, writer).processFile());
        verify(writer).write("Processed content");
    }

    @Test
    void mocksAdvancedRepositoryRestFileAndNetworkDependencies() {
        Repository repo = mock(Repository.class);
        when(repo.getData()).thenReturn("Mock Data");
        assertEquals("Processed Mock Data", new Service(repo).processData());

        RestClient restClient = mock(RestClient.class);
        when(restClient.getResponse()).thenReturn("Mock Response");
        assertEquals("Fetched Mock Response", new ApiService(restClient).fetchData());

        NetworkClient networkClient = mock(NetworkClient.class);
        when(networkClient.connect()).thenReturn("Mock Connection");
        assertEquals("Connected to Mock Connection", new NetworkService(networkClient).connectToServer());
    }
}
