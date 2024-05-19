package homework;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerService {
    private Map<Customer, String> customerServices;
    public CustomerService() {
        customerServices = new HashMap<>();
    }

    public Map.Entry<Customer, String> getSmallest() {
        TreeSet<Long> scoresTreeSet = fillScoresTreeSet();
        return scoresTreeSet.size() != 0
                ? customerServices.entrySet().stream()
                .filter(f -> f.getKey().getScores() == scoresTreeSet.getFirst())
                .findFirst()
                .map(v -> new AbstractMap.SimpleEntry<Customer, String>(
                        new Customer(v.getKey().getId(), v.getKey().getName(), v.getKey().getScores()),
                        v.getValue()))
                .get()
                : null;
    }

    public TreeSet<Long> fillScoresTreeSet() {
        List<Long> scoresList = customerServices.entrySet().stream().map(v -> v.getKey().getScores()).collect(Collectors.toList());
        return new TreeSet<Long>(scoresList);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        TreeSet<Long> scoresTreeSet = fillScoresTreeSet();
        long customerScores = customer.getScores();
        if (scoresTreeSet.getLast() > customerScores) {
            long nextScores = scoresTreeSet.stream().filter(f -> f > customerScores ).findFirst().get();
            for (Map.Entry<Customer, String> entry : customerServices.entrySet()) {
                if (entry.getKey().getScores() == nextScores) {
                    return new AbstractMap.SimpleEntry<Customer, String>(
                            new Customer(entry.getKey().getId(),entry.getKey().getName(), entry.getKey().getScores()),
                            entry.getValue());
                }
            }
        }
        return null;
    }

    public void add(Customer customer, String data) {
        customerServices.put(customer, data);
    }
}
