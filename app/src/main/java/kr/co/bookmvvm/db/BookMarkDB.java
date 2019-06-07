package kr.co.bookmvvm.db;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.bookmvvm.model.Book;

public class BookMarkDB {
    private static BookMarkDB instance;
    private HashMap<String,Book> bookMarkList = new HashMap<>();
    private BookMarkDB() {  }

    public static BookMarkDB getInstance(){
        if(instance == null){
            instance = new BookMarkDB();
        }
        return instance;
    }

    public void addBookMark(String isbn13,Book item) {
        bookMarkList.put(isbn13,item);
    }

    public void delBookMark(String isbn13) {
        if (bookMarkList.get(isbn13) != null) {
            bookMarkList.remove(isbn13);
            Log.d("aaa","delBookMark : " + bookMarkList.size());
        }
    }

    public boolean isBookMark(String isbn13) {
        if (bookMarkList.get(isbn13) != null) {
            return true;
        }

        return false;
    }

    public HashMap<String,Book> getBookMarkList() {
        return bookMarkList;
    }
}
