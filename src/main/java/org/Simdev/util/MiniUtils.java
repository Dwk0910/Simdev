package org.Simdev.util;

import java.util.Scanner;

public class MiniUtils {
    public static boolean ask(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.print(ColorText.text(message, "yellow", "none", true) + " " + ColorText.text("[Y/N] ", "white", "none", false) + " : ");
        String input = scan.nextLine();
        return input.equalsIgnoreCase("Y");
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}
