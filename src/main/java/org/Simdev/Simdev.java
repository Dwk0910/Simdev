package org.Simdev;

import java.util.List;
import java.util.Scanner;

import org.Simdev.util.ColorText;
import org.Simdev.util.MiniUtils;
import org.Simdev.util.PrintMessage;

public class Simdev {
    public static String version = "Release 2.0.0";
    public static Scanner scan = new Scanner(System.in);
    public void run(String OS, List<Object> history) {
        do {
            System.out.println("===== " +
                    ColorText.text("SIMDEV", "b-green", "none", true) +
                    ColorText.text(" - ", "gray", "none", false) +
                    ColorText.text("Simple Development Tool", "cyan", "none", true) +
                    " ====="
            );
            System.out.println();
            System.out.println(ColorText.text(version, "yellow", "none", true));
            System.out.println("Current System : " + ColorText.text(OS, "cyan", "none", true));
            System.out.println();

            // menus
            System.out.println(ColorText.text("- ", "white", "none", true) + ColorText.text("메뉴를 선택해주세요", "magenta", "none", true));
            System.out.println(ColorText.text("1) ", "blue", "none", true) + ColorText.text("최근 프로젝트 선택 및 열기", "white", "none", false));
            System.out.println(ColorText.text("2) ", "blue", "none", true) + ColorText.text("프로젝트 찾아 열기", "white", "none", false));
            System.out.println(ColorText.text("3) ", "blue", "none", true) + ColorText.text("새로운 프로젝트 만들기", "white", "none", false));
            System.out.println(ColorText.text("4) ", "blue", "none", true) + ColorText.text("Simdev 종료", "white", "none", false));
            System.out.print(ColorText.text(" : ", "yellow", "none", true));

            String select = scan.nextLine();
            MiniUtils.clearConsole();
            switch (select) {
                case "1" -> {
                    /*
                    About history object (JSONObject) :

                    {
                        "name": "projectname",
                        "last-open": "0000-00-00",
                        "location": "/~~~/~~~/~~~/$projectname.devinf"
                    }

                     */
                    // TODO : make printing recent projects and selecting system
                }

                case "2" -> {
                    // TODO : make opening system (Open Finder/File-Manager required)
                }

                case "3" -> {
                    // TODO : make making system (Imagine FileTree before develop!)
                }

                case "4" -> {
                    if (MiniUtils.ask("Simdev를 종료하시겠습니까?")) {
                        System.out.println(PrintMessage.get("Simdev를 종료합니다...", "info"));
                        System.exit(0);
                    } else MiniUtils.clearConsole();
                }

                default -> {
                    MiniUtils.clearConsole();
                    System.out.println(PrintMessage.get("잘못된 메뉴 선택입니다", "error"));
                }
            }
        } while (true);
    }
}