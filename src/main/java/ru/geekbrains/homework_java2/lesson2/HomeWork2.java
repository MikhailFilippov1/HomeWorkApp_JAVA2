package ru.geekbrains.homework_java2.lesson2;

import java.util.Scanner;

public class HomeWork2 {

    public static void main(String[] args) throws MyArraySizeException {
        int numVariant;

        String[][] array1 = {{"1", "2", "3", "4"},
                             {"5", "6", "7", "8"},
                             {"9", "1", "2", "3"},
                             {"4", "5", "6", "7"}};

        String[][] array2 = {{"1    ", "Super", "3    ", "4    "},
                             {"1    ", "2    ", "3    ", "4    "},
                             {"1    ", "2    ", "Money", "4    "},
                             {"1    ", "2    ", "3    ", "4    "}};

        String[][] array3 = {{"1", "2", "3", "4", "5"},
                             {"5", "6", "7", "8"},
                             {"9", "1", "2", "3", "7"},
                             {"4", "5", "6", "7"}};

        for (int y = 0; y < array1.length; y++){                //Печать инициированных массивов для проверки, чтобы
            for (int x = 0; x < array1[y].length; x++) {        //проверяющему легче было выбрать вариант для проверки
                System.out.printf("|%s ", array1[y][x]);
            }
            System.out.println();
        }
        System.out.println();

        for (int y = 0; y < array2.length; y++){
            for (int x = 0; x < array2[y].length; x++) {
                System.out.printf("|%s ", array2[y][x]);
            }
            System.out.println();
        }
        System.out.println();

        for (int y = 0; y < array3.length; y++){
            for (int x = 0; x < array3[y].length; x++) {
                System.out.printf("|%s ", array3[y][x]);
            }
            System.out.println();
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter array number (1, 2 or 3) >>>");
            numVariant = scanner.nextInt();

            if (numVariant == 3) {
                try {
                    myArrayParseMethod(array3);
                    } catch(MyArraySizeException e) {
                        System.out.println(e);
                    }
            } else if(numVariant == 2) {
                try {
                    myArrayParseMethod(array2);
                } catch(MyArrayDataException | MyArraySizeException e){
                    System.out.println(e);
                    }
            } else if(numVariant == 1) {
                try {
                    myArrayParseMethod(array1);
                } catch(MyArraySizeException | MyArrayDataException e) {    // Это для чистоты эксперимента
                    System.out.println(e);
                    }
                }

            if (!exitCondition()) {
                System.out.print("Good bye!");
                break;
            }
        }
    }


     public static void myArrayParseMethod(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        for (int y = 0; y < array.length; y++) {
            if (array.length > 4) {
                throw new MyArraySizeException("Size Y more than 4");
            }
            for (int x = 0; x < array[y].length; x++) {
                if (array[y].length > 4) {
                    throw new MyArraySizeException("Size X more than 4");
                }
                try {
                    sum += Integer.parseInt(array[y][x].trim());
                }catch (NumberFormatException e) {
                    System.out.printf("Can't parse String %s into Integer in the [%d][%d] cell\n", array[y][x], y, x);
                    return;
                }
            }
        }
            System.out.printf("The sum of all cells in the array is %d\n", sum);
     }

    public static boolean exitCondition(){
        Scanner scanner = new Scanner(System.in);
        String s;
        char a;
        for(;;){
            System.out.print("Продолжить? [y/n]:");
            s = scanner.nextLine();
            a = s.charAt(0);
            if(a == 'y')return true;
            else if(a == 'n')return false;
            System.out.print('\r');
        }
    }
}

