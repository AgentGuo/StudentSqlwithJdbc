package jdbc;
import java.util.Scanner;
public class Jdbc {
    public static void main(String[] args) {
        MysqlClass mysql = new MysqlClass();
        Scanner scanner = new Scanner(System.in);
        int op = 0;
        showTable();
        while(op != -1){
            op = scanner.nextInt();
            switch (op){
                case -1:
                    break;
                case 0:
                    showTable();
                    break;
                case 1:
                    System.out.println("**新生入学信息增加**");
                    mysql.addStudent();
                    break;
                case 2:
                    System.out.println("**学生信息删除**");
                    mysql.deleteStudent();
                    break;
                case 3:
                    System.out.println("**查询所有学生信息**");
                    mysql.selectAlLStudent();
                    break;
                case 4:
                    System.out.println("**学生信息修改**");
                    mysql.updateStudent();
                    break;
                case 5:
                    System.out.println("**增加新课程**");
                    mysql.addCourse();
                    break;
                case 6:
                    System.out.println("**删除没有选课的课程信息**");
                    mysql.deleteNullCourse();
                    break;
                case 7:
                    System.out.println("**查询课程信息**");
                    mysql.selectAlLCourse();
                    break;
                case 8:
                    System.out.println("**修改课程信息**");
                    mysql.updateCourse();
                    break;
                case 9:
                    System.out.println("**录入学生成绩**");
                    mysql.addSC();
                    break;
                case 10:
                    System.out.println("**删除学生成绩**");
                    mysql.deleteSC();
                    break;
                case 11:
                    System.out.println("**查询所有学生成绩**");
                    mysql.selectAllSC();
                    break;
                case 12:
                    System.out.println("**修改学生成绩**");
                    mysql.updateSC();
                    break;
                case 13:
                    System.out.println("**院系学生成绩统计**");
                    mysql.statisticalSC();
                    break;
                case 14:
                    System.out.println("**学生成绩排名**");
                    mysql.studentRank();
                    break;
                case 15:
                    System.out.println("**查询学生基本信息和选课信息**");
                    mysql.studentInfo();
            }
        }

    }
    public static void showTable(){
        System.out.println("请选择你需要的功能（输入数字选择操作）");
        System.out.println("--------------------------------------");
        System.out.println("**学生类**");
        System.out.println("1.新生入学信息增加  2.学生信息删除  3.查询所有学生信息  4.学生信息修改");
        System.out.println("**课程类**");
        System.out.println("5.增加新课程  6.删除没有选课的课程信息  7.查询课程信息  8.修改课程信息");
        System.out.println("**学生成绩类**");
        System.out.println("9.录入学生成绩  10.删除学生成绩  11.查询所有学生成绩  12.修改学生成绩");
        System.out.println("**其他**");
        System.out.println("13.院系学生成绩统计  14.学生成绩排名  15.查询学生基本信息和选课信息  0.查看菜单");
        System.out.println("--------------------------------------");
    }
}
