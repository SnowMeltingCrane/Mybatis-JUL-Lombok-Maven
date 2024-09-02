import book.manage.entity.Student;
import book.manage.mapper.BookMapper;
import book.manage.sql.SqlUtils;
import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class MainTest {

    @Test
    public void test() {
        SqlUtils.doSqlWork(mapper->{
            mapper.getAllBorrows().forEach(System.out::println);
        });

    }
}
