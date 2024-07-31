package org.Simdev;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.Simdev.util.PrintMessage;
import org.Simdev.util.MiniUtils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        String os_pr = System.getProperty("os.name").toLowerCase();
        String os;

        if (os_pr.contains("win")) os = "windows";
        else os = "unix";

        JSONParser parser = new JSONParser();
        List<Object> history = new ArrayList<>();
        String current_dir = System.getProperty("user.dir");
        String data_dir = current_dir + File.separator + "data";
        File data_dir_f = new File(data_dir);
        File history_f = new File(data_dir + File.separator + "projects.dat");

        String filename = history_f.getName();

        try {
            if (!data_dir_f.exists() && !data_dir_f.mkdirs()) throw new IOException();
            if (!history_f.exists()) {
                if (!history_f.createNewFile()) throw new IOException();

                FileWriter writer = new FileWriter(history_f);
                writer.write("[]");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            PrintMessage.Crash("0001A, 0001C", filename);
        }

        try {
            FileReader reader = new FileReader(history_f);
            JSONArray array = (JSONArray) parser.parse(reader);
            history.addAll(array);
        } catch (IOException | ParseException e) {
            PrintMessage.Crash(e.getClass().getName().split("\\.")[e.getClass().getName().split("\\.").length - 1].equalsIgnoreCase("IOException") ? "0001B" : "0001D", filename);
        }

        System.out.println(PrintMessage.get("파일 검사가 완료되었습니다.", "info"));
        MiniUtils.clearConsole();

        Simdev cls = new Simdev();
        cls.run(os, history);
    }
}