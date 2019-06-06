package kr.co.bookmvvm.db;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.bookmvvm.model.Memo;

public class MemoDB {
    private static MemoDB instance;
    private HashMap<String, HashMap<Long,Memo>> memos = new HashMap<>();
    private MemoDB() {  }

    public static MemoDB getInstance(){
        if(instance == null){
            instance = new MemoDB();
        }
        return instance;
    }

    public void addMemo(String isbn13,Memo item) {
        if (memos.get(isbn13) != null) {
            memos.get(isbn13).put(item.getId(),item);
        } else {
            memos.put(isbn13,new HashMap<Long, Memo>());
            memos.get(isbn13).put(item.getId(),item);
        }
    }

    public void delMemo(String isbn13,Long id) {
        if (memos.get(isbn13) != null) {
            memos.get(isbn13).remove(id.longValue());
        }
    }

    public HashMap<String, HashMap<Long,Memo>> getMemos() {
        return memos;
    }
}
