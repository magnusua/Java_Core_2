package ru.geekbrains;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.*;

/**
 * Java Core. Professional level. Lesson 1
 * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 * 3. Большая задача: классы Fruit -> Apple, Orange
 * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 * Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 * Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare
 * в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
 * Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 * Не забываем про метод добавления фрукта в коробку.
 */
public class Main {
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            out.println("1 и 2 задание");
            //          Task 1.
            String[] chars = IntStream.rangeClosed('А', 'я').mapToObj(c -> "" + (char) c).collect(Collectors.joining()).split("(?!^)"); // собираем в массив
            int pos1 = chars.length - (random.nextInt(chars.length) + 1); //позиция 1 го символа
            int pos2 = chars.length - (random.nextInt(chars.length) + 1); //позиция 2 го символа
            out.println("Исходный массив: \n" + Arrays.toString(chars));
            out.println("Измененный масив: \n" + Arrays.toString(SwapArray(chars, pos1, pos2)));
            //          Task 2.
            ToArrayList(chars); //переводим массив в ArrayList
            out.println("\n" + String.join(", ", chars)); //печатаем что получилось https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string
            out.println("\n");
        }).start();
//          Task            3.
        Thread.sleep(3000);
        out.println("3 задание");
        var boxes = new ArrayList<Box>(); //создаем массив для коробок, для удобства работы
        for (int i = 0; i < 10; i++) {
            boxes.add(new Box(RandomName()));
        }

        for (Box box : boxes) {
            int Type = random.nextInt(2); // случайный тип фруктов
            int bound = random.nextInt(40); //случайное число фруктов в коробках
            for (int j = 0; j < bound; j++) { //наполняем коробку
                if (Type == 0) box.addFruit(new Apple());
                else box.addFruit(new Orange());
            }
        }
        //печатаем коробки что получились
        for (Box box : boxes) {
            out.println(box.getBoxName() + " " + box.getSize() + " "
                    + box.getBoxWeight() + " " + box.getBoxTypeFruit() + "\n ");
        }
        // сравнение веса коробок
        out.println("compare Box 1 to Box 2: " + boxes.get(0).compare(boxes.get(1)));

        boxes.get(0).moveObjTo(boxes.get(1));

    }//main

    //      1
    public static <T> T[] SwapArray(T[] arr, int i, int j) {
        try {
            T tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        } catch (Exception ignored) {
            out.println("Error SwapArray");
            exit(1);
        }
        return arr;
    }

    //      2
    public static <T> ArrayList<T> ToArrayList(T arr) {
        return new ArrayList<>(Collections.singletonList(arr));
    }

    public static String RandomName() {
        char[] chars = IntStream.rangeClosed('A', 'z').mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();

        int NameLength = (2 + random.nextInt(6));
        String Surname = "Box_";
        StringBuilder sb = new StringBuilder(NameLength); //создаем строку
        sb.append(Surname);
        for (int i = 0; i < NameLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c); //генерируем строку из случайных букв
        }
        return sb.substring(0, 1).toUpperCase() + sb.substring(1).toLowerCase(); //выводим строку с Заглавной 1 буквой
    }
}