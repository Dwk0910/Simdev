package org.Simdev;

import org.Simdev.object.Project;

public class ProjectManager {
    public static void run(Project project) {
        try {
            System.out.println("ProjectManager runs perfectly!");
            System.out.println("name : " + project.getName());
            System.out.println("date : " + project.getDate());
            System.out.println("directory : " + project.getDirectory());
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}