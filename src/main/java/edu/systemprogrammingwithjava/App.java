package edu.systemprogrammingwithjava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "System Programming with Java!" );

        // How to create a Stream
        Stream<String> stream1 = Stream.empty();

        List<String> list = Arrays.asList("1","2");
        Stream<String> stream2 = list.stream();

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name1","System");
        hashMap.put("name2","Programing");
        Stream<String> stream3 = hashMap.values().stream();

        String[] strings = new String[]{"System","Programing","With","Java"};
        Stream<String> stream4 = Arrays.stream(strings);

        //Before Streams
        /*for(String s:strings){
            if(s.length()>6)
                System.out.println(s);
        }*/

        //After Streams

        Predicate<String> filter = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                System.out.println("Evaluating: "+s);
                if(s.length()>6)
                    return true;
                return false;
            }
        };

        //Predicate<String> filter1 = s -> s.length()>6;

        //stream4.filter(str -> str.length()>6).forEach(System.out::println);

        // Lazy Loading
        Stream<String> stream5 = stream4.filter(filter);
        stream5.forEach(System.out::println);
    }
}
