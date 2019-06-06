package kr.co.bookmvvm.views.searchbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnItemClick;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.db.HistoriesDB;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Books;
import kr.co.bookmvvm.retrofit.RetrofitService;
import kr.co.bookmvvm.views.bookdetail.BookDetailActivity;
import kr.co.bookmvvm.views.searchbook.adapter.SearchAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBookFragment extends Fragment {
    Integer PAGECOUNT = 10;
    Integer total_count = -1;
    Integer now_page = -1;
    boolean now_loading = false;
    String search_word = "";
    @BindView(R.id.et_search_word) EditText et_search_word;
    @BindView(R.id.lv_search_list) ListView lv_search_list;
    @BindView(R.id.btn_search) Button btn_search;
    @BindView(R.id.sp_search_list) SwipeRefreshLayout sp_search_list;
    ArrayList<Book> books = new ArrayList<>();
    SearchAdapter searchAdapter;

    public static SearchBookFragment newInstance() {
        return new SearchBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_search_book, container, false);
        ButterKnife.bind(this, view);

        lv_search_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean loadMore =  firstVisibleItem + visibleItemCount >= totalItemCount;

                if(loadMore) {
                    searchBooks();
                }

            }
        });

        sp_search_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initSearchInfo();
                sp_search_list.setRefreshing(false);
                searchBooks();
            }
        });

        return view;
    }

    public void initSearchInfo() {
        total_count = -1;
        now_page = -1;
        now_loading = false;
        search_word = "";
        books.clear();
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchAdapter = new SearchAdapter(getActivity(),books);
        lv_search_list.setAdapter(searchAdapter);

        searchBooks();
    }

    @OnClick(R.id.btn_search)
    public void onClickBtnSearch(View view) {
        initSearchInfo();
        search_word = et_search_word.getText().toString();
        searchBooks();
    }

    @OnItemClick(R.id.lv_search_list)
    public void onItemClick(AdapterView<?> parent, int position) {
        Book item = books.get(position);
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra("bookInfo", gson.toJson(item));
        startActivity(intent);
    }

    public void searchBooks() {
        if (now_loading) return;
        if (total_count != -1 && isLastPage()) return;
        if (search_word.equals("")) return;
        now_loading = true;

        Call<Books> observe = RetrofitService.getInstance().getRetrofitRequest().getSearchBooksList(search_word,now_page+1);
        observe.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                if (response.isSuccessful()) {
                    Books ret = response.body();
                    total_count = ret.getTotal();
                    now_page = ret.getPage();
                    books.addAll(ret.getBooks());
                    searchAdapter.notifyDataSetChanged();

                } else {
                    Log.d("net_err","get search list err");
                }
                now_loading = false;
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                t.printStackTrace();
                now_loading = false;
            }
        });
    }

    public boolean isLastPage() {
        Integer total_pages_count = Math.round((float)total_count / (float)PAGECOUNT);
        if (now_page == total_pages_count ) return true;

        return false;
    }
}