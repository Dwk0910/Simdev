package org.Simdev;

import org.Simdev.object.Project;

import org.Simdev.util.ColorText;
import org.Simdev.util.MiniUtils;
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
            "package": "abc.def",
            "classPath": [".", "$dir/library"],
            "version": "0.0.0",
            "libraries": [
                "abc.def.hij.kln:0.0.0",
                "mop.qrs.tuv.wxy:0.0.0"
            ],
            "sets_local_lib_name": "library (default:lib)"
            "sets_jar_name": "JARFILE_$version" <- 생성되는 .jar 파일의 이름
        }

        sets_[SETTING NAME] : 추가적인 유저 설정. 커스텀 설정과 같은 개념이다. (필수 작성 요소가 아님)
        libraries 안에는 gradle implementation과 똑같은 내용으로 작성.
         */

        MiniUtils.clearConsole();
        System.out.print(ColorText.text("- ", "gray", "none", false));
        System.out.println(ColorText.text(project.getName(), "green", "none", true));

    }
}