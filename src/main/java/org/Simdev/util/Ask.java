package org.Simdev.util;

import java.util.Scanner;

public class Ask {
    public static boolean run(String message) {
        Scanner scan = new Scanner(System.in);

        System.out.print(ColorText.text(message, "yellow", "none", true) + " " + ColorText.text("[Y/N] ", "white", "none", false) + " : ");
        String input = scan.nextLine();
        return input.equalsIgnoreCase("Y");
    }
}