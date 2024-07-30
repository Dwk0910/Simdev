package org.Simdev.util;

public class PrintMessage {
    public static String get(String message, String type) {
        String color = "";

        switch (type) {
            case "info" -> color = "green";
            case "warning" -> color = "yellow";
            case "error" -> color = "red";
        }

        return ColorText.text("[", "gray", "none", false) +
                ColorText.text("Simdev", "cyan", "none", true) +
                ColorText.text("/", "orange", "none", false) +
                ColorText.text(type.toUpperCase(), color, "none", true) +
                ColorText.text("]", "gray", "none", false) + " : " +
                ColorText.text(message, "white", "none", true);
    }
}