package forJar;

import java.io.*;
public class GenerateManifest {
    public static void main(String[] args) {
        try {
            File targetDir = new File(System.getProperty("user.dir") + "/../lib");
            File file = new File("MANIFEST.MF");
            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);

            String projectName = "Simdev";
            String mainClassName = "Main";

            String[] liblist = targetDir.list();
            writer.println("Main-Class: " + projectName + "." + mainClassName);

            if (liblist == null) { writer.flush(); System.exit(0); }
            writer.print("Class-Path: ");
            for (String lib : liblist) writer.print("lib/" + lib + " ");
            writer.println();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}