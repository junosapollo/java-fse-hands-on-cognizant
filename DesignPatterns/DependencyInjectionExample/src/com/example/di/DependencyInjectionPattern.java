package com.example.di;

interface CustomerRepository {
    String findCustomerById(String id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        // Mock database call
        if (id.equals("1001")) {
            return "Alice Smith";
        } else if (id.equals("1002")) {
            return "Bob Jones";
        }
        return "Unknown Customer";
    }
}

class CustomerService {
    private CustomerRepository customerRepository;

    // Dependency Injection via constructor
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomerDetails(String id) {
        String customerName = customerRepository.findCustomerById(id);
        System.out.println("Customer Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + customerName);
        System.out.println("-------------------------");
    }
}

public class DependencyInjectionPattern {
    public static void main(String[] args) {
        // Inject the concrete repository into the service manually
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        System.out.println("Fetching customer 1001:");
        service.displayCustomerDetails("1001");

        System.out.println("Fetching customer 1002:");
        service.displayCustomerDetails("1002");
        
        System.out.println("Fetching customer 9999:");
        service.displayCustomerDetails("9999");
    }
}
