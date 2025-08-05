import java.io.Serializable;

public class Student implements Serializable{
    private int id;
    private String name;
    private int age;
    private double marks;

    public Student (int id, String name, int age, double marks){
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks; 
    }
    
    public String getName(){
        return name;
    }
    public void SetName(String name){
        this.name = name;
    }

    public int getID(){
        return id;
    }
    public void SetID(int id){
        this.id = id;
    }

    public int getAge(){
        return age;
    }
    public void SetAge(int age){
        this.age = age;
    }

    public double getMarks(){
        return marks;
    }
    public void SetMarks(double marks){
        this.marks = marks;
    }

    @Override
    public String toString(){
        return "ID: "+id+ ", Name: "+name+", Age: "+age+", Marks: "+marks;
    }

}
