package kg.attractor.java.homework;

import com.google.gson.Gson;
import kg.attractor.java.homework.domain.Customer;
import kg.attractor.java.homework.domain.Item;
import kg.attractor.java.homework.domain.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class RestaurantOrders {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private List<Order> orders;

    private RestaurantOrders(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            orders = List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("__________"+maxOrderTotal()+"\n");
//        System.out.println(minOrderTotal()+"\n");
    }

    public static RestaurantOrders read(String fileName) {
        var ro = new RestaurantOrders(fileName);
        ro.getOrders().forEach(Order::calculateTotal);

        return ro;
    }

    public List<Order> getOrders() {
        return orders;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    // Наполните этот класс решением домашнего задания.
    // Вам необходимо создать все необходимые методы
    // для решения заданий из домашки :)
    // вы можете добавлять все необходимые imports
    //
    public static void printList(List<Order> orders){
         orders.forEach(System.out::println);
    }

    public static List<Order> maxOrderTotal(List<Order>orders,int limit){
        return orders.stream().sorted(Comparator.comparing(Order::getTotal).reversed()).limit(limit).collect(Collectors.toList());

    }
    public static List<Order> minOrderTotal(List<Order> orders, int limit) {
        return  orders.stream().sorted(Comparator.comparing(Order::getTotal)).limit(limit).collect(Collectors.toList());
    }

    public static List<Order> homeDeliveryOrders(List<Order> orders) {
        return orders.stream().filter(Order::isHomeDelivery).collect(Collectors.toList());
    }

    public static Order maxPrice(List<Order> orders){
        return homeDeliveryOrders(orders).stream().max(Comparator.comparing(Order::getTotal)).get();
    }
    public static Order minPrice(List<Order> orders){
        return homeDeliveryOrders(orders).stream().min(Comparator.comparing(Order::getTotal)).get();
    }

    public static List <Order> filterOrder(List<Order> orders, double MinOrderTotal, double MaxOrderTotal){
        return orders.stream().sorted(Comparator.comparingDouble(Order::getTotal)).dropWhile(e->e.getTotal()>MinOrderTotal).takeWhile(e->e.getTotal()<MaxOrderTotal).collect(Collectors.toList());
    }
    public static TreeSet<String> getEmail(List<Order> orders){
        return orders.stream().map(e -> e.getCustomer().getEmail()).distinct().collect(toCollection(TreeSet::new));
    }

    public static Double totalOrders(List<Order> orders){
        return orders.stream().mapToDouble(Order::getTotal).sum();
    }
    public static Map<String, List<Order>>customerOrder(List<Order> orders) {
        return orders.stream().distinct().collect(Collectors.groupingBy(Order -> Order.getCustomer().getFullName()));
    }
    public static Map<Customer, Double> customerOrderSum(List<Order> orders){
        return orders.stream().collect(groupingBy(Order::getCustomer,summingDouble(Order::getTotal)));
    }
    public static Optional<Map.Entry<Customer, Double>> maxCustomerOrder(List<Order> orders){
        return customerOrderSum(orders).entrySet().stream().max(Comparator.comparing(e->e.getValue()));
    }

    public static Optional<Map.Entry<Customer, Double>> minCustomerOrder(List<Order> orders){
        return customerOrderSum(orders).entrySet().stream().min(Comparator.comparing(e->e.getValue()));
    }
    public static Map<String, Set<String>> productOrder(List<Order>orders){
        Function<? super Order,String> byEmail = order -> order.getCustomer().getEmail();
        Function<Order, Stream<? extends Item>> byItems = o -> o.getItems().stream();
        return  orders.stream().collect(groupingBy(byEmail , flatMapping(byItems, mapping(Item::getName,toSet()))));

    }

}
