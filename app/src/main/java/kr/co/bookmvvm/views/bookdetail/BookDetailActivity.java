package kr.co.bookmvvm.views.bookdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.bookmvvm.GlideApp;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.db.BookMarkDB;
import kr.co.bookmvvm.db.HistoriesDB;
import kr.co.bookmvvm.db.MemoDB;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Memo;
import kr.co.bookmvvm.util.Utils;
import kr.co.bookmvvm.views.bookdetail.adapter.MemoAdapter;

public class BookDetailActivity extends AppCompatActivity {
    @BindView(R.id.btn_back) Button btn_back;
    @BindView(R.id.img_book_detail) ImageView img_book_detail;
    @BindView(R.id.txt_book_title) TextView txt_book_title;
    @BindView(R.id.txt_book_subtitle) TextView txt_book_subtitle;
    @BindView(R.id.txt_book_isbn13) TextView txt_book_isbn13;
    @BindView(R.id.txt_book_price) TextView txt_book_price;
    @BindView(R.id.txt_book_url) TextView txt_book_url;
    @BindView(R.id.img_bookmark) ImageView img_bookmark;
    @BindView(R.id.lv_memo) ListView lv_memo;
    @BindView(R.id.et_memo) EditText et_memo;

    Book book;
    private Boolean isHistory = false;
    MemoAdapter memoAdapter;
    HashMap<Long,Memo> memos = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra("bookInfo");
        Gson gson = new Gson();
        book = gson.fromJson(jsonObj,Book.class);

        isHistory = BookMarkDB.getInstance().isBookMark(book.getIsbn13());

        GlideApp.with(getApplicationContext())
                .load(book.getImage())
                .centerInside()
                .into(img_book_detail);
        txt_book_title.setText(book.getTitle());
        txt_book_subtitle.setText(book.getSubtitle());
        txt_book_isbn13.setText(book.getIsbn13());
        txt_book_price.setText(book.getPrice());
        txt_book_url.setText(book.getUrl());

        HistoriesDB.getInstance().addHistory(book.getIsbn13(),book);

        drawBookMarkImg();

        memoAdapter = new MemoAdapter(memos);
        lv_memo.setAdapter(memoAdapter);

        if (MemoDB.getInstance().getMemos().get(book.getIsbn13()) != null) {
            reloadMemos();
        }
    }

    public void drawBookMarkImg() {
        if (isHistory) {
            GlideApp.with(this).load(R.drawable.book_mark_on).into(img_bookmark);
        } else {
            GlideApp.with(this).load(R.drawable.book_mark_off).into(img_bookmark);
        }
    }

    @OnClick(R.id.btn_back)
    public void onClickBtnBack(View view) {
        finish();
    }

    @OnClick(R.id.img_bookmark)
    public void onClickImgBookMark(View view) {
        if (isHistory) {
            BookMarkDB.getInstance().delBookMark(book.getIsbn13());
            isHistory = false;
        } else {
            BookMarkDB.getInstance().addBookMark(book.getIsbn13(),book);
            isHistory = true;
        }

        drawBookMarkImg();
    }

    @OnClick(R.id.btn_add_memo)
    public void onClickBtnAddMemo(View view) {
        String memo = et_memo.getText().toString();
        if (memo.equals("")) return;

        Long id = Utils.uniqueCurrentTimeMS();
        Memo add = new Memo(id,book.getIsbn13(),memo);
        MemoDB.getInstance().addMemo(book.getIsbn13(),add);

        et_memo.setText("");
        reloadMemos();
    }

    public void reloadMemos() {
        memos.putAll(MemoDB.getInstance().getMemos().get(book.getIsbn13()));
        memoAdapter.setDatas(MemoDB.getInstance().getMemos().get(book.getIsbn13()));
        memoAdapter.notifyDataSetChanged();
    }
}
