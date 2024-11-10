
public class Student {
    // Declare the attributes of the Student class
    private String ID;
    private String FirstName;
    private String LastName;
    private String Gender;
    private Double GPA;
    private Integer Level;
    private String Address;

    // Default constructor
    public Student() {
        this.ID = "";
        this.FirstName = "";
        this.LastName = "";
        this.Gender = "";
        this.GPA = 0.0;
        this.Level = 0;
        this.Address = "";
    }

    // Parameterized constructor
    public Student(String ID, String FirstName, String LastName, String Gender, Double GPA, Integer Level, String Address) {
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Gender = Gender;
        this.GPA = GPA;
        this.Level = Level;
        this.Address = Address;
    }

    // Getters
    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public Double getGPA() {
        return GPA;
    }

    public Integer getLevel() {
        return Level;
    }

    public String getAddress() {
        return Address;
    }

    // Override the toString() method to return a string representation of the Student object
    @Override
    public String toString() {
        return "<" + ID + ", " + FirstName + ", " + LastName + ", " + Gender + ", " + GPA + ", " + Level + ", " + Address + ">";
    }
}