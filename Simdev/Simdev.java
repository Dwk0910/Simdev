package Simdev;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Simdev extends JFrame {
    public static String version = "1.0.0";

    // other
    public JButton generate_btn = new JButton("프로젝트 생성");
    public Simdev() {
        setTitle("SIMDEV DEVTOOL V.1.0.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setLocation(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - (int) getSize().getWidth() / 2,
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - (int) getSize().getHeight() / 2
        ); // ALIGN: CENTER

        this.run();

        setVisible(true);
    }

    public void run() {
        JLabel req1 = new JLabel("false"); // envSetting 확인 (두 개중 적어도 한 개는 체크 필요)
        JLabel req2 = new JLabel("false"); // projectName 확인 (입력해야 함, 금지문자)
        JLabel req3 = new JLabel("false"); // mainclass 확인 (입력해야 함, 금지문자)
        JLabel req4 = new JLabel("false"); // projectPath 확인 (null은 안됨)

        /* ==================== [ INIT AREA ] ==================== */
        // ICON
        JPanel iconPanel = new JPanel();
        iconPanel.setPreferredSize(new Dimension(700, 70));
        iconPanel.setLayout(null);
        JLabel icon = new JLabel("<html><body><h1 style=\"Font-size: 40; line-height: 10;\">SIMDEV</h1></body></html>'");
        JLabel icon2 = new JLabel("<html><body><h4>JAVA PROJECT GENERATOR</h4></body></html>");

        // SET OPTION
        icon.setFont(new Font(icon.getFont().getFontName(), icon.getFont().getStyle(), 50));
        icon2.setForeground(new Color(164, 112, 75));
        icon.setBounds(20, 10, 790, 40);
        icon2.setBounds(21, 45, 790, 20);

        iconPanel.add(icon);
        iconPanel.add(icon2);
        add(iconPanel);

        // VERSION
        JPanel version_panel = new JPanel();
        version_panel.setPreferredSize(new Dimension(90, 70));
        version_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel version_label = new JLabel(version);
        version_label.setPreferredSize(new Dimension(70, 50));
        version_panel.add(version_label);

        add(version_panel);

        /* ==================== [ MAIN AREA ] ==================== */
        JPanel project_init_panel = new JPanel();
        project_init_panel.setLayout(null);
        project_init_panel.setPreferredSize(new Dimension(790, 120));

        // Project Name
        JLabel projectname_label = new JLabel("프로젝트 이름");
        JTextField projectname_textfield = new JTextField();
        projectname_label.setBounds(20, 0, 790, 20);
        projectname_textfield.setBounds(17, 21, 750, 20);

        project_init_panel.add(projectname_label);
        project_init_panel.add(projectname_textfield);

        // Project Path
        JLabel projectpath_label = new JLabel("프로젝트 경로");
        JButton projectpath_btn = new JButton("경로 선택");
        JTextField projectpath_textfield = new JTextField();
        projectpath_label.setBounds(20, 55, 650, 20);
        projectpath_textfield.setBounds(17, 75, 350, 20);
        projectpath_btn.setBounds(365, 75, 100, 20);

        project_init_panel.add(projectpath_label);
        project_init_panel.add(projectpath_textfield);
        project_init_panel.add(projectpath_btn);

        // MainClass Name
        JLabel mainclassname_label = new JLabel("Main Class 이름");
        JTextField mainclassname_textfield = new JTextField();
        mainclassname_label.setBounds(500, 55, 100, 20);
        mainclassname_textfield.setBounds(497, 75, 270, 20);

        project_init_panel.add(mainclassname_label);
        project_init_panel.add(mainclassname_textfield);

        add(project_init_panel);

        /* ==================== [ ADDITIONAL SETTING ] ==================== */
        JPanel additional_setting_panel = new JPanel();
        additional_setting_panel.setLayout(null);
        additional_setting_panel.setPreferredSize(new Dimension(790, 110));

        // Additional Setting
        JLabel additional_setting_label = new JLabel("프로젝트 추가 설정");
        additional_setting_label.setBounds(20, 0, 790, 20);
        additional_setting_panel.add(additional_setting_label);

        // Environment Setting
        JLabel envsetting_label = new JLabel("개발 환경");
        JCheckBox envsetting_windows = new JCheckBox("Windows");
        JCheckBox envsetting_unix = new JCheckBox("unix");
        JLabel envsetting_windows_label = new JLabel("Windows로 개발 (run, build, makeJar) 모두 bat파일로 생성");
        JLabel envsetting_unix_label = new JLabel("Unix시스템으로 개발 (run, build, makeJar) 모두 sh파일로 생성");

        envsetting_label.setBounds(40, 23, 790, 20);
        envsetting_windows.setBounds(34, 43, 25, 20);
        envsetting_windows_label.setBounds(63, 43, 765, 20);
        envsetting_unix.setBounds(34, 65, 25, 20);
        envsetting_unix_label.setBounds(63, 65, 765, 20);

        additional_setting_panel.add(envsetting_label);
        additional_setting_panel.add(envsetting_windows);
        additional_setting_panel.add(envsetting_windows_label);
        additional_setting_panel.add(envsetting_unix);
        additional_setting_panel.add(envsetting_unix_label);

        add(additional_setting_panel);

        /* ==================== [ STATUS LABEL ] ==================== */
        JPanel status_panel = new JPanel();
        status_panel.setLayout(null);
        status_panel.setPreferredSize(new Dimension(790, 100));

        JLabel status_label = new JLabel("프로젝트 생성 가능 여부");
        JLabel status = new JLabel("");

        status.setBounds(20, 35, 790, 40);
        status_label.setBounds(20, 0, 790, 20);

        // CheckBox Event
        ActionListener CheckboxListener = (e) -> {
            if (envsetting_windows.isSelected() || envsetting_unix.isSelected()) {
                req1.setText("true");
            } else {
                req1.setText("false");
            }

            if (IsValid(projectname_textfield.getText())) {
                req2.setText("true");
            } else {
                req2.setText("false");
            }
            if (IsValid(mainclassname_textfield.getText())) {
                req3.setText("true");
            } else {
                req3.setText("false");
            }
            if (!projectpath_textfield.getText().equals("")) {
                req4.setText("true");
            } else {
                req4.setText("false");
            }

            if (req1.getText().equals("true") & req2.getText().equals("true") & req3.getText().equals("true") & req4.getText().equals("true")) {
                status.setText("프로젝트 생성이 가능합니다");
                status.setForeground(Color.BLUE);
                status.setFont(new Font(status.getFont().getFontName(), Font.BOLD, status.getFont().getSize()));
                generate_btn.setEnabled(true);
            } else {
                // req1 (ProjectName 입력하지 않음 확인)
                status.setText("<html><body>프로젝트 생성이 불가합니다 : 입력하지 않았거나 선택하지 않은 항목이 있는지 확인하세요.<br/>또는 각 항목에 입력할 수 없는 문자가 입력되었는지 확인하세요.</body></html>");
                status.setForeground(Color.RED);
                status.setFont(new Font(status.getFont().getFontName(), Font.BOLD, status.getFont().getSize()));
                generate_btn.setEnabled(false);
            }
        };

        // Event
        projectpath_btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showDialog(this, null);
            File dir = jfc.getSelectedFile();
            if (dir == null) { projectpath_textfield.setText(""); } else {
                projectpath_textfield.setText(dir.getPath());
                req4.setText("true");
            }

            if (envsetting_windows.isSelected() || envsetting_unix.isSelected()) {
                req1.setText("true");
            } else {
                req1.setText("false");
            }

            if (IsValid(projectname_textfield.getText())) {
                req2.setText("true");
            } else {
                req2.setText("false");
            }
            if (IsValid(mainclassname_textfield.getText())) {
                req3.setText("true");
            } else {
                req3.setText("false");
            }
            if (!projectpath_textfield.getText().equals("")) {
                req4.setText("true");
            } else {
                req4.setText("false");
            }

            if (req1.getText().equals("true") & req2.getText().equals("true") & req3.getText().equals("true") & req4.getText().equals("true")) {
                status.setText("프로젝트 생성이 가능합니다");
                status.setForeground(Color.BLUE);
                status.setFont(new Font(status.getFont().getFontName(), Font.BOLD, status.getFont().getSize()));
                generate_btn.setEnabled(true);
            } else {
                // req1 (ProjectName 입력하지 않음 확인)
                status.setText("<html><body>프로젝트 생성이 불가합니다 : 입력하지 않았거나 선택하지 않은 항목이 있는지 확인하세요.<br/>또는 각 항목에 입력할 수 없는 문자가 입력되었는지 확인하세요.</body></html>");
                status.setForeground(Color.RED);
                status.setFont(new Font(status.getFont().getFontName(), Font.BOLD, status.getFont().getSize()));
                generate_btn.setEnabled(false);
            }
        });

        // TextField Event
        KeyListener listener = new KeyListener() {
            @Override
            public void keyReleased(KeyEvent event) {
                if (envsetting_windows.isSelected() || envsetting_unix.isSelected()) {
                    req1.setText("true");
                } else {
                    req1.setText("false");
                }

                if (IsValid(projectname_textfield.getText())) {
                    req2.setText("true");
                } else {
                    req2.setText("false");
                }
                if (IsValid(mainclassname_textfield.getText())) {
                    req3.setText("true");
                } else {
                    req3.setText("false");
                }
                if (!projectpath_textfield.getText().equals("")) {
                    req4.setText("true");
                } else {
                    req4.setText("false");
                }

                if (req1.getText().equals("true") & req2.getText().equals("true") & req3.getText().equals("true") & req4.getText().equals("true")) {
                    status.setText("프로젝트 생성이 가능합니다");
                    status.setForeground(Color.BLUE);
                    status.setFont(new Font(status.getFont().getFontName(), Font.BOLD, status.getFont().getSize()));
                    generate_btn.setEnabled(true);
                } else {
                    status.setText("<html><body>프로젝트 생성이 불가합니다 : 입력하지 않았거나 선택하지 않은 항목이 있는지 확인하세요.<br/>또는 각 항목에 입력할 수 없는 문자가 입력되었는지 확인하세요.</body></html>");
                    status.setForeground(Color.RED);
                    status.setFont(new Font(status.getFont().getFontName(), Font.BOLD, status.getFont().getSize()));
                    generate_btn.setEnabled(false);
                }
            }

            @Override
            public void keyPressed(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }
        };

        // Add EventListener
        projectname_textfield.addKeyListener(listener);
        mainclassname_textfield.addKeyListener(listener);
        projectpath_textfield.addKeyListener(listener);

        envsetting_windows.addActionListener(CheckboxListener);
        envsetting_unix.addActionListener(CheckboxListener);

        status_panel.add(status);
        status_panel.add(status_label);
        add(status_panel);

        /* ==================== [ FINAL ] ==================== */
        JPanel final_panel = new JPanel();
        final_panel.setLayout(null);
        final_panel.setPreferredSize(new Dimension(790, 200));

        JLabel license_label = new JLabel("Made by Dwk0910");
        license_label.setForeground(Color.GRAY);
        license_label.setFont(new Font(license_label.getFont().getFontName(), Font.ITALIC, license_label.getFont().getSize()));

        license_label.setBounds(20, 20, 340, 200);
        generate_btn.setBounds(670, 90, 110, 40);

        final_panel.add(license_label);
        final_panel.add(generate_btn);
        add(final_panel);

        generate_btn.addActionListener((e) -> {
            int ans = JOptionPane.showConfirmDialog(this, "'" + projectname_textfield.getText() + "' 프로젝트가 생성됩니다. 계속하시겠습니까?", "프로젝트 생성", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ans == 0) {
                if (req1.getText().equals("false") || req2.getText().equals("false") || req3.getText().equals("false") || req4.getText().equals("false")) {
                    JOptionPane.showMessageDialog(this, "<html><body>프로젝트 생성에 실패하였습니다.<br/>입력하지 않았거나 선택하지 않은 항목이 있는지 확인하세요.<br/>또는 각 항목에 입력할 수 없는 문자가 입력되었는지 확인하세요.</body></html>", "프로젝트 생성 실패", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.setVisible(false);
                    new Generate(projectname_textfield.getText(), mainclassname_textfield.getText(), projectpath_textfield.getText(), envsetting_windows.isSelected(), envsetting_unix.isSelected());
                    this.dispose();
                }
            }
        });
    }

    public static boolean IsValid(String str) {
        if (str.equals("")) { return false; }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) { return false; }
        }
        return true;
    }
}