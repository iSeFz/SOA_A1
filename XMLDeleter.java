import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLDeleter {
    String filePath = "University.xml";
        // Delete student by ID
    public boolean deleteStudentById(String studentId) throws Exception {
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Declare the Document object to be used to create the XML file
        Document document;

        // Declare the root element <University>
        Element university;

        // File object to check for the existence of the file
        File inputFile = new File(filePath);

        // Create a new xml file or parse an existing one
        if (inputFile.exists()) {
            document = builder.parse(inputFile);
            university = document.getDocumentElement();
        } else {
            System.out.println("File does not exist!");
            return false;
        }

        // Get the list of all <Student> elements
        NodeList students = university.getElementsByTagName("Student");

        // Iterate over all elements inside the <University> element
        for (int i = 0; i < students.getLength(); i++) {
            Node student = students.item(i);
            // If the student ID matches the given ID
            if (student.getAttributes().getNamedItem("ID").getNodeValue().equals(studentId)) {
                // Remove the student element
                university.removeChild(student);
                // Write the changes to the XML file
                writeChangesToXML(document);
                return true;
            }
        }
        return false;
    }

    // Write the changes to the XML file
    public void writeChangesToXML(Document document) throws Exception {
        // Create a Transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Write the changes to the XML file
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}
