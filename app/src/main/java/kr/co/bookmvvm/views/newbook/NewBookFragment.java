package kr.co.bookmvvm.views.newbook;

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
import android.widget.ListView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Books;
import kr.co.bookmvvm.retrofit.RetrofitService;
import kr.co.bookmvvm.views.bookdetail.BookDetailActivity;
import kr.co.bookmvvm.views.newbook.adapter.NewBookListAdapter;
import kr.co.bookmvvm.vm.NewBookViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewBookFragment extends Fragment {

    private NewBookViewModel mViewModel;
    @BindView(R.id.lv_book_list) ListView lv_book_list;
    Books books;
    NewBookListAdapter newBookListAdapter;

    public static NewBookFragment newInstance() {
        return new NewBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_new_book, container, false);
        ButterKnife.bind(this, view);

        refreshNewBookList();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewBookViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.getNewBooks().observe(this, new Observer<Books>() {
                @Override
                public void onChanged(@Nullable Books items) {
                    books = items;
                    refreshNewBookList();
                }
            }
        );
    }

    public void refreshNewBookList() {
        if (books != null) {
            newBookListAdapter = new NewBookListAdapter(books,getActivity());
            lv_book_list.setAdapter(newBookListAdapter);
        }
    }

    @OnItemClick(R.id.lv_book_list)
    public void onItemClick(AdapterView<?> parent, int position) {
        Book item = books.getBooks().get(position);
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra("bookInfo", gson.toJson(item));
        startActivity(intent);
    }

}
