package edu.systemprogrammingwithjava;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StreamAPIMain {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("Steve","US",1000,"Checking"));
        accounts.add(new Account("Bob","US",1500,"Saving"));
        accounts.add(new Account("Ram","CA",900,"Saving"));
        accounts.add(new Account("John","UK",800,"Saving"));
        accounts.add(new Account("Viva","UK",1100,"Checking"));

        System.out.println("\nName of people having less then 1000$ in account");
        accounts.stream()
                .filter(account -> account.balance<1000)
                .forEach(account -> System.out.println(account.holderName));


        System.out.println("\n Name of people having Checking account in US");
        accounts.stream()
                .filter(account -> account.country.equalsIgnoreCase("US") && account.type.equalsIgnoreCase("Checking"))
                .forEach(account -> System.out.println(account.holderName));

        System.out.println("\n name of person who is having max balance");

        /*Comparator<Account> comp = new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o2.balance> o1.balance?-1:0;
            }
        };*/
        //Comparator<Account> comp = (o1, o2) -> o2.balance> o1.balance?-1:0;

        System.out.println(accounts.stream().max((o1, o2) -> o2.balance> o1.balance?-1:0).get().holderName);

        System.out.println("\nSum of all count in US");
        final int[] sum = {0};

        accounts.stream()
                .filter(account -> account.country.equalsIgnoreCase("US"))
                .forEach(account -> sum[0] = sum[0]+account.balance);
        System.out.println("Sum of US Balanac: "+ sum[0]);

        System.out.println("\nSum of all account by Country");
        Map<String, Integer> sumByCountry =
                accounts.stream()
                        .collect( Collectors.groupingBy(account -> account.country)
                        ).entrySet().stream()
                        .collect( Collectors.toMap(
                                e -> e.getKey(),
                                e -> e.getValue().stream()
                                        .mapToInt(a -> a.balance).sum()
                        ));
        sumByCountry.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

        AtomicInteger counter = new AtomicInteger(0);
        int listSize = 2;

        Map<Integer, List<Account>> collectMap = accounts.stream().collect(Collectors.groupingBy(x -> counter.getAndIncrement() / listSize));

        Collection<List<Account>> collectLists = accounts.stream().collect(Collectors.groupingBy(x -> counter.getAndIncrement() / listSize)).values();

        collectLists.forEach(list1 -> {
            System.out.println("");
            list1.forEach(a1 -> System.out.println(a1.holderName));
        });
    }
}
