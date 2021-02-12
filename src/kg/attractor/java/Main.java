package kg.attractor.java;

// import static java.util.stream.Collectors.*;
// import static java.util.Comparator.*;

// используя статические imports
// мы импортируем *всё* из Collectors и Comparator.
// теперь нам доступны такие операции как
// toList(), toSet() и прочие, без указания уточняющего слова Collectors. или Comparator.
// вот так было до импорта Collectors.toList(), теперь стало просто toList()


import kg.attractor.java.homework.RestaurantOrders;
import kg.attractor.java.homework.domain.Order;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // это для домашки
        // выберите любое количество заказов, какое вам нравится.

        var orders = RestaurantOrders.read("orders_100.json").getOrders();

        System.out.println("__________________________________");
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Напечатать весь список заказов\n" +
                "2 - Вывести список заказов имеющих наибольшую общую стоимость.\n" +
                "3 - Вывести список заказов, имеющих наименьшую общую стоимость.\n" +
                "4 - Вывести список заказов которые были оформлены с выдачей на дом\n"+
                "5 - Вывести список из заказов на дом были наиболее прибыльные\n" +
                "6 - Вывести список из заказов на дом были наименее прибыльные\n"+
                "7 - Вывести список который будет выбирать все заказы с общей суммой больше minOrderTotal, и меньше maxOrderTotal\n"+
                "8 - Вывести метод возвращающий общую стоимость всех заказов.\n"+
                "9 - Вывести список всех email адресов для рассылки всем клиентам\n"+
                "10- Вывести список уникальных закзчиков и их заказы\n"+
                "11- Вывести список уникальных закзчиков и их суммы за заказы\n"+
                "12- Вывести заказчика с максимальной суммой всех его заказов\n"+
                "13- Вывести заказчика с минимальной суммой всех его заказов\n"+
                "14- Вывести информацию о товаре");
        int change = sc.nextInt();
        switch (change) {
            case 1:
                RestaurantOrders.printList(orders);
                break;
            case 2:
                System.out.println(RestaurantOrders.maxOrderTotal(orders, 15));
                break;
            case 3:
            System.out.println(RestaurantOrders.minOrderTotal(orders, 15));
                break;
            case 4:
            System.out.println(RestaurantOrders.homeDeliveryOrders(orders));
                break;
            case 5:
            System.out.println(RestaurantOrders.maxPrice(orders));
                break;
            case 6:
                System.out.println(RestaurantOrders.minPrice(orders));
                break;
            case 7:
                System.out.println(RestaurantOrders.filterOrder(orders,6,120));
              break;
            case 8:
                System.out.println(RestaurantOrders.totalOrders(orders)+"$");
                break;
            case 9:
                System.out.println(RestaurantOrders.getEmail(orders));
                break;
            case 10:
                System.out.println(RestaurantOrders.customerOrder(orders));
                break;
            case 11:
                System.out.println(RestaurantOrders.customerOrderSum(orders));
                break;
            case 12:
                System.out.println(RestaurantOrders.maxCustomerOrder(orders));
                break;
            case 13:
                System.out.println(RestaurantOrders.minCustomerOrder(orders));
                break;
            case 14:
                System.out.println(RestaurantOrders.productOrder(orders));

        }
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)
    }

}
