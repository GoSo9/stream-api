package edu.systemprogrammingwithjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StreamAPIMethods {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Bob","Willy","Rose","Sita","Kabir","Wrestle","Mongy");

        //MAP
        //List<Integer> collect = list.stream().map(s -> s.length()).collect(Collectors.toList());
        List<Integer> collect = list.stream().map(String::length).collect(Collectors.toList());

        System.out.println("MAP: "+ collect);

        //REDUCE
        BinaryOperator<String> binaryOperator = new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                return s+s2;
            }
        };
        System.out.println("REDUCE: "+list.stream().filter(s -> s.length()==4).reduce(binaryOperator).get());

        //FILTER
        list.stream().filter(s -> s.startsWith("W")).collect(Collectors.toList()).forEach(s -> System.out.println("FILTER: "+s));

        //FLAT MAP
        List<Account> accounts1 = new ArrayList<>();
        accounts1.add(new Account("Steve","US",1000,"Checking"));
        accounts1.add(new Account("Bob","US",1500,"Saving"));
        accounts1.add(new Account("Ram","CA",900,"Saving"));
        accounts1.add(new Account("John","UK",800,"Saving"));
        accounts1.add(new Account("Viva","UK",1100,"Checking"));

        List<Account> accounts2 = new ArrayList<>();
        accounts2.add(new Account("Steve1","US",1000,"Checking"));
        accounts2.add(new Account("Bob1","US",1500,"Saving"));
        accounts2.add(new Account("Ram1","CA",900,"Saving"));
        accounts2.add(new Account("John1","UK",800,"Saving"));
        accounts2.add(new Account("Viva1","UK",1100,"Checking"));

        List<List<Account>> accountList = Arrays.asList(accounts1, accounts2);
        accountList.stream().forEach(System.out::println);

        List<Account> accountListFlatMap = accountList.stream().flatMap(Collection::stream).collect(Collectors.toList());
        accountListFlatMap.stream().forEach(a -> System.out.println("FLATMAP: "+ a.holderName));

        //COUNT
        System.out.println("COUNT: "+accountListFlatMap.stream().count());

        //LIMIT
        accountListFlatMap.stream().limit(2).forEach(s -> System.out.println("LIMIT: "+s.holderName));

        //SKIP
        accountListFlatMap.stream().skip(2).forEach(s -> System.out.println("SKIP: "+s.holderName));

        //SORTED
        //Comparator<Account> comp = (o1, o2) -> o2.balance>o1.balance?-1:0;
        accountListFlatMap.stream().sorted((o1, o2) -> o2.balance>o1.balance?-1:0).forEach(s -> System.out.println("SORTED: "+s.holderName + " - " + s.balance));

        //PEEK
        accountListFlatMap.stream().peek(s -> System.out.println("PEEK: "+s.holderName)).forEach(s -> System.out.println("AFTER PEEK: "+s.holderName));

        //ANY MATCH / ALL MATCH / NONE MATCH
        System.out.println("ANY MATCH: "+accountListFlatMap.stream().anyMatch(a -> a.balance>1000));
        System.out.println("ALL MATCH: "+accountListFlatMap.stream().allMatch(a -> a.balance>1000));
        System.out.println("NONE MATCH:" + accountListFlatMap.stream().noneMatch(a -> a.balance<200));

    }
}
