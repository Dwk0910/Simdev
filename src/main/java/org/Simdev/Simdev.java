package org.Simdev;

import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import org.Simdev.object.Project;
import org.Simdev.util.ColorText;
import org.Simdev.util.MiniUtils;
import org.Simdev.util.PrintMessage;

import org.json.simple.JSONObject;

public class Simdev {
    public static String version = "Release 2.0.0";
    public static Scanner scan = new Scanner(System.in);
    public void run(String OS, List<JSONObject> history) {
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
                    if (history.size() == 0) {
                        try {
                            System.out.println(PrintMessage.get("표시할 최근 프로젝트가 없습니다.", "error"));
                            Thread.sleep(1500);
                            MiniUtils.clearConsole();
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Print graph of projects
                    System.out.println("+------------+------------+----------------------------------------------+");
                    System.out.println("|  [Index]   |   [Date]   |                [Project Name]                |");
                    System.out.println("+------------+------------+----------------------------------------------+");

                    /* index -> 0 or 00 or 000 or 0000
                     1자릿수 : 5-6
                     2자릿수 : 5-5
                     3자릿수 : 4-5
                     4자릿수 : 4-4
                     9999 이상은 거부

                     date -> 00.00.00
                     12345678 즉, length = 8
                     "|   [Date]   |"
                       000000000111
                       123456789012
                     * 3 ~ 10 index (2-2 nullS, E) *
                     */

                    Map<Integer, JSONObject> map = new HashMap<>();
                    int i = 1;
                    for (JSONObject o : history) {
                        map.put(i, o);
                        i++;
                    }
                    do {
                        for (Integer key : map.keySet()) {
                            JSONObject value = map.get(key);
                            int nullS = 0, nullE = 0;
                            if (key.toString().length() == 1) {
                                nullS = 5;
                                nullE = 6;
                            } else if (key.toString().length() == 2) {
                                nullS = 5;
                                nullE = 5;
                            } else if (key.toString().length() == 3) {
                                nullS = 4;
                                nullE = 5;
                            } else if (key.toString().length() == 4) {
                                nullS = 4;
                                nullE = 4;
                            }

                            String oName = map.get(key).get("name").toString();
                            String name = oName.length() <= 42 ? oName : oName.substring(0, 41) + "...";

                            System.out.println("|" + " ".repeat(nullS) + key + " ".repeat(nullE) + "|" +
                                    " ".repeat(2) + map.get(key).get("date") + " ".repeat(2) + "|" +
                                    " " + name + " ".repeat(46 /* <- name 행 width */ - (1 /* <- 공백문자 */ + name.length())) + "|");
                            System.out.println("+------------+------------+----------------------------------------------+");
                        }

                        System.out.println();
                        System.out.print(ColorText.text("실행할 프로젝트의 index 를 입력해주세요 (나가려면 'q'를 입력해주세요) : ", "yellow", "none", true));

                        try {
                            String input = scan.nextLine();
                            if (input.equalsIgnoreCase("q")) {
                                MiniUtils.clearConsole();
                                break;
                            }

                            String prjName = map.get(Integer.parseInt(input)).get("name").toString();
                            String prjDate = map.get(Integer.parseInt(input)).get("date").toString();
                            String prjLocation = map.get(Integer.parseInt(input)).get("location").toString();

                            MiniUtils.clearConsole();
                            ProjectManager.run(new Project(prjName, prjDate, prjLocation));
                            MiniUtils.clearConsole();
                            break;
                        } catch (NumberFormatException | NullPointerException e) {
                            MiniUtils.clearConsole();
                            System.out.println(PrintMessage.get("잘못된 index 입력입니다.", "error"));
                            System.out.println("+------------+------------+----------------------------------------------+");
                            System.out.println("|  [Index]   |   [Date]   |                [Project Name]                |");
                            System.out.println("+------------+------------+----------------------------------------------+");
                            continue;
                        }
                    } while (true);
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