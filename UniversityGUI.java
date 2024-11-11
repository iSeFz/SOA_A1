import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniversityGUI {
    private University university;

    public UniversityGUI() {
        university = new University();
        createMainFrame();
    }

    private void createMainFrame() {
        JFrame mainFrame = new JFrame("University Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAddStudentFrame();
            }
        });

        JButton manageStudentButton = new JButton("Manage Students");
        manageStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createManageStudentFrame();
            }
        });

        panel.add(addStudentButton);
        panel.add(manageStudentButton);

        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private void createAddStudentFrame() {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setSize(400, 400);
        addStudentFrame.setLayout(new GridLayout(9, 2));

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField();
        JLabel gpaLabel = new JLabel("GPA:");
        JTextField gpaField = new JTextField();
        JLabel levelLabel = new JLabel("Level:");
        JTextField levelField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Student student = new Student(
                            idField.getText(),
                            firstNameField.getText(),
                            lastNameField.getText(),
                            genderField.getText(),
                            Double.parseDouble(gpaField.getText()),
                            Integer.parseInt(levelField.getText()),
                            addressField.getText()
                    );
                    university.students.add(student);
                    XMLWriter xmlWriter = new XMLWriter(university.fileName);
                    xmlWriter.storeStudentsToXML(university.students);
                    JOptionPane.showMessageDialog(addStudentFrame, "Student added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(addStudentFrame, "Error adding student: " + ex.getMessage());
                }
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentFrame.dispose();
            }
        });

        addStudentFrame.add(idLabel);
        addStudentFrame.add(idField);
        addStudentFrame.add(firstNameLabel);
        addStudentFrame.add(firstNameField);
        addStudentFrame.add(lastNameLabel);
        addStudentFrame.add(lastNameField);
        addStudentFrame.add(genderLabel);
        addStudentFrame.add(genderField);
        addStudentFrame.add(gpaLabel);
        addStudentFrame.add(gpaField);
        addStudentFrame.add(levelLabel);
        addStudentFrame.add(levelField);
        addStudentFrame.add(addressLabel);
        addStudentFrame.add(addressField);
        addStudentFrame.add(addButton);
        addStudentFrame.add(stopButton);

        addStudentFrame.setVisible(true);
    }

    private void createManageStudentFrame() {
        JFrame manageStudentFrame = new JFrame("Manage Students");
        manageStudentFrame.setSize(400, 200);
        manageStudentFrame.setLayout(new GridLayout(2, 1));

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSearchStudentFrame();
            }
        });

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDeleteStudentFrame();
            }
        });

        manageStudentFrame.add(searchButton);
        manageStudentFrame.add(deleteButton);

        manageStudentFrame.setVisible(true);
    }

    private void createSearchStudentFrame() {
        JFrame searchStudentFrame = new JFrame("Search Student");
        searchStudentFrame.setSize(400, 200);
        searchStudentFrame.setLayout(new GridLayout(3, 2));

        JLabel searchLabel = new JLabel("Enter GPA or First Name:");
        JTextField searchField = new JTextField();
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XMLParser xmlParser = new XMLParser(university.fileName);
                    Student student = xmlParser.findStudent(searchField.getText());
                    if (student != null) {
                        resultArea.setText("Student found: " + student);
                    } else {
                        resultArea.setText("Student not found!");
                    }
                } catch (Exception ex) {
                    resultArea.setText("Error searching student: " + ex.getMessage());
                }
            }
        });

        searchStudentFrame.add(searchLabel);
        searchStudentFrame.add(searchField);
        searchStudentFrame.add(new JLabel()); // Empty label for spacing
        searchStudentFrame.add(searchButton);
        searchStudentFrame.add(new JLabel()); // Empty label for spacing
        searchStudentFrame.add(resultArea);

        searchStudentFrame.setVisible(true);
    }

    private void createDeleteStudentFrame() {
        JFrame deleteStudentFrame = new JFrame("Delete Student");
        deleteStudentFrame.setSize(400, 200);
        deleteStudentFrame.setLayout(new GridLayout(3, 2));

        JLabel deleteLabel = new JLabel("Enter Student ID:");
        JTextField deleteField = new JTextField();
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XMLDeleter xmlDeleter = new XMLDeleter(university.fileName);
                    if (xmlDeleter.deleteStudentById(deleteField.getText())) {
                        resultArea.setText("Student deleted successfully!");
                    } else {
                        resultArea.setText("Student not found!");
                    }
                } catch (Exception ex) {
                    resultArea.setText("Error deleting student: " + ex.getMessage());
                }
            }
        });

        deleteStudentFrame.add(deleteLabel);
        deleteStudentFrame.add(deleteField);
        deleteStudentFrame.add(new JLabel()); // Empty label for spacing
        deleteStudentFrame.add(deleteButton);
        deleteStudentFrame.add(new JLabel()); // Empty label for spacing
        deleteStudentFrame.add(resultArea);

        deleteStudentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UniversityGUI();
            }
        });
    }
}