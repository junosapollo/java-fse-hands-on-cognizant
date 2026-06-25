package com.example.sorting;

class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order [ID=" + orderId + ", Customer=" + customerName + ", TotalPrice=$" + totalPrice + "]";
    }
}

public class SortingCustomerOrders {

    // bubble sort
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() < orders[j + 1].getTotalPrice()) { // sort descending
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // quick sort average
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() > pivot) { // sort descending
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders1 = {
                new Order("O1", "Alice", 250.0),
                new Order("O2", "Bob", 150.0),
                new Order("O3", "Charlie", 500.0),
                new Order("O4", "Diana", 100.0)
        };

        Order[] orders2 = orders1.clone();

        System.out.println("Using Bubble Sort (Descending order of Total Price):");
        bubbleSort(orders1);
        for (Order o : orders1) {
            System.out.println(o);
        }

        System.out.println("\nUsing Quick Sort (Descending order of Total Price):");
        quickSort(orders2, 0, orders2.length - 1);
        for (Order o : orders2) {
            System.out.println(o);
        }

    }
}
