package org.Simdev;

import org.Simdev.object.Project;

import org.Simdev.util.PrintMessage;

import java.io.File;

public class ProjectManager {
    public static void run(Project project) {
        System.out.println(PrintMessage.get(project.getName() + " - 잠시만 기다려 주세요. 파일 정보를 읽고 있습니다...", "info"));

        File infFile = new File(project.getLocation());
        if (!infFile.exists()) PrintMessage.Error(1, "0001A", "devinf.dat");

        /* about devinf.dat
        {
            "name": "project name",
            "mainClass": "abc.def.Main",
            "classPath": ".",
            "version": "0.0.0",
            "implementation": [
                "abc.def.hij.kln:0.0.0",
                "mop.qrs.tuv.wxy:0.0.0"
            ],
            "sets_local_lib_name": "library (default:lib)"
        }
         */
    }
}