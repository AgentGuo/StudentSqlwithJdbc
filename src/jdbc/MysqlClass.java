package jdbc;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.PreparedStatement;
public class MysqlClass {
    // Connection连接对象
    private Connection con;

    /**
     * MysqlClass的初始化函数
     */
    public MysqlClass(){
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/CSEDB_U201811010";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "qwer";
        //遍历查询结果集
        try {
            //加载驱动程序,初始化给定类
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

    /**
     * 用与查询全部学生信息
     */
    public void selectAlLStudent(){
        try {
            String sql = "select * from Student";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("\t*学号*" + "\t" + "*姓名*" + "\t" + "*性别*" + "\t" + "*年龄*");
            String name= null;
            String id = null;
            String sex = null;
            String age = null;
            while(rs.next()){
                //获取sno这列数据
                id = rs.getString("Sno");
                //获取sname这列数据
                name = rs.getString("Sname");
                //获取sex这列数据
                sex = rs.getString("Ssex");
                //获取age这列数据
                age = rs.getString("Sage");
                //输出结果
                System.out.println(id + "\t" + name + "\t\t" + sex + "\t\t" + age);
            }
            System.out.println("--------------------------------------");
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 添加学生
     */
    public void addStudent(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入学生学号(9位数字)：");
            String Sno = scanner.next();
            System.out.println("请输入学生姓名：");
            String Sname = scanner.next();
            System.out.println("请输入学生性别(男/女)：");
            String Ssex = scanner.next();
            System.out.println("请输入学生年龄：");
            int Sage = scanner.nextInt();
            System.out.println("请输入学生是否有奖学金(是/否)：");
            String Scholarship = scanner.next();
            System.out.println("插入数据："+ Sno + " " + Sname +" "+ Ssex + " " + Sage + " " + Scholarship);
            String sql = "insert into Student(Sno, Sname, Ssex, Sage, Scholarship) values(?,?,?,?,?)";
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Sno);
            ps.setString(2, Sname);
            ps.setString(3, Ssex);
            ps.setInt(4, Sage);
            ps.setString(5, Scholarship);
            //String sql = "insert into Student(Sno, Sname, Ssex, Sage, Scholarship) values('212345678', '郭', '男', 20, '是')";

//            Statement statement = con.createStatement();
            int insertCount = ps.executeUpdate();
            System.out.println("成功插入"+insertCount+"条数据");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 更新学生信息
     */
    public void updateStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生学号(9位数字):");
        String Sno = scanner.next();
        try{
            System.out.println("该学生当前的信息为：");
            String sql = "select * from Student where Sno = " + Sno;
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("\t*学号*" + "\t" + "*姓名*" + "\t" + "*性别*" + "\t" + "*年龄*");
            String name= null;
            String id = null;
            String sex = null;
            String age = null;
            while(rs.next()){
                //获取sno这列数据
                id = rs.getString("Sno");
                //获取sname这列数据
                name = rs.getString("Sname");
                //获取sex这列数据
                sex = rs.getString("Ssex");
                //获取age这列数据
                age = rs.getString("Sage");
                //输出结果
                System.out.println(id + "\t" + name + "\t\t" + sex + "\t\t" + age);
            }
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入学生新的信息：");
        System.out.print("请输入学生新的姓名：");
        String Sname = scanner.next();
        System.out.print("请输入学生新的性别：");
        String Ssex = scanner.next();
        System.out.print("请输入学生新的年龄：");
        int Sage = scanner.nextInt();
        System.out.print("请输入学生是否获得奖学金：");
        String Scholarship = scanner.next();
        try{
            String sql = "update Student set Sname = ?, Ssex = ?, Sage = ?, Scholarship = ? where Sno = " + Sno;
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Sname);
            ps.setString(2, Ssex);
            ps.setInt(3, Sage);
            ps.setString(4, Scholarship);
            int flag = ps.executeUpdate();
            if(flag > 0)
                System.out.println("修改成功");
            else
                System.out.println("修改失败");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("--------------------------------------");
    }

    /**
     * 删除学生
     */
    public void deleteStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生学号(9位数字):");
        String Sno = scanner.next();
        try{
            String sql = "delete from Student where Sno = " + Sno;
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            int flag = ps.executeUpdate();
            if(flag > 0)
                System.out.println("删除成功");
            else
                System.out.println("删除失败");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 添加课程
     */
    public void addCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入课程号：");
        String Cno = scanner.next();
        System.out.println("请输入课程名：");
        String Cname = scanner.next();
        System.out.println("请输入先修课程号：");
        String Cpno = scanner.next();
        System.out.println("请输入课程学分：");
        int Ccredit = scanner.nextInt();
        try{
            System.out.println("插入数据："+ Cno + " " + Cname +" "+ Cpno + " " + Ccredit);
            String sql = "insert into Course(Cno, Cname, Cpno, Ccredit) values(?,?,?,?)";
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Cno);
            ps.setString(2, Cname);
            ps.setString(3, Cpno);
            ps.setInt(4, Ccredit);
            int insertCount = ps.executeUpdate();
            System.out.println("成功插入"+insertCount+"条数据");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 查询所有课程
     */
    public void selectAlLCourse(){
        try {
            String sql = "select * from Course";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(String.format("%-9s%-8s%-8s%-8s", "*课程号*" ,"*课程名*", "*先修课程号*", "*课程学分*"));
            String Cno= null;
            String Cname = null;
            String Cpno = null;
            String Ccredit = null;
            while(rs.next()){
                Cname = rs.getString("Cname");
                Cno = rs.getString("Cno");
                Cpno = rs.getString("Cpno");
                Ccredit = rs.getString("Ccredit");
                //输出结果
                System.out.println(String.format("%-9s%-12s%-10s%-8s", Cno, Cname, Cpno, Ccredit));
            }
            System.out.println("--------------------------------------");
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除没用选课的课程
     */
    public void deleteNullCourse(){
        try{
            String sql = "delete from Course where Cno not in (select distinct Cno from SC)";
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            int insertCount = ps.executeUpdate();
            System.out.println("成功删除"+insertCount+"条数据");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 修改课程信息
     */
    public void updateCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要修改的课程号：");
        String CnoInput = scanner.next();
        try{
            String sql = "select * from Course where Cno = " + CnoInput;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println(String.format("%-9s%-8s%-8s%-8s", "*课程号*" ,"*课程名*", "*先修课程号*", "*课程学分*"));
            String Cno= null;
            String Cname = null;
            String Cpno = null;
            String Ccredit = null;
            while(rs.next()){
                //获取sno这列数据
                Cname = rs.getString("Cname");
                //获取sname这列数据
                Cno = rs.getString("Cno");
                //获取sex这列数据
                Cpno = rs.getString("Cpno");
                //获取age这列数据
                Ccredit = rs.getString("Ccredit");
                //输出结果
                System.out.println(String.format("%-9s%-12s%-10s%-8s", Cno, Cname, Cpno, Ccredit));
            }
            System.out.println("--------------------------------------");
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("请输入修改信息");
        System.out.println("请输入课程名：");
        String Cname = scanner.next();
        System.out.println("请输入先修课程号：");
        String Cpno = scanner.next();
        System.out.println("请输入课程学分：");
        int Ccredit = scanner.nextInt();
        try{
            String sql = "update Course set Cname = ?, Cpno = ?, Ccredit = ? where Cno = " + CnoInput;
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Cname);
            ps.setString(2, Cpno);
            ps.setInt(3, Ccredit);
            int flag = ps.executeUpdate();
            if(flag > 0)
                System.out.println("修改成功");
            else
                System.out.println("修改失败");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 添加成绩信息
     */
    public void addSC(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生学号：");
        String Sno = scanner.next();
        System.out.println("请输入课程号：");
        String Cno = scanner.next();
        System.out.println("请输入成绩：");
        int Grade = scanner.nextInt();
        try{
            System.out.println("插入数据："+ Sno + " " + Cno +" "+ Grade);
            String sql = "insert into SC(Sno, Cno, Grade) values(?,?,?)";
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Sno);
            ps.setString(2, Cno);
            ps.setInt(3, Grade);
            int insertCount = ps.executeUpdate();
            System.out.println("成功插入"+insertCount+"条数据");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除成绩信息
     */
    public void deleteSC(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生学号：");
        String Sno = scanner.next();
        System.out.println("请输入课程号：");
        String Cno = scanner.next();
        try{
            String sql = "delete from SC where Sno = ? and Cno = ?";
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Sno);
            ps.setString(2, Cno);
            int flag = ps.executeUpdate();
            if(flag > 0)
                System.out.println("删除成功");
            else
                System.out.println("删除失败");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 查询所有成绩信息
     */
    public void selectAllSC(){
        try {
            String sql = "select * from SC";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(String.format("%-9s%-8s%-8s", "*学号*" ,"*课程号*", "*成绩*"));
            String Sno= null;
            String Cno = null;
            String Grade = null;
            while(rs.next()){
                Sno = rs.getString("Sno");
                Cno = rs.getString("Cno");
                Grade = rs.getString("Grade");
                //输出结果
                System.out.println(String.format("%-12s%-8s%-10s", Sno, Cno, Grade));
            }
            System.out.println("--------------------------------------");
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 修改成绩信息
     */
    public void updateSC(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生学号：");
        String Sno = scanner.next();
        System.out.println("请输入课程号：");
        String Cno = scanner.next();
        System.out.println("请输入学生新的成绩：");
        int Grade = scanner.nextInt();
        try{
            String sql = "update SC set Grade = ? where Sno = ? and Cno = ?";
            //预编译sql
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Grade);
            ps.setString(2, Sno);
            ps.setString(3, Cno);
            int flag = ps.executeUpdate();
            if(flag > 0)
                System.out.println("修改成功");
            else
                System.out.println("修改失败");
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 统计院系成绩信息
     */
    public void statisticalSC(){
        try {
            String sql = "select Sdept, avg(Grade) avgGrade, max(Grade) maxGrade, min(Grade) minGrade from (select Sdept, Grade from Student, SC where Student.Sno = SC.Sno) as deptGrade(Sdept, Grade) group by Sdept";
            String sql2 = null;
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = null;
            ResultSet rs = ps.executeQuery(sql);
            ResultSet rs2 = null;
            System.out.println(String.format("%-9s%-8s%-8s%-8s%-8s%-8s", "*院系*" ,"*平均成绩*", "*最高分*", "*最低分*", "*优秀率*", "*不及格人数*"));
            String Sdept= null;
            String avgGrade = null;
            String maxGrade = null;
            String minGrade = null;
            // 总人数
            int stuNum;
            // 优秀人数
            int excellentNum;
            // 未及格人数
            int noPassNum;
            while(rs.next()){
                Sdept = rs.getString("Sdept");
                avgGrade = rs.getString("avgGrade");
                maxGrade = rs.getString("maxGrade");
                minGrade = rs.getString("minGrade");
                //输出结果

                // 查询总人数
                sql2 = "select count(*) as count from SC, Student where Student.Sdept = ? and Student.Sno = SC.Sno";
                ps2 = con.prepareStatement(sql2);
                ps2.setString(1, Sdept);
                rs2 = ps2.executeQuery();
                rs2.next();
                stuNum = rs2.getInt("count");
                rs2.close();

                // 查询优秀人数
                sql2 = "select count(*) as count  from SC, Student where Student.Sdept = ? and Student.Sno = SC.Sno and SC.Grade >= 90";
                ps2 = con.prepareStatement(sql2);
                ps2.setString(1, Sdept);
                rs2 = ps2.executeQuery();
                rs2.next();
                excellentNum = rs2.getInt("count");
                rs2.close();

                // 查询不及格人数
                sql2 = "select count(*) as count  from SC, Student where Student.Sdept = ? and Student.Sno = SC.Sno and SC.Grade < 60";
                ps2 = con.prepareStatement(sql2);
                ps2.setString(1, Sdept);
                rs2 = ps2.executeQuery();
                rs2.next();
                noPassNum = rs2.getInt("count");
                rs2.close();

                // 查询未及格人数
                System.out.println(String.format("%-9s%-12s%-10s%-9s%-12.2f%-9s", Sdept, avgGrade, maxGrade, minGrade, (double)excellentNum/stuNum, noPassNum));
            }
            System.out.println("--------------------------------------");
            rs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 院系学生成绩排名
     */
    public void studentRank(){
        PreparedStatement ps = null;
        String sql = null;
        ResultSet rs1 = null, rs2 = null;
        String Sdept = null;
        String name = null;
        int grade;
        ArrayList<Student> stu = new ArrayList<Student>();
        try{
            sql = "select distinct Sdept from Student";
            ps = con.prepareStatement(sql);
            rs1 = ps.executeQuery();
            while(rs1.next()){
                // 清空数组
                stu.clear();
                Sdept = rs1.getString("Sdept");
                sql = "select Sname, avg(Grade) as avgGrade from (select Sname, Student.Sno, Grade from Student, SC where Student.Sno = SC.Sno and Student.Sdept = ?) as deptStudent(Sname, Sno, Grade) group by Sname";
                ps = con.prepareStatement(sql);
                ps.setString(1, Sdept);
                rs2 = ps.executeQuery();
                while(rs2.next()){
                    name = rs2.getString("Sname");
                    grade = rs2.getInt("avgGrade");
                    stu.add(new Student(name, Sdept, grade));
                }
                System.out.println("**" + Sdept + "系**");
                Collections.sort(stu);
                for(int i = 0; i < stu.size(); i++){
                    System.out.println((i+1) + "\t" +stu.get(i));
                }
                rs2.close();
            }
            rs1.close();
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * 查询学生基本信息和课程信息
     */
    public void studentInfo(){
        System.out.println("请输入学生学号:");
        Scanner scanner = new Scanner(System.in);
        String SnoInput = scanner.next();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Student where Sno = ?";
        String Sname = null;
        String Ssex = null;
        String Sage = null;
        String Sdept = null;
        String Scholarship = null;
        String Cno = null;
        String Cname = null;
        String Ccredit = null;
        String Grade = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, SnoInput);
            rs = ps.executeQuery();
            while (rs.next()){
                Sname = rs.getString("Sname");
                Ssex = rs.getString("Ssex");
                Sage = rs.getString("Sage");
                Sdept = rs.getString("Sdept");
                Scholarship = rs.getString("Scholarship");
            }
            rs.close();
            System.out.println("-----------");
            System.out.println("**学生基本信息**");
            System.out.println("-----------");
            System.out.println("\t*学号*" + "\t" + "*姓名*" + "\t" + "*性别*" + "\t" + "*年龄*"+ "\t" + "*院系*"+ "\t" + "*是否获得奖学金*");
            System.out.println(SnoInput + "\t" + Sname + "\t\t" + Ssex + "\t\t" + Sage + "\t\t" + Sdept + "\t\t" + Scholarship);
            sql = "select SC.Cno, Course.Cname, Course.Ccredit, Grade from SC, Course where SC.Sno = ? and SC.Cno = Course.Cno";
            ps = con.prepareStatement(sql);
            ps.setString(1, SnoInput);
            rs = ps.executeQuery();
            System.out.println("-----------");
            System.out.println("**课程信息**");
            System.out.println("-----------");
            System.out.println(String.format("%-9s%-8s%-8s%-8s", "*课程号*" ,"*课程名*", "*课程学分*", "*成绩*"));
            while (rs.next()){
                Cno = rs.getString("Cno");
                Cname = rs.getString("Cname");
                Ccredit = rs.getString("Ccredit");
                Grade = rs.getString("Grade");
                System.out.println(String.format("%-9s%-12s%-10s%-8s", Cno, Cname, Ccredit, Grade));
            }
            rs.close();
            System.out.println("--------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
