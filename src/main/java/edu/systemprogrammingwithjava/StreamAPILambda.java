package edu.systemprogrammingwithjava;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StreamAPILambda {
    public static void main(String[] args) {
        //SUPPLIER
        /*Supplier<List<Customer>> nameList = new Supplier<List<Customer>>() {
            @Override
            public List<Customer> get() {
                List<Customer> list = new ArrayList<>();
                list.add(new Customer("Jeorge","US", 29, 2000));
                list.add(new Customer("Kelly","UK", 35, 1500));
                list.add(new Customer("Samanta","US", 31, 0));
                list.add(new Customer("Raghu","IN", 42, 3500));
                list.add(new Customer("Soniya","IN", 50, 2100));
                list.add(new Customer("Mat","US", 61, 2700));

                return list;
            }
        };*/

        Supplier<List<Customer>> nameList2 = () -> {
            List<Customer> list = new ArrayList<>();
            list.add(new Customer("Jeorge","US", 29, 2000));
            list.add(new Customer("Kelly","UK", 35, 1500));
            list.add(new Customer("Samanta","US", 31, 0));
            list.add(new Customer("Raghu","IN", 42, 3500));
            list.add(new Customer("Soniya","IN", 50, 2100));
            list.add(new Customer("Mat","US", 61, 2700));

            return list;
        };

        nameList2.get().stream().forEach(s -> System.out.println("SUPPLIER: "+s));

        //FILTER
        /*Predicate<Customer> customerPredicateBalancerGt2000  = new Predicate<Customer>() {
            @Override
            public boolean test(Customer customer) {
                return customer.balance>2000?true:false;
            }
        };*/
        Predicate<Customer> customerPredicateBalancerGt20001  = customer -> customer.balance>2000?true:false;
        nameList2.get().stream().filter(customerPredicateBalancerGt20001).forEach(s -> System.out.println("PREDICATE: "+s));

        //MAP
        /*Function<Customer, Integer> function = new Function<Customer, Integer>() {
            @Override
            public Integer apply(Customer customer) {
                return customer.age;
            }
        };*/
        Function<Customer, Integer> function1 = customer -> customer.age;

        nameList2.get().stream().map(function1).forEach(s -> System.out.println("FUNCTION: "+s));

        //CONSUMER
        Consumer<Customer> consumer = new Consumer<Customer>() {
            @Override
            public void accept(Customer customer) {
                System.out.println("CONSUMER: "+customer);
            }
        };
        nameList2.get().stream().forEach(consumer);
    }
}
