package samples;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Student {
    // Private member variables
    private String name;
    private int age;
    private String studentId;
    private String major;

    // Constructor to initialize the Student object
    public Student(String name, int age, String studentId, String major) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.major = major;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age > 0) {  // Validate that age is positive
            this.age = age;
        }
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // Method to convert the Student object to JSON and save to a file
    public void saveToJsonFile(String filename) {
        // Create a Gson object
        Gson gson = new Gson();

        // Convert the Student object to a JSON string
        String jsonString = gson.toJson(this);

        // Write the JSON string to a file
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonString);
            System.out.println("JSON file created: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static Student loadFromJsonFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, Student.class);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Main method for testing the class
    public static void main(String[] args) {
//        // Creating a Student object
//        Student student1 = new Student("Alice Johnson", 20, "S12345", "Computer Science");
//
//        // Save the student details to a JSON file
//        student1.saveToJsonFile("student.json");
    	  // Load the student details from the JSON file
        Student student = Student.loadFromJsonFile("student.json");

        if (student != null) {
            // Print the student details
            System.out.println("Loaded Student Details:");
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Major: " + student.getMajor());

            // Modify and save back to JSON
            student.setName("Alice Smith");
            student.saveToJsonFile("student_updated.json");
    	
    }
}
}
