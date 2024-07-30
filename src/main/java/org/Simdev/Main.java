package org.Simdev;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.Simdev.util.PrintMessage;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        String os_pr = System.getProperty("os.name").toLowerCase();
        String os;
        if (os_pr.contains("win")) {
            os = "windows";
        } else {
            os = "unix";
        }

        JSONParser parser = new JSONParser();
        List<Object> history = new ArrayList<>();
        String current_dir = System.getProperty("user.dir");
        String data_dir = current_dir + File.separator + "data";
        File data_dir_f = new File(data_dir);
        File history_f = new File(data_dir + File.separator + "projects.dat");

        try {
            if (!data_dir_f.exists() && !data_dir_f.mkdirs()) {
                throw new IOException();
            }

            if (!history_f.exists()) {
                if (!history_f.createNewFile()) {
                    throw new IOException();
                }

                FileWriter writer = new FileWriter(history_f);
                writer.write("[]");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(PrintMessage.get("알 수 없는 오류가 발생했습니다. (IOException)", "error"));
            System.exit(-1);
        }

        try {
            FileReader reader = new FileReader(history_f);
            JSONArray array = (JSONArray)parser.parse(reader);
            history.addAll(array);
        } catch (IOException e) {
            System.out.println(PrintMessage.get("알 수 없는 오류가 발생했습니다. (IOException)", "error"));
            System.exit(-1);
        } catch (ParseException e) {
            System.out.println(PrintMessage.get("알 수 없는 오류가 발생했습니다. (ParseException)", "error"));
            System.exit(-1);
        }

        Simdev cls = new Simdev();
        cls.run(os, history);
    }
}