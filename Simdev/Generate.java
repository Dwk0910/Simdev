package Simdev;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Generate extends JFrame {
    public Generate(String projectName, String mainClassName, String path, boolean windows, boolean unix) {
        /* ==================== [ RUN AREA ] ==================== */
        setTitle("프로젝트 생성중");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setLocation(
                (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - (int) getSize().getWidth() / 2,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - (int) getSize().getHeight() / 2
        ); // ALIGN: CENTER

        File target = new File(path);

        // VALIDATE
        if ((
                !Simdev.IsValid(projectName) |
                !Simdev.IsValid(mainClassName) |
                !(windows | unix)
        ) | (
                path.isEmpty() |
                        !target.exists()
        )
        ) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패하였습니다.<br/>입력하지 않았거나 선택하지 않은 항목이 있는지 확인하세요.<br/>또는 각 항목에 입력할 수 없는 문자가 입력되었는지 확인하세요.</body></html>", "프로젝트 생성 실패", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        String[] target_listfiles = target.list();
        if (target_listfiles != null) {
            if (target_listfiles.length != 0) {
                if (!target_listfiles[0].equals(".DS_Store")) {
                    JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패하였습니다.<br/>폴더가 비어있지 않습니다.</body></html>", "프로젝트 생성 실패", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }

        // GENERATE
            /*
              TODO: 파일 추가, 파일 수정시스템 제작.
              파일 트리 :
              $ProjectName |
                           | classes      | empty |
                           | comp         | win -> build.bat, makeJar.bat, run.bat / unix -> build.sh, makeJar.sh, run.sh |
                           | forJar       | MANIFEST.MF
                           | jarFiles     | empty |
                           | lib          | empty |
                           | $ProjectName | $MainclassName.java |

                           forJar/MANIFEST.MF:
                                Main-Class: $ProjectName.$MainclassName (아래에 빈 줄 추가 필요)

                           comp/build.bat:
                                javac -cp ./../lib/*; ./../$ProjectName/*.java -encoding utf-8 -d ./../classes
                                pause

                           comp/makeJar.bat:
                                javac -cp ./../lib/*; ./../$ProjectName/*.java -encoding utf-8 -d ./../classes && cd ./../classes && jar -cvmf ./../forJar/MANIFEST.MF ./../jarFiles/$ProjectName.jar ./$ProjectName/*
                                pause

                           comp/run.bat:
                                javac -cp ./../lib/*; ./../$ProjectName/*.java -encoding utf-8 -d ./../classes && cd ./../classes && java $ProjectName.$MainclassName

                           or comp/build.sh:
                                javac -cp ./../lib/*: ./../$ProjectName/*.java -encoding utf-8 -d ./../classes

                           comp/makeJar.sh:
                                ./build.sh
                                cd ./../classes/ || echo "ERROR: Command 'cd'"
                                jar -cvmf ./../forJar/MANIFEST.MF ./../jarFiles/$ProjectName.jar ./$ProjectName/*

                           comp/run.sh:
                                ./build.sh
                                cd ./../classes/ || echo "ERROR: Command 'cd'"
                                java -cp "./$ProjectName:./../lib/*:" $ProjectName.$MainclassName

                           $ProjectName/$MainclassName.java:
                                public class $MainclassName {
                                    public static void main(String[] args) {
                                    }
                                }
             */
        // 작업현황 (Label) 출력
        JPanel statusLabelPanel = new JPanel();
        statusLabelPanel.setPreferredSize(new Dimension(590, 80));
        statusLabelPanel.setLayout(null);

        JLabel statusanoLabel = new JLabel("'" + projectName + "' 프로젝트가 생성됩니다. 잠시만 기다려 주십시오.");
        JLabel status = new JLabel("프로젝트 생성 시작중...");
        statusanoLabel.setBounds(45, 20, 590, 20);
        status.setBounds(45, 45, 590, 20);

        statusLabelPanel.add(statusanoLabel);
        statusLabelPanel.add(status);
        add(statusLabelPanel);

        // 작업현황 (백분율) 출력
        JPanel bakint_panel = new JPanel();
        bakint_panel.setPreferredSize(new Dimension(590, 20));
        bakint_panel.setLayout(null);

        JLabel bakint = new JLabel("0%");
        bakint.setBounds(500, 0, 90, 20);

        bakint_panel.add(bakint);
        add(bakint_panel);

        // 작업현황 (ProgressBar) 출력
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        progressPanel.setPreferredSize(new Dimension(590, 200));

        JProgressBar progress_statusbar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        progress_statusbar.setPreferredSize(new Dimension(400, 20));
        progress_statusbar.setValue(0);

        progressPanel.add(progress_statusbar);
        add(progressPanel);

        // 취소버튼 출력
        JPanel cancelPanel = new JPanel();
        cancelPanel.setLayout(null);
        cancelPanel.setPreferredSize(new Dimension(590, 100));

        JButton cancelButton = new JButton("취소");
        cancelButton.setBounds(500, 0, 80, 40);

        cancelButton.addActionListener(e -> {
            int ans = JOptionPane.showConfirmDialog(this, "<html><body>프로젝트가 아직 제작중입니다.<br/>지금 취소하면 프로젝트를 만들 수 없습니다.<br/>정말 계속하시겠습니까?", "프로젝트 취소", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (ans == 0) {
                System.exit(0);
            }
        });

        cancelPanel.add(cancelButton);
        add(cancelPanel);

        // START

        // Directories
        statusanoLabel.setText("폴더 생성중...");
        status.setText(projectName + "/classes");

        File dir_classes = new File(PathPlus(path, "classes"));
        if (!dir_classes.mkdir()) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 폴더를 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        bakint.setText(windows & unix ? "6%" : "8%");
        progress_statusbar.setValue(windows & unix ? 6 : 8);

        status.setText(projectName + "/comp");
        File dir_comp = new File(PathPlus(path, "comp"));
        if (!dir_comp.mkdir()) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 폴더를 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        bakint.setText(windows & unix ? "12%" : "16%");
        progress_statusbar.setValue(windows & unix ? 12 : 16);

        status.setText(projectName + "/forJar");
        File dir_forJar = new File(PathPlus(path, "forJar"));
        if (!dir_forJar.mkdir()) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 폴더를 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        bakint.setText(windows & unix ? "18%" : "24%");
        progress_statusbar.setValue(windows & unix ? 18 : 24);

        status.setText(projectName + "/jarFiles");
        File dir_jarFiles = new File(PathPlus(path, "jarFiles"));
        if (!dir_jarFiles.mkdir()) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 폴더를 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        status.setText(projectName + "/lib");
        File dir_lib = new File(PathPlus(path, "lib"));
        if (!dir_lib.mkdir()) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 폴더를 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        bakint.setText(windows & unix ? "24%" : "32%");
        progress_statusbar.setValue(windows & unix ? 24 : 32);

        status.setText(projectName + "/" + projectName);
        File dir_projectName = new File(PathPlus(path, projectName));
        if (!dir_projectName.mkdir()) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 폴더를 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        bakint.setText(windows & unix ? "30%" : "40%");
        progress_statusbar.setValue(windows & unix ? 30 : 40);

        // Files
        statusanoLabel.setText("파일 생성중...");
        ArrayList<String> todo = new ArrayList<>();

        // comp files
        File file_build_unix = new File(PathPlus(PathPlus(path, "/comp"), "/build.sh"));
        File file_makeJar_unix = new File(PathPlus(PathPlus(path, "/comp"), "/makeJar.sh"));
        File file_run_unix = new File(PathPlus(PathPlus(path, "/comp"), "/run.sh"));

        File file_build_windows = new File(PathPlus(PathPlus(path, "/comp"), "/build.bat"));
        File file_makeJar_windows = new File(PathPlus(PathPlus(path, "/comp"), "/makeJar.bat"));
        File file_run_windows = new File(PathPlus(PathPlus(path, "/comp"), "/run.bat"));

        File file_MANIFEST = new File(PathPlus(PathPlus(path, "/forJar"), "/MANIFEST.MF"));
        File file_GenerateManifest = new File(PathPlus(PathPlus(path, "/forJar"), "/GenerateManifest.java"));

        File file_mainclass = new File(PathPlus(PathPlus(path, "/" + projectName), "/" + mainClassName + ".java"));

        try {
            if (windows & unix) {
                status.setText(projectName + "/comp/build.sh");
                if (!file_build_unix.createNewFile()) { throw new IOException(); }
                bakint.setText("36%");
                progress_statusbar.setValue(36);
                todo.add("build.sh");

                status.setText(projectName + "/comp/makeJar.sh");
                if (!file_makeJar_unix.createNewFile()) { throw new IOException(); }
                bakint.setText("42%");
                progress_statusbar.setValue(42);
                todo.add("makeJar.sh");

                status.setText(projectName + "/comp/run.sh");
                if (!file_run_unix.createNewFile()) { throw new IOException(); }
                bakint.setText("48%");
                progress_statusbar.setValue(48);
                todo.add("run.sh");

                status.setText(projectName + "/comp/build.bat");
                if (!file_build_windows.createNewFile()) { throw new IOException(); }
                bakint.setText("54%");
                progress_statusbar.setValue(54);
                todo.add("build.bat");

                status.setText(projectName + "/comp/makeJar.bat");
                if (!file_makeJar_windows.createNewFile()) { throw new IOException(); }
                bakint.setText("60%");
                progress_statusbar.setValue(60);
                todo.add("makeJar.bat");

                status.setText(projectName + "/comp/run.bat");
                if (!file_run_windows.createNewFile()) { throw new IOException(); }
                bakint.setText("66%");
                progress_statusbar.setValue(66);
                todo.add("run.bat");
            } else if (windows) {
                status.setText(projectName + "/comp/build.bat");
                if (!file_build_windows.createNewFile()) { throw new IOException(); }
                bakint.setText("50%");
                progress_statusbar.setValue(50);
                todo.add("build.bat");

                status.setText(projectName + "/comp/makeJar.bat");
                if (!file_makeJar_windows.createNewFile()) { throw new IOException(); }
                bakint.setText("56%");
                progress_statusbar.setValue(56);
                todo.add("makeJar.bat");

                status.setText(projectName + "/comp/run.bat");
                if (!file_run_windows.createNewFile()) { throw new IOException(); }
                bakint.setText("64%");
                progress_statusbar.setValue(64);
                todo.add("run.bat");
            } else {
                status.setText(projectName + "/comp/build.sh");
                if (!file_build_unix.createNewFile()) { throw new IOException(); }
                bakint.setText("50%");
                progress_statusbar.setValue(50);
                todo.add("build.sh");

                status.setText(projectName + "/comp/makeJar.sh");
                if (!file_makeJar_unix.createNewFile()) { throw new IOException(); }
                bakint.setText("56%");
                progress_statusbar.setValue(56);
                todo.add("makeJar.sh");

                status.setText(projectName + "/comp/run.sh");
                if (!file_run_unix.createNewFile()) { throw new IOException(); }
                bakint.setText("64%");
                progress_statusbar.setValue(64);
                todo.add("run.sh");
            }

            status.setText(projectName + "/forJar/MANIFEST.MF");
            if (!file_MANIFEST.createNewFile()) { throw new IOException(); }
            bakint.setText("72%");
            progress_statusbar.setValue(72);

            status.setText(projectName + "/" + projectName + "/" + mainClassName + ".java");
            if (!file_mainclass.createNewFile()) { throw new IOException(); }
            bakint.setText(windows & unix ? "78%" : "83%");
            progress_statusbar.setValue(windows & unix ? 78 : 83);

            // Write
            statusanoLabel.setText("파일 쓰는중...");

            FileWriter fw;
            PrintWriter writer;
            for (String content : todo) {
                switch(content) {
                    case "build.sh" -> {
                        status.setText(projectName + "/comp/build.sh");
                        fw = new FileWriter(file_build_unix);
                        writer = new PrintWriter(fw);
                        writer.print("javac -cp ./../lib/*: ./../"+ projectName +"/*.java -encoding utf-8 -d ./../classes");
                        writer.flush();
                    }

                    case "makeJar.sh" -> {
                        status.setText(projectName + "/comp/makeJar.sh");
                        fw = new FileWriter(file_makeJar_unix);
                        writer = new PrintWriter(fw);
                        writer.println("./build.sh");
                        writer.println("if [ -n \"$(ls -A ./../lib)\" ]; then");
                        writer.println("  if [ ! -e ./../jarFiles/lib ]; then");
                        writer.println("    mkdir ./../jarFiles/lib");
                        writer.println("  fi");
                        writer.println("  cp ./../lib/* ./../jarFiles/lib");
                        writer.println("else");
                        writer.println("  if [ -e ./../jarFiles/lib ]; then");
                        writer.println("    rm -r ./../jarFiles/lib");
                        writer.println("  fi");
                        writer.println("fi");
                        writer.println("cd ./../forJar || echo \"ERROR: Command 'cd'\"");
                        writer.println("java GenerateManifest.java");
                        writer.println("cd ./../classes || echo \"ERROR: Command 'cd'\"");
                        writer.print("jar -cvmf ./../forJar/MANIFEST.MF ./../jarFiles/"+ projectName +".jar ./" + projectName + "/*");
                        writer.flush();
                    }

                    case "run.sh" -> {
                        status.setText(projectName + "/comp/run.sh");
                        fw = new FileWriter(file_run_unix);
                        writer = new PrintWriter(fw);
                        writer.println("./build.sh");
                        writer.println("cd ./../classes/ || echo \"ERROR: Command 'cd'\"");
                        writer.print("java -cp \"./"+ projectName +":./../lib/*:\" " + projectName + "." + mainClassName);
                        writer.flush();
                    }

                    case "build.bat" -> {
                        status.setText(projectName + "/comp/build.bat");
                        fw = new FileWriter(file_build_windows);
                        writer = new PrintWriter(fw);
                        writer.println("javac -cp ./../lib/*; ./../"+ projectName +"/*.java -encoding utf-8 -d ./../classes");
                        writer.print("pause");
                        writer.flush();
                    }

                    case "makeJar.bat" -> {
                        status.setText(projectName + "/comp/makeJar.bat");
                        fw = new FileWriter(file_makeJar_windows);
                        writer = new PrintWriter(fw);
                        writer.println("@echo off");
                        writer.println("set \"folder=./../lib\"");
                        writer.println();
                        writer.println("dir /b \"%folder%\" 2>nul | findstr \"^\" >nul");
                        writer.println("if not errorlevel 1 (");
                        writer.println("  if not exist \"./../jarFiles/lib\" (");
                        writer.println("    mkdir ./../jarFiles/lib");
                        writer.println("  )");
                        writer.println("  copy \"./../lib/*\" \"./../jarFiles/lib\"");
                        writer.println(") else (");
                        writer.println("  if exist \"./../jarFiles/lib\" (");
                        writer.println("    rmdir \"./../jarFiles/lib\"");
                        writer.println("  )");
                        writer.println(")");
                        writer.flush();
                    }

                    case "run.bat" -> {
                        status.setText(projectName + "/comp/run.bat");
                        fw = new FileWriter(file_run_windows);
                        writer = new PrintWriter(fw);
                        writer.println("javac -cp ./../lib/*; ./../"+ projectName +"/*.java -encoding utf-8 -d ./../classes && cd ./../classes && java -cp \"./"+ projectName +";./../lib/*;\" "+ projectName +"."+ mainClassName);
                        writer.print("pause");
                        writer.flush();
                    }

                    // GENERAL

                    default -> {
                        JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 알 수 없는 오류가 발생했습니다. Dwk0910에게 문의하십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }

                bakint.setText("99%");
                progress_statusbar.setValue(99);

                status.setText(projectName + "/" + projectName + "/" + mainClassName + ".java");
                fw = new FileWriter(file_mainclass);
                writer = new PrintWriter(fw);
                writer.println("package " + projectName + ";\n");
                writer.println("public class " + mainClassName + " {");
                writer.println("    public static void main(String[] args) {");
                writer.println("    }");
                writer.print("}");
                writer.flush();

                fw = new FileWriter(file_GenerateManifest);
                writer = new PrintWriter(fw);
                writer.println("package forJar;");
                writer.println("\nimport java.io.*;");
                writer.println("\npublic class GenerateManifest {");
                writer.println("    public static void main(String[] args) {");
                writer.println("        try {");
                writer.println("            File targetDir = new File(System.getProperty(\"user.dir\") + \"/../lib\");");
                writer.println("            File file = new File(\"MANIFEST.MF\");");
                writer.println("            FileWriter fw = new FileWriter(file);");
                writer.println("            PrintWriter writer = new PrintWriter(fw);");
                writer.println();
                writer.println("            String projectName = \"" + projectName + "\";");
                writer.println("            String mainClassName = \"" + mainClassName + "\";");
                writer.println();
                writer.println("            String[] liblist = targetDir.list();");
                writer.println("            writer.println(\"Main-Class: \" + projectName + \".\" + mainClassName);");
                writer.println();
                writer.println("            if (liblist == null) { writer.flush(); System.exit(0); }");
                writer.println("            writer.print(\"Class-Path: \");");
                writer.println("            for (String lib : liblist) writer.print(\"lib/\" + lib + \" \");");
                writer.println("            writer.println();");
                writer.println("            writer.flush();");
                writer.println("        } catch (IOException e) {");
                writer.println("            e.printStackTrace();");
                writer.println("        }");
                writer.println("    }");
                writer.println("}");
                writer.flush();

                bakint.setText("100%");
                progress_statusbar.setValue(100);

            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패했습니다 : 파일을 만들 수 없습니다<br/>프로젝트 폴더가 사용 가능한 상태인지 확인해 보십시오.</body></html>", "프로젝트 오류", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(0);
        }

        JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성을 완료하였습니다</body></html>", "프로젝트 생성 완료", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);

        setVisible(true);
    }

    public String PathPlus(String path, String plus) {
        if (path.charAt(path.length() - 1) == '/') { return path + plus; } else { return path + "/" + plus; }
    }
}
