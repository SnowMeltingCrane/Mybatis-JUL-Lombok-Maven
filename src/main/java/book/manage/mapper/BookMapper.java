package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Insert("insert into student (name,sex,grade) values (#{name},#{sex},#{grade})")
    int addStudent(Student student);

    @Select("select * from student")
    List<Student> getAllStudents();

    @Insert("insert into book (title, `desc`, price) values (#{title},#{desc},#{price})")
    int addBook(Book book);

    @Select("select * from book")
    List<Book> getAllBooks();

    @Insert("insert into borrow(sid,bid) values (#{sid},#{bid})")
    int addBorrow(@Param("sid")int sid, @Param("bid")int bid);

    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "sid", property = "student",one = @One(select = "getStudentById")),
            @Result(column = "bid", property = "book",one = @One(select ="getBookById"))
    })
    @Select("select * from borrow")
    List<Borrow> getAllBorrows();

    @Select("select * from student where sid = #{sid}")
    Student getStudentById(int sid);

    @Select("select * from book where bid = #{bid}")
    Book getBookById(int bid);



}
