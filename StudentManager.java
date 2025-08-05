import java.io.*;
import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>(); //list of students managed by this class
    private static final String FILE_NAME = "students.dat"; //file name used for storing student data

    //adds a new student to list.
    public void addStudent(Student s){
        students.add(s);
        System.out.println("\n---Student added successfully!---");
    }

    //method to check for duplicate student id
    public boolean isDuplicateID(int id){
        for(Student student : students){
            if(student.getID()== id){
                return true; //duplicate found, exit method
            }
        }
        return false; //no dups, then return false after full iteration
    }

    //display the list of students in the console
    public void viewStudents(){
        for (Student s : students){
            System.out.println(s);
        }
    }

    //saves the list of students to a file using serialization.
    //the file used for storage is determined by the constant -FILE_NAME.
    public void saveToFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(students);
            System.out.println("Data saved to file.");
        } 
        catch (Exception e){
            System.out.println("Error saving to file: "+ e.getMessage());
        }
    }

    //loads the list of students from a file using deserialization
    //if the file does not exist, it initializes the list as empty
    public void loadFromFile(){
        File file = new File(FILE_NAME);
        if(file.exists()){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
                students = (ArrayList<Student>) ois.readObject();
                System.out.println("Date loaded from file.");
            } catch (Exception e){
                System.out.println("Error loading from file: "+e.getMessage());
            }
        }else{
            System.out.println("File not exist, Starting refresh...");
        }
    }
    
    //searches for a student by their unique id
    public Student searchStudentById(int id){
        for(Student s : students){
            if(s.getID()==id){
                return s;
            }
        }
        return null;
    }

    //updates the info of a student with given id
    public boolean updateStudent(int id, String name, int age, double marks){
        Student s = searchStudentById(id);
        if(s != null){
            s.SetID(id);
            s.SetName(name);
            s.SetAge(age);
            s.SetMarks(marks);

            return true; //indicate success
        }
        else{
            //student not found
            System.out.println("Student not found.");
        }
        return false; //indicate failure
    }

    //deletes a student with given id from the list
    public boolean deleteStudent(int id){
        Student s = searchStudentById(id);
        if(s != null){
            students.remove(s);
            return true; //deletion success
        }
        else{
           return false; //student not found
        }
    }

    //sorts the list of students by their names in alphabetical order
    public void sortByName(){
        students.sort((Student s1, Student s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Sorted by Name (A-Z): ");
        viewStudents();
    }

    //sorts the list of students by their marks in ascending order
    public void sortByMarks(){
        students.sort((Student s1, Student s2)-> Double.compare(s1.getMarks(),s2.getMarks()));
        System.out.println("Sorted by marks (highest to lowest): ");
        viewStudents();
    }

}
