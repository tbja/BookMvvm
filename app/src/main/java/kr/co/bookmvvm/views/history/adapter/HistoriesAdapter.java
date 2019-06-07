package kr.co.bookmvvm.views.history.adapter;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.bookmvvm.GlideApp;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.db.HistoriesDB;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.vm.HistoryViewModel;

public class HistoriesAdapter extends BaseAdapter {
    HashMap<String,Book> items = new HashMap<>();
    ArrayList bItems = new ArrayList<>();
    Context context;

    public HistoriesAdapter(Context context, HashMap<String,Book> items) {
        this.context = context;
        this.items = items;
        bItems.addAll(items.entrySet());
    }

    public void setDatas(HashMap<String,Book> items) {
        bItems.clear();
        bItems.addAll(items.entrySet());
    }

    @Override
    public int getCount() {
        return bItems.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) bItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_histories, parent, false);
            holder = new Holder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Map.Entry<String, String> item = (Map.Entry<String, String>) getItem(position);

        final Book bItem = items.get(item.getKey());

        GlideApp.with(context)
                .load(bItem.getImage())
                .centerInside()
                .into(holder.img_histories_book_detail);
        holder.txt_histories_book_title.setText(bItem.getTitle());
        holder.txt_histories_book_subtitle.setText(bItem.getSubtitle());
        holder.txt_histories_book_isbn13.setText(bItem.getIsbn13());
        holder.txt_histories_book_price.setText(bItem.getPrice());
        holder.txt_histories_book_url.setText(bItem.getUrl());

        holder.btn_del_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoriesDB.getInstance().delHistory(bItem.getIsbn13());
                items.remove(bItem.getIsbn13());
                setDatas(items);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    static class Holder {
        @BindView(R.id.img_histories_book_detail) ImageView img_histories_book_detail;
        @BindView(R.id.txt_histories_book_isbn13) TextView txt_histories_book_isbn13;
        @BindView(R.id.txt_histories_book_title) TextView txt_histories_book_title;
        @BindView(R.id.txt_histories_book_subtitle) TextView txt_histories_book_subtitle;
        @BindView(R.id.txt_histories_book_price) TextView txt_histories_book_price;
        @BindView(R.id.txt_histories_book_url) TextView txt_histories_book_url;
        @BindView(R.id.btn_del_history) Button btn_del_history;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
