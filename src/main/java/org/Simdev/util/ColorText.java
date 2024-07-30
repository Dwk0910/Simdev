package org.Simdev.util;

// JANSI Import

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class ColorText {
    public static String text(String text, String color, String background, boolean isBold) {
        AnsiConsole.systemInstall();

        Ansi result = new Ansi();
        switch (color) {
            case "black" -> result = Ansi.ansi().fgBlack();
            case "red" -> result = Ansi.ansi().fgRed();
            case "green" -> result = Ansi.ansi().fgGreen();
            case "yellow" -> result = Ansi.ansi().fgYellow();
            case "blue" -> result = Ansi.ansi().fgBlue();
            case "magenta" -> result = Ansi.ansi().fgMagenta();
            case "cyan" -> result = Ansi.ansi().fgCyan();
            case "b-black" -> result = Ansi.ansi().fgBrightBlack();
            case "b-red" -> result = Ansi.ansi().fgBrightRed();
            case "b-green" -> result = Ansi.ansi().fgBrightGreen();
            case "b-yellow" -> result = Ansi.ansi().fgBrightYellow();
            case "b-blue" -> result = Ansi.ansi().fgBrightBlue();
            case "b-magenta" -> result = Ansi.ansi().fgBrightMagenta();
            case "b-cyan" -> result = Ansi.ansi().fgBrightCyan();
        }

        if (background.length() != 0) {
            switch (background) {
                case "red" -> result = result.bgRed();
                case "green" -> result = result.bgGreen();
                case "yellow" -> result = result.bgYellow();
                case "magenta" -> result = result.bgMagenta();
                case "cyan" -> result = result.bgCyan();
                case "b-red" -> result = result.bgBrightRed();
                case "b-green" -> result = result.bgBrightGreen();
                case "b-yellow" -> result = result.bgBrightYellow();
                case "b-magenta" -> result = result.bgBrightMagenta();
                case "b-cyan" -> result = result.bgBrightCyan();
            }
        }

        if (isBold) result = result.bold();

        result = result.a(text).reset();
        return result.toString();
    }
}