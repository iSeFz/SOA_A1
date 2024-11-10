import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {

    // Search for student by GPA or FirstName
    public Student findStudent(String searchValue) throws Exception {
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Declare the Document object to be used to create the XML file
        Document document;

        // Declare the root element <University>
        Element university;

        // File object to check for the existence of the file
        File inputFile = new File("University.xml");

        // Create a new xml file or parse an existing one
        if (inputFile.exists()) {
            document = builder.parse(inputFile);
            university = document.getDocumentElement();
        } else {
            System.out.println("File does not exist!");
            return null;
        }

        // Get the list of all <Student> elements
        NodeList students = university.getElementsByTagName("Student");

        // Iterate over all elements inside the <University> element
        for (int i = 0; i < students.getLength(); i++) {
            Node student = students.item(i);
            Element element = (Element) student;
            // If the search value matches the FirstName or GPA of the student
            if (element.getElementsByTagName("FirstName").item(0).getTextContent().equals(searchValue) ||
                    element.getElementsByTagName("GPA").item(0).getTextContent().equals(searchValue)) {
                // Return the student object
                return (new Student(
                        student.getAttributes().getNamedItem("ID").getNodeValue(),
                        element.getElementsByTagName("FirstName").item(0).getTextContent(),
                        element.getElementsByTagName("LastName").item(0).getTextContent(),
                        element.getElementsByTagName("Gender").item(0).getTextContent(),
                        Double.parseDouble(element.getElementsByTagName("GPA").item(0).getTextContent()),
                        Integer.parseInt(element.getElementsByTagName("Level").item(0).getTextContent()),
                        element.getElementsByTagName("Address").item(0).getTextContent()));
            }
        }
        return null;
    }
}
