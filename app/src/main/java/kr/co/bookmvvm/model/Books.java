package kr.co.bookmvvm.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Books {
    private int error;
    private int total;
    private int page;
    private ArrayList<Book> books;
}
