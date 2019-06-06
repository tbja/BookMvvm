package kr.co.bookmvvm.views.bookmark;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.views.bookmark.adapter.BookMarkAdapter;
import kr.co.bookmvvm.vm.BookMarkViewModel;


public class BookMarkFragment extends Fragment {
    @BindView(R.id.lv_bookmark) ListView lv_bookmark;
    private BookMarkViewModel bookMarkViewModel;
    private HashMap<String ,Book> bookMarksItems = new HashMap<>();
    private BookMarkAdapter bookMarkAdapter;

    public static BookMarkFragment newInstance() {
        return new BookMarkFragment();
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

        bookMarkViewModel = ViewModelProviders.of(this).get(BookMarkViewModel.class);
        // TODO: Use the ViewModel

        bookMarkAdapter = new BookMarkAdapter(getActivity(),bookMarksItems);
        lv_bookmark.setAdapter(bookMarkAdapter);

        bookMarkViewModel.getBookMarks().observe(this, new Observer<HashMap<String, Book>>() {
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

}
