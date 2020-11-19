package jdbc;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    private String name;
    private String dept;
    private int grade;
    public Student(String name, String dept, int grade){
        this.name = name;
        this.dept = dept;
        this.grade = grade;
    }
    public int getGrade(){
        return this.grade;
    }
    public String getName(){
        return this.name;
    }
    public String getDept(){
        return this.dept;
    }
    @Override
    public int compareTo(Student s1){
        return Double.compare(s1.grade, this.grade);
    }
    public String toString(){
        return this.dept + "\t\t" + this.name +  "\t\t" + this.grade;
    }
}
