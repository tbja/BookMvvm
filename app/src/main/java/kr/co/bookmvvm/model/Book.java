package kr.co.bookmvvm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String title;
    private String subtitle;
    private String isbn13;
    private String price;
    private String image;
    private String url;
}
