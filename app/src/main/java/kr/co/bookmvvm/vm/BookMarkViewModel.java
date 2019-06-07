package kr.co.bookmvvm.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.bookmvvm.db.BookMarkDB;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Books;

public class BookMarkViewModel extends ViewModel {
    private MutableLiveData<HashMap<String,Book>> bookMarks;

    public LiveData<HashMap<String,Book>> getBookMarks() {
        if (bookMarks == null) {
            bookMarks = new MutableLiveData<>();
            loadBookMarks();
        }
        return bookMarks;
    }

    public void loadBookMarks() {
        bookMarks.setValue(BookMarkDB.getInstance().getBookMarkList());
    }
}
