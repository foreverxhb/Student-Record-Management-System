import java.util.Scanner;

public class Main{
    public static void main (String[]args){
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);

        manager.loadFromFile(); //load existing data

        while(true)
        {
            System.out.println("\n1. Add Student \n2. View Student \n3. Search \n4. Update Student \n5. Delete Student \n6. Sort By Name \n7. Exit");         
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                System.out.println("Enter Student ID, Name, Age, and Marks below - ");

                int id= 0;
                String name = "";
                int age= 0;
                double marks = 0;

                boolean validInput = false; //flag to manage re-prompting
                try{
                    //valid ID input
                    while(!validInput){
                        System.out.print("Enter Student ID (positive integer): ");

                        //check if input is a valid integer
                        if(sc.hasNextInt()){
                            id = sc.nextInt();

                            if(id>0){
                                //check is ID is +ve and check for dup ID after we have valid input
                                if(manager.isDuplicateID(id)){
                                    System.out.println("Error: Duplicate ID detected. Please try again!");
                                }
                                else{
                                    validInput = true; //valid ID and no dup
                                }
                            }
                            else{
                                System.out.println("ID must be a positive integer, Try again!");
                            }
                        }
                        else{
                            System.out.println("Invalid input. Please enter a valid integer for ID.");
                            sc.next(); //clear invalid input
                        }
                    }
                    //consume the newline left by sc.next()
                    sc.nextLine();

                    //validate name input
                    validInput = false;
                    while (!validInput){
                        System.out.print("Enter Student FirstName: ");
                        name = sc.nextLine();
                        if(!name.trim().isEmpty() && Main.containsAlphabet(name)){
                            validInput = true; // Valid name
                        }
                        else{
                            System.out.println("Something wrong, Check your name and Try again!");
                        }
                    }
                    //validate Age input
                    validInput = false;
                    while(!validInput){
                        System.out.print("Enter Student Age (integer between 1 and 120): ");
                        if(sc.hasNextInt()){
                            age = sc.nextInt();
                            if(age>0 && age<=120){
                                validInput = true; //valid age
                            }
                            else{
                                System.out.println("Age must be between 1 and 120, Try Again!");
                            }
                        }
                        else{
                            System.out.println("Invalid input, Please enter a valid integer for Age.");
                            sc.next(); //clear invalid input
                        }
                    }
                    //validate marks input 
                    validInput = false;
                    while(!validInput){
                        System.out.print("Enter Student Marks: ");
                        if(sc.hasNextDouble()){
                            marks = sc.nextDouble();
                            if(marks >= 0 && marks <=100){
                                validInput = true; //valid marks
                            }
                            else{
                                 System.out.print("Marks must be between 0 to 100. Try again!");
                            }
                        }else{
                             System.out.print("Invalid input, Please enter a valid input for marks.\n");
                             sc.next(); //clear invalid input
                        }
                    }

                    //Once all inputs are valid, create student and add to the manager
                    Student s = new Student(id, name, age, marks);
                    manager.addStudent(s);

                }catch (Exception e){
                    System.out.println("An unexpected error occurred: "+ e.getMessage());
                    sc.nextLine(); //clear the scanner buffer
                }
                break;
                case 2: //view all students
                manager.viewStudents();
                break;

                case 3: //search for a student
                System.out.print("Enter Student ID to search: ");
                if (sc.hasNextInt()) {
                int searchId = sc.nextInt();
                Student foundStudent = manager.searchStudentById(searchId);
                if(foundStudent != null){
                    System.out.println("Student found: "+foundStudent);
                }else{
                    System.out.println("No student found with ID: "+searchId);
                }
               }else {
                    System.out.println("---Invalid input! Please enter a valid numeric Student ID.---");
                    sc.next(); // consume the invalid input to prevent an infinite loop
                 }
                break;

                case 4: //update student details
                System.out.print("Enter Student ID to update: ");
                int updateId = sc.nextInt();
                System.out.print("Enter new name: ");
                String updateName = sc.next();
                System.out.print("Enter new age: ");
                int updateAge = sc.nextInt();
                System.out.print("Enter new marks: ");
                double updateMarks = sc.nextDouble();
                boolean updateStatus = manager.updateStudent(updateId, updateName, updateAge, updateMarks);
                if(updateStatus){
                     System.out.print("\n---Student updated successfully!---\n");
                }
                else{
                     System.out.print("Update failed, Student with ID not found.");
                }
                break;

                case 5: //delete a student
                 System.out.print("Enter Student ID to delete: ");
                 int deleteId = sc.nextInt();
                 boolean deleteStatus = manager.deleteStudent(deleteId);
                 if(deleteStatus){
                     System.out.print("\n---Student deleted successfully!---\n");
                 }
                 else{
                     System.out.print("\n---Deletion failed, Student with ID not found!---\n");
                 }
                 break;

                 case 6://sort students by name
                 manager.sortByName();
                 System.out.println("Students sorted by name.");
                 break;

                 case 7:
                System.out.println("Exiting... Goodbye! :)");
                sc.close();
                return;
            }
        }
    }
    public static boolean containsAlphabet(String str){
        if(str == null || str.isEmpty()){
            return false;
        }
        for(char ch : str.toCharArray()){
            if(Character.isLetter(ch)){
                return true;
            }
        }
        return false;
    }
}