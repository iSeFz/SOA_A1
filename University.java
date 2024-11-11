import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// University class to store students data
public class University {
    // Declare students array to store students data
    private List<Student> students;

    // Global Scanner to be used in all methods
    Scanner scanner;

    // Data file name
    private final String fileName;

    // Default constructor
    public University() {
        this.students = new ArrayList<Student>();
        this.scanner = new Scanner(System.in);
        this.fileName = "University.xml";
    }

    // Main method
    public static void main(String[] args) throws Exception {
        System.out.println("\tWelcome to the University!");
        University university = new University();
        // Create or open the file
        university.createFileIfNotExist();
        // Search for student by GPA or FirstName or Delete student by ID
        university.askUser();
        // Close the scanner
        university.close();
    }

    public void createFileIfNotExist() throws Exception {
        // Check if the file exists
        File inputFile = new File(fileName);
        if (inputFile.exists()) return;

        // Get students data from the user
        addStudents();
        // Store the added students data to an XML file
        XMLWriter xmlWriter = new XMLWriter(fileName);
        xmlWriter.storeStudentsToXML(students);
    }

    public void askUser() throws Exception {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Search for a student");
            System.out.println("2. Delete a student");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    searchStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Close the scanner
    public void close() {
        scanner.close();
    }

    // Take students data as input from the user and create a Student objects and add them to the list.
    public void addStudents() {
        // Ask the user to enter the number of students s/he wants to store data about
        System.out.print("Enter the number of students to store data about: ");
        Integer numStudents = scanner.nextInt();
        // Consume the newline character to prevent it from being read by the next scanner.nextLine() call
        scanner.nextLine();
        // Ask the user to enter the data for each student
        while(numStudents > 0) {
            System.out.println("\t\tEnter new student data!");
            System.out.print("Enter Student ID: ");
            String studID = scanner.nextLine();
            System.out.print("Enter First Name: ");
            String studFirstName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String studLastName = scanner.nextLine();
            System.out.print("Enter Gender: ");
            String studGender = scanner.nextLine();
            System.out.print("Enter GPA: ");
            Double studGPA = scanner.nextDouble();
            System.out.print("Enter Level: ");
            Integer studLevel = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Address: ");
            String studAddress = scanner.nextLine();
            Student student = new Student(studID, studFirstName, studLastName, studGender, studGPA, studLevel, studAddress);
            students.add(student);
            numStudents--;
        }
    }

    // Search for student with GPA or FirstName
    public void searchStudent() throws Exception {
        // Ask the user to enter the GPA or FirstName of the student to search
        System.out.print("Enter the GPA or FirstName of the student to search: ");
        String searchValue = scanner.nextLine();
        // Search for the student with the given GPA or FirstName
        XMLParser xmlParser = new XMLParser(fileName);
        Student student = xmlParser.findStudent(searchValue);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found!");
        }
    }

    // Delete student by ID
    public void deleteStudent() throws Exception {
        // Ask the user to enter the ID of the student to delete
        System.out.print("Enter the ID of the student to delete: ");
        String studentId = scanner.nextLine();
        // Delete the student with the given ID
        XMLDeleter xmlDeleter = new XMLDeleter(fileName);
        if (xmlDeleter.deleteStudentById(studentId)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }
}
