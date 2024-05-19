package homework;

import java.util.Stack;

public class CustomerReverseOrder {

    private Stack<Customer> сustomerReverseOrder;

    public CustomerReverseOrder() {
        сustomerReverseOrder = new Stack<>();
    }

    public void add(Customer customer) {
        сustomerReverseOrder.add(customer);
    }

    public Customer take() {
        return сustomerReverseOrder.removeLast();
    }
}
