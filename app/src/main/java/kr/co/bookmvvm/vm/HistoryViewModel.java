package kr.co.bookmvvm.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.HashMap;

import kr.co.bookmvvm.db.HistoriesDB;
import kr.co.bookmvvm.model.Book;

public class HistoryViewModel extends ViewModel {
    private MutableLiveData<HashMap<String, Book>> histories;

    public LiveData<HashMap<String,Book>> getHistories() {
        if (histories == null) {
            histories = new MutableLiveData<>();
            loadHistories();
        }
        return histories;
    }

    public void loadHistories() {
        histories.setValue(HistoriesDB.getInstance().getHistories());
    }
}
