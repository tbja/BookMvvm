package kr.co.bookmvvm.views.bookmark;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.views.bookdetail.BookDetailActivity;
import kr.co.bookmvvm.views.bookmark.adapter.BookMarkAdapter;
import kr.co.bookmvvm.vm.BookMarkViewModel;


public class BookMarkFragment extends Fragment {
    static BookMarkFragment instance = null;
    @BindView(R.id.lv_bookmark) ListView lv_bookmark;
    private BookMarkViewModel bookMarkViewModel;
    private HashMap<String ,Book> bookMarksItems = new HashMap<>();
    private BookMarkAdapter bookMarkAdapter;

    public static BookMarkFragment newInstance() {
        if (instance == null) {
            instance = new BookMarkFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bookMarkViewModel = ViewModelProviders.of(getActivity()).get(BookMarkViewModel.class);
        // TODO: Use the ViewModel

        bookMarkAdapter = new BookMarkAdapter(getActivity(),bookMarksItems);
        lv_bookmark.setAdapter(bookMarkAdapter);

        bookMarkViewModel.getBookMarks().observe(getActivity(), new Observer<HashMap<String, Book>>() {
                    @Override
                    public void onChanged(@Nullable HashMap<String, Book> bookMarkHashMap) {
                        bookMarksItems.clear();
                        bookMarksItems.putAll(bookMarkHashMap);
                        bookMarkAdapter.setDatas(bookMarkHashMap);
                        bookMarkAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

    @OnItemClick(R.id.lv_bookmark)
    public void onItemClick(AdapterView<?> parent, int position) {
        Book item = bookMarksItems.get(bookMarkAdapter.getItem(position).getKey());
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra("bookInfo", gson.toJson(item));
        startActivity(intent);
    }

}
