# 学生数据库JAVA应用

> 版本说明
>
> * `java`:`jdk1.8.0_261`
> * `mysql驱动`:`mysql-connector-java-8.0.22.jar`
> * `mysql数据库`:`Ver 8.0.22 for Linux on x86_64 (MySQL Community Server - GPL)`

## Get Start

> 快速使用

在`MysqlClass.java`中（line 18，[点击这里](https://github.com/AgentGuo/StudentSqlwithJdbc/blob/main/src/jdbc/MysqlClass.java)），将如下的配置信息修改为你的数据库的信息

~~~java
    public MysqlClass(){
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/CSEDB_U201811010";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "qwer";
~~~

**then run it !!!**

## 数据库说明

数据库有三个表，Student、Course、SC，各个表的字段信息如下：

**Student**（char长度适当即可）

~~~
+-------------+----------------+-----------+
| COLUMN_NAME | COLUMN_COMMENT | DATA_TYPE |
+-------------+----------------+-----------+
| Sage        |                | smallint  |
| Scholarship |                | char      |
| Sdept       |                | char      |
| Sname       |                | char      |
| Sno         |                | char      |
| Ssex        |                | char      |
+-------------+----------------+-----------+
~~~

**Course**（char长度适当即可）

~~~
+-------------+----------------+-----------+
| COLUMN_NAME | COLUMN_COMMENT | DATA_TYPE |
+-------------+----------------+-----------+
| Ccredit     |                | smallint  |
| Cname       |                | char      |
| Cno         |                | char      |
| Cpno        |                | char      |
+-------------+----------------+-----------+
~~~

**SC**（char长度适当即可）

~~~
+-------------+----------------+-----------+
| COLUMN_NAME | COLUMN_COMMENT | DATA_TYPE |
+-------------+----------------+-----------+
| Cno         |                | char      |
| Grade       |                | smallint  |
| Sno         |                | char      |
+-------------+----------------+-----------+
~~~

