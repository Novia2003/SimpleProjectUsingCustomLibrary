package org.example;

import ru.vsu.cs.novichikhin.RegularHashMultiSet;
import ru.vsu.cs.novichikhin.RegularHashSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        RegularHashSet<String> countriesAndColorsTheirFlags = readLinesFromFile("src/main/resources/input.txt");
        RegularHashMultiSet<String> colors = fillSetWithColors(countriesAndColorsTheirFlags);

        writeResults(countriesAndColorsTheirFlags.size(), colors);
    }

    private static RegularHashMultiSet<String> fillSetWithColors(RegularHashSet<String> lines) {
        RegularHashMultiSet<String> colors = new RegularHashMultiSet<>();

        Iterator<String> iterator = lines.iterator();

        while (iterator.hasNext()) {
            String currentColors = iterator.next().replaceAll(".+: ", "");
            String[] array = currentColors.split(", ");

            for (String color : array) {
                colors.add(color, 1);
            }
        }

        return colors;
    }

    private static RegularHashSet<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        RegularHashSet<String> set = new RegularHashSet<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                set.add(scanner.nextLine());
            }
        }

        return set;
    }

    private static void writeResults(int quantityCountries, RegularHashMultiSet<String> colors) {
        System.out.printf("Были рассмотрены цвета флагов %s стран.", quantityCountries);
        System.out.println("Список встречаемости каждого цвета среди всех представленных в файле стран: ");

        Iterator<String> iterator = colors.iterator();

        while (iterator.hasNext()) {
            String color = iterator.next();
            System.out.println(color + ": " + colors.getQuantity(color));
        }
    }
}