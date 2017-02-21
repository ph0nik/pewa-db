package com.pewa;

import org.apache.commons.lang.math.NumberUtils;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class UserInterface {
    private static Scanner userInput = new Scanner(System.in);
    private static String userInputString ="";
    private static int option;

    private UserInterface() {
    }

    private static int pickElement(Map foundElements) {
        String elementString;
        int elementInt = -1;

        do {
            System.out.print("Wybierz pozycję: ");
            elementString = userInput.next();
            if(!NumberUtils.isNumber(elementString)) {
                System.out.println("To nie jest liczba.");
            }
            else {
                if (elementString.contains(".")) {
                    elementInt = (int) Double.parseDouble(elementString);
                }
                else {
                    elementInt = Integer.parseInt(elementString);
                }
                if(!foundElements.containsKey(elementInt)) {
                    System.out.println("Nie ma pozycji o takim numerze, wybierz ponownie");
                }
            }
        } while (!foundElements.containsKey(elementInt));

        return elementInt;
    }

    static void search() {
        Map<Integer, String> mainOptions = new TreeMap();
        mainOptions.put(1, "Szukaj książki.");
        mainOptions.put(2, "Szukaj filmu.");
        mainOptions.put(3, "Szukaj serialu.");
        mainOptions.forEach((x,y) -> System.out.println(x + ". " + y));

        option = pickElement(mainOptions);

        if (userInputString.isEmpty()) {
            System.out.print("Szukaj tytułu: ");
            userInputString = userInput.next();
        }

/*
        if(option == 1) {
//            com.pewa.book.BookSearch.bSearch(userInputString);
        } else if(option == 2) {
//            com.pewa.movie.MovieParseToObject.mSearch(userInputString);
        } else if(option == 3) {
//            com.pewa.tv.TvShowParseToObject.sTvShow(userInputString);
        }*/



    }

    static void closeInput() {
        userInput.close();
    }
}
