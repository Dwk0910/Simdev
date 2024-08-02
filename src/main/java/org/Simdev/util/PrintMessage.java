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

    /*
    [ERRORCODE]

    - TYPE D (-edly, Simdev가 Crash될 정도의 치명적인 오류) -

    D-0001류 : (IOException, ParseException) cause에 발생파일 명시하기!
    ㄴ D-0001A : write 오류
    ㄴ D-0001B : read 오류
    ㄴ D-0001C : create 오류
    ㄴ D-0001D : (ParseException) 파일 손상으로 간주

    - TYPE G (-eneral, 일반적인 경고식 오류) -
    G-0001류 (TYPE G) : Simdev 프로젝트 설정파일을 찾을 수 없음.
    ㄴ G-0001A : devinf.dat
    ㄴ * 추가예정 *

    */

    public static void Error(int type, String errorcode, String cause) {
        switch (type) {
            case 0 -> {
                System.out.println(PrintMessage.get("치명적인 오류가 발생하여 종료합니다. (Code " + ColorText.text("D-" + errorcode, "yellow", "none", true) + ColorText.text(") : ", "white", "none", true) + cause, "error"));
                System.exit(-1);
            }

            case 1 -> {
                try {
                    System.out.println(PrintMessage.get("오류가 발생했습니다. (Code " + ColorText.text("G-" + errorcode, "yellow", "none", true) + ColorText.text(") : ", "white", "none", true) + cause, "error"));
                    System.out.print(
                            ColorText.text("[ ", "gray", "none", false) +
                                    ColorText.text("이전 화면으로 돌아가기까지.. ", "green", "none", false) +
                                    ColorText.text("6", "red", "none", true) +
                                    ColorText.text(" ]", "gray", "none", false)
                    );

                    for (int i = 5; i >= 0; i--) {
                        Thread.sleep(1000);
                        System.out.print("\b\b\b" +
                                ColorText.text(Integer.toString(i), "red", "none", true) +
                                ColorText.text(" ]", "gray", "none", false)
                        );
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}