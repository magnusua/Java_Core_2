package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

import static org.decimal4j.util.DoubleRounder.*;

public class Box<T extends Fruit> {
    private ArrayList<T> box;
    private final String boxName;

    @SafeVarargs
    public Box(String boxName, T... fruits) {
        this.boxName = boxName;
        this.box = new ArrayList<>(Arrays.asList(fruits));
    }

    public int getSize() {
        return box.size();
    }

    public float getBoxWeight() {
        float totalWeight = 0.0F;
        for (T t : box) {
            totalWeight += t.getWeight();
        }
        return (float) round(totalWeight, 2);
    }

    public void addFruit(T fruit) {
        if (this.box.isEmpty()) {
            this.box.add(fruit);
        } else if (this.box.get(0).getClass().equals(fruit.getClass())) {
            this.box.add(fruit);
        } else {
            System.out.println("Добавить " + fruit.getClass().getName().replace("ru.geekbrains.", "")
                    + " не удалось, т.к. в коробке уже есть фрукты другого типа");
        }

    }

    public boolean compare(Box<? extends Fruit> boxToCompare) {
        return Math.abs(this.getBoxWeight() - boxToCompare.getBoxWeight()) >= 0
                && (Math.abs(this.getBoxWeight() - boxToCompare.getBoxWeight()) < 0.0001);
    }

    public String getBoxTypeFruit() {
        if (this.box.size() > 0) {
            return this.box.get(0).getClass().getName().replace("ru.geekbrains.", ""); //берем имя класса, удаляя лишнее
        } else return "Empty Box";
    }

    public void moveObjTo(@org.jetbrains.annotations.NotNull Box<T> targetbox) {
        if (this.box.size() > 0 & (this.getBoxTypeFruit().equals(targetbox.getBoxTypeFruit()) | targetbox.getBoxTypeFruit().contains("Empty Box"))) {
            targetbox.box.addAll(this.box);
            this.box.clear();
            System.out.println("Перегрузить удалось");
        } else
            System.out.println("Перегрузить " + this.getBoxTypeFruit() + " из " + this.getBoxName() + " в " + targetbox.getBoxName() + " не удалось");
    }

    public String getBoxName() {
        return boxName;
    }

}