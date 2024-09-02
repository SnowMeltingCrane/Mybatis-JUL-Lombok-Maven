package book.manage.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
    private final int bid;
    private final String title;
    private final String desc;
    private final double price;
}
