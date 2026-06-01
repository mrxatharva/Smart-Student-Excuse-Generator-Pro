import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.TitledBorder;

// ===== Student Class =====
class Student {
    String name;
    int roll;
    String department;

    Student(String n, int r, String d) {
        name = n;
        roll = r;
        department = d;
    }
}

// ===== Main Class (AWT + Swing Hybrid) =====
public class SmartStudentExcuseGeneratorPro extends Frame implements ActionListener {

    JTextField nameField, rollField, deptField, attendanceField;
    JTextArea outputArea, customExcuseArea;
    JComboBox<String> categoryChoice, teacherChoice;
    JButton generateBtn, customBtn, attendanceBtn, clearBtn, exitBtn;

    Random random = new Random();
    Student student;

    String late[] = {"Bus was late", "Train got cancelled", "Traffic jam occurred"};
    String absent[] = {"Health issue", "Family emergency", "Medical appointment"};
    String assignment[] = {"Laptop crashed", "File got corrupted", "Power failure occurred"};

    String lateReasons[] = {"due to heavy traffic", "due to rain", "due to transport delay"};
    String absentReasons[] = {"due to fever", "due to family problem", "due to hospital visit"};
    String assignmentReasons[] = {"due to power cut", "due to system error", "due to internet issue"};

    SmartStudentExcuseGeneratorPro() {

        setTitle("Smart Student Excuse Generator - Professional Edition");
        setSize(1000, 700);
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 45)); // Dark base theme

        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 18);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 17);

        // ===== TOP PANEL (Student Info) =====
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 15, 15));
        topPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.CYAN, 2), 
                "Student Information", TitledBorder.LEFT, TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 20), Color.WHITE));
        topPanel.setBackground(new Color(0, 102, 153)); // Blue Theme

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(labelFont);
        nameField = new JTextField();
        nameField.setFont(fieldFont);
        nameField.setBackground(new Color(230, 245, 255));

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setForeground(Color.WHITE);
        rollLabel.setFont(labelFont);
        rollField = new JTextField();
        rollField.setFont(fieldFont);
        rollField.setBackground(new Color(230, 245, 255));

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setForeground(Color.WHITE);
        deptLabel.setFont(labelFont);
        deptField = new JTextField();
        deptField.setFont(fieldFont);
        deptField.setBackground(new Color(230, 245, 255));

        JLabel teacherLabel = new JLabel("Teacher Type:");
        teacherLabel.setForeground(Color.WHITE);
        teacherLabel.setFont(labelFont);
        teacherChoice = new JComboBox<>(new String[]{"Strict", "Cool", "Sleepy"});
        teacherChoice.setFont(fieldFont);
        teacherChoice.setBackground(Color.WHITE);

        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(rollLabel);
        topPanel.add(rollField);
        topPanel.add(deptLabel);
        topPanel.add(deptField);
        topPanel.add(teacherLabel);
        topPanel.add(teacherChoice);

        add(topPanel, BorderLayout.NORTH);

        // ===== LEFT PANEL (Controls) =====
        JPanel leftPanel = new JPanel(new GridLayout(8, 1, 15, 15));
        leftPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN, 2), 
                "Controls", TitledBorder.LEFT, TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 20), Color.WHITE));
        leftPanel.setBackground(new Color(0, 153, 136)); // Teal Theme

        JLabel catLabel = new JLabel("Excuse Category:");
        catLabel.setForeground(Color.WHITE);
        catLabel.setFont(labelFont);

        categoryChoice = new JComboBox<>(new String[]{"Late", "Absent", "Assignment"});
        categoryChoice.setFont(fieldFont);

        generateBtn = new JButton("Generate Excuse");
        customBtn = new JButton("Use Custom Excuse");
        attendanceBtn = new JButton("Check Attendance");
        clearBtn = new JButton("Clear Output");
        exitBtn = new JButton("Exit");

        generateBtn.setFont(btnFont);
        customBtn.setFont(btnFont);
        attendanceBtn.setFont(btnFont);
        clearBtn.setFont(btnFont);
        exitBtn.setFont(btnFont);

        generateBtn.setBackground(new Color(255, 193, 7));
        customBtn.setBackground(new Color(255, 87, 34));
        attendanceBtn.setBackground(new Color(76, 175, 80));
        clearBtn.setBackground(new Color(33, 150, 243));
        exitBtn.setBackground(new Color(244, 67, 54));

        JLabel customLabel = new JLabel("Custom Excuse:");
        customLabel.setForeground(Color.WHITE);
        customLabel.setFont(labelFont);

        customExcuseArea = new JTextArea(3, 20);
        customExcuseArea.setFont(fieldFont);
        customExcuseArea.setBackground(new Color(240, 255, 255));

        JLabel attLabel = new JLabel("Attendance %:");
        attLabel.setForeground(Color.WHITE);
        attLabel.setFont(labelFont);

        attendanceField = new JTextField();
        attendanceField.setFont(fieldFont);

        leftPanel.add(catLabel);
        leftPanel.add(categoryChoice);
        leftPanel.add(generateBtn);
        leftPanel.add(customBtn);
        leftPanel.add(customLabel);
        leftPanel.add(new JScrollPane(customExcuseArea));
        leftPanel.add(attLabel);
        leftPanel.add(attendanceField);

        add(leftPanel, BorderLayout.WEST);

        // ===== CENTER PANEL (LARGE OUTPUT AREA) =====
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 2), 
                "Generated Excuse Report (Large Output Area)", 
                TitledBorder.LEFT, TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 22), Color.BLACK));
        centerPanel.setBackground(new Color(220, 255, 220)); // Light Green

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.BOLD, 22)); // VERY LARGE FONT
        outputArea.setBackground(new Color(245, 255, 245));
        outputArea.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ===== BOTTOM PANEL (Buttons) =====
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(63, 81, 181)); // Indigo Theme
        bottomPanel.add(attendanceBtn);
        bottomPanel.add(clearBtn);
        bottomPanel.add(exitBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Window Close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    void initializeListeners() {
        generateBtn.addActionListener(this);
        customBtn.addActionListener(this);
        attendanceBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        exitBtn.addActionListener(this);
    }

    String generateExcuse(int cat) {
        String base = "", reason = "";
        switch (cat) {
            case 0 -> {
                base = late[random.nextInt(late.length)];
                reason = lateReasons[random.nextInt(lateReasons.length)];
            }
            case 1 -> {
                base = absent[random.nextInt(absent.length)];
                reason = absentReasons[random.nextInt(absentReasons.length)];
            }
            case 2 -> {
                base = assignment[random.nextInt(assignment.length)];
                reason = assignmentReasons[random.nextInt(assignmentReasons.length)];
            }
            default -> {}
        }
        return base + " " + reason;
    }

    int believability() {
        return random.nextInt(101);
    }

    String teacherReaction(String type) {
        if (type.equalsIgnoreCase("Strict"))
            return "Come with parents tomorrow!";
        else if (type.equalsIgnoreCase("Cool"))
            return "Okay, submit next time.";
        else
            return "Hmm... take your seat.";
    }

    String parentMode(String excuse) {
        return "Due to unavoidable circumstances, " + excuse.toLowerCase() + ".";
    }

    void displayOutput(String excuse) {
        String teacher = (String) teacherChoice.getSelectedItem();
        outputArea.setText(
                """
                ================ SMART STUDENT EXCUSE REPORT ================
                
                Student Name : """ + student.name + """
                
                Roll Number  : """ + student.roll + """
                
                Department   : """ + student.department + """
                
                
                Excuse Statement:
                """ + excuse + """
                
                
                Believability Score : """ + believability() + """
                %
                Teacher Reaction    : """ + teacherReaction(teacher) + """
                
                
                Parent Mode Version:
                """ + parentMode(excuse) + """
                
                =============================================================="""
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            student = new Student(
                    nameField.getText(),
                    Integer.parseInt(rollField.getText()),
                    deptField.getText()
            );

            if (e.getSource() == generateBtn) {
                String excuse = generateExcuse(categoryChoice.getSelectedIndex());
                displayOutput(excuse);
            } 
            else if (e.getSource() == customBtn) {
                displayOutput(customExcuseArea.getText());
            } 
            else if (e.getSource() == attendanceBtn) {
                double att = Double.parseDouble(attendanceField.getText());
                if (att >= 75)
                    outputArea.setText("Attendance Status: ALLOWED 😎");
                else
                    outputArea.setText("Attendance Status: DETAINED ⚠");
            } 
            else if (e.getSource() == clearBtn) {
                outputArea.setText("");
            } 
            else if (e.getSource() == exitBtn) {
                System.exit(0);
            }
        } catch (NumberFormatException | NullPointerException ex) {
            outputArea.setText("⚠ Input Error! Please enter valid details.");
        }
    }

    public static void main(String[] args) {
        SmartStudentExcuseGeneratorPro frame = new SmartStudentExcuseGeneratorPro();
        frame.initializeListeners();
    }
}