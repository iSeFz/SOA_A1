import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// University class to store students data
public class University {
    // Declare students array to store students data
    private List<Student> students;

    // Default constructor
    public University() {
        this.students = new ArrayList<Student>();
    }

    // Main method
    public static void main(String[] args) throws Exception {
        System.out.println("\tWelcome to the University!");
        University university = new University();
        // Get students data from the user
        university.addStudents();
        // Store the added students data to an XML file
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.storeStudentsToXML(university.students);
        // Search for student by GPA or FirstName
        university.searchStudent();
    }

    // Take students data as input from the user and create a Student objects and add them to the list.
    public void addStudents() {
        // Declare a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
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
        
        scanner.close();
    }

    // Search for student with GPA or FirstName
    public void searchStudent() throws Exception {
        // Declare a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        // Ask the user to enter the GPA or FirstName of the student to search
        System.out.print("Enter the GPA or FirstName of the student to search: ");
        String searchValue = scanner.nextLine();
        scanner.close();
        // Search for the student with the given GPA or FirstName
        XMLParser xmlParser = new XMLParser();
        Student student = xmlParser.findStudent(searchValue);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found!");
        }
    }
}
