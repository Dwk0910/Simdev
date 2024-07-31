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

    public static void Crash(String errorcode, String cause) {
        /*
        [ERRORCODE]

        0001류 : (IOException, ParseException) cause에 발생파일 명시하기!
        ㄴ 0001A : write 오류
        ㄴ 0001B : read 오류
        ㄴ 0001C : create 오류
        ㄴ 0001D : (ParseException) 파일 손상으로 간주

        - 추가예정 -
        */

        System.out.println(PrintMessage.get("치명적인 오류가 발생하여 종료합니다. (Code " + ColorText.text(errorcode, "yellow", "none", true) + ColorText.text(") : ", "white", "none", true) + cause, "error"));
        System.exit(-1);
    }
}