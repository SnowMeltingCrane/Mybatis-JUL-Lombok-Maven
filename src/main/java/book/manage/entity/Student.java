package book.manage.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Student {
    private final int sid;
    private final String name;
    private final String sex;
    private final int grade;

}
