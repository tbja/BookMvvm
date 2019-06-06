package kr.co.bookmvvm.views.history;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.views.bookdetail.BookDetailActivity;
import kr.co.bookmvvm.views.history.adapter.HistoriesAdapter;
import kr.co.bookmvvm.vm.HistoryViewModel;


public class HistoryFragment extends Fragment {
    @BindView(R.id.lv_hostories) ListView lv_hostories;
    private HistoryViewModel historyViewModel;
    HistoriesAdapter historiesAdapter;
    HashMap<String,Book> histories = new HashMap<>();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel

        historiesAdapter = new HistoriesAdapter(getActivity(),histories);
        lv_hostories.setAdapter(historiesAdapter);

        historyViewModel.getHistories().observe(this, new Observer<HashMap<String, Book>>() {
                    @Override
                    public void onChanged(@Nullable HashMap<String, Book> bookMarkHashMap) {
                        histories.clear();
                        histories.putAll(bookMarkHashMap);
                        historiesAdapter.setDatas(bookMarkHashMap);
                        historiesAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

    @OnItemClick(R.id.lv_hostories)
    public void onItemClick(AdapterView<?> parent, int position) {
        Book item = histories.get(historiesAdapter.getItem(position).getKey());
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        Gson gson = new Gson();
        intent.putExtra("bookInfo", gson.toJson(item));
        startActivity(intent);
    }

}
