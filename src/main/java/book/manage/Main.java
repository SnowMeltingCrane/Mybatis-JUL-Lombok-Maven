package book.manage;

import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.sql.SqlUtils;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;


@Log
public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));

            while(true) {
                System.out.println("========================");
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("3.录入书籍借阅信息");
                System.out.println("4.查询书籍借阅信息");
                System.out.println("5.查询学生信息");
                System.out.println("6.查询书籍信息");
                System.out.println("输入你要执行的操作（输入其他字符退出）：");
                int choice;
                try {
                    choice = sc.nextInt();
                }catch (Exception e){
                    return;
                }
                sc.nextLine();
                switch(choice) {
                    case 1:
                        addStudent(sc);
                        break;
                    case 2:
                        addBook(sc);
                        break;
                    case 3:
                        addBorrow(sc);
                        break;
                    case 4:
                        showBorrow();
                        break;
                    case 5:
                        showStudents();
                        break;
                    case 6:
                        showBooks();
                        break;
                    default:
                        return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addBook(Scanner sc) {
        System.out.print("请输入书本名称：");
        String title = sc.nextLine();
        System.out.print("请输入书本描述：");
        String desc = sc.nextLine();
        System.out.print("请输入书本价格：");
        String price = sc.nextLine();
        double p = Double.parseDouble(price);
        Book book = Book.builder()
                .title(title)
                .desc(desc)
                .price(p)
                .build();
        System.out.println(book);
        SqlUtils.doSqlWork(mapper -> {
            int i =mapper.addBook(book);
            if (i > 0){
                System.out.println("添加书本成功");
                log.info("新添加了一条书本信息"+book);
            }
            else
                System.out.println("添加失败");
        });
    }

    private static void showBorrow(){
        SqlUtils.doSqlWork(mapper->{
            mapper.getAllBorrows().forEach(borrow->{
                System.out.println(borrow.getStudent().getName()+"->"+borrow.getBook().getTitle());
            });
        });
    }

    private static void showStudents(){
        SqlUtils.doSqlWork(mapper->{
            mapper.getAllStudents().forEach(s->{
                System.out.println("学号："+s.getSid()+" 姓名："+s.getName()+" 性别："+s.getSex()+" 年级："+s.getGrade()+"级");
            });
        });
    }

    private static void showBooks(){
        SqlUtils.doSqlWork(mapper->{
            mapper.getAllBooks().forEach(s->{
                System.out.println("书籍号"+s.getBid()+" 书名："+s.getTitle()+" 详情："+s.getDesc()+" 价格："+s.getPrice());
            });
        });
    }

    private static void addBorrow(Scanner sc) {
        System.out.print("请输入借阅学生的学号：");
        String a = sc.nextLine();
        int sid = Integer.parseInt(a);
        System.out.print("请输入被借阅的书籍号：");
        String b = sc.nextLine();
        int bid = Integer.parseInt(b);
        SqlUtils.doSqlWork(mapper -> {
            int i =mapper.addBorrow(sid, bid);
            if (i > 0){
                System.out.println("添加借阅信息成功");
                log.info("新添加了一条书本借阅信息,借阅学生学号："+sid+"被借阅书籍号"+bid);
            }
            else
                System.out.println("添加失败");
        });
    }

    private static void addStudent(Scanner sc) {
        System.out.print("请输入学生名字：");
        String name = sc.nextLine();
        System.out.print("请输入学生性别（男或女）：");
        String sex = sc.nextLine();
        System.out.print("请输入学生年级：");
        String grade = sc.nextLine();
        int g =Integer.parseInt(grade);
        Student student = Student.builder()
                .name(name)
                .sex(sex)
                .grade(g)
                .build();
        System.out.println(student);
        SqlUtils.doSqlWork(mapper -> {
            int i =mapper.addStudent(student);
            if (i > 0){
                System.out.println("添加学生成功");
                log.info("新添加了一条学生信息"+student);
            }
            else
                System.out.println("添加失败");
        });
    }
}


