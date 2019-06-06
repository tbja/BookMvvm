package kr.co.bookmvvm.model;

import android.content.Intent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Memo {
    private Long id;
    private String isbn13;
    private String Memo;
}
