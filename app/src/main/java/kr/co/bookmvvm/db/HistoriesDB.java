package kr.co.bookmvvm.db;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import kr.co.bookmvvm.model.Book;

public class HistoriesDB {
    private static HistoriesDB instance;
    private HashMap<String,Book> histories = new HashMap<>();
    private HistoriesDB() {  }

    public static HistoriesDB getInstance(){
        if(instance == null){
            instance = new HistoriesDB();
        }
        return instance;
    }

    public void addHistory(String isbn13,Book item) {
        histories.put(isbn13,item);
    }

    public void delHistory(String isbn13) {
        if (histories.get(isbn13) != null) {
            histories.remove(isbn13);
        }
    }

    public boolean isHistory(String isbn13) {
        if (histories.get(isbn13) != null) {
            return true;
        }

        return false;
    }

    public HashMap<String,Book> getHistories() {
        return histories;
    }
}
