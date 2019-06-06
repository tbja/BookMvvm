package kr.co.bookmvvm.views.bookmark.adapter;

import android.content.Context;
import android.util.Log;
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
import kr.co.bookmvvm.db.BookMarkDB;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.views.newbook.adapter.NewBookListAdapter;

public class BookMarkAdapter extends BaseAdapter {
    HashMap<String,Book> items = new HashMap<>();
    ArrayList bItems = new ArrayList<>();
    Context context;

    public BookMarkAdapter(Context context,HashMap<String,Book> items) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
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
                .into(holder.img_bookmark_book_detail);
        holder.txt_bookmark_book_title.setText(bItem.getTitle());
        holder.txt_bookmark_book_subtitle.setText(bItem.getSubtitle());
        holder.txt_bookmark_book_isbn13.setText(bItem.getIsbn13());
        holder.txt_bookmark_book_price.setText(bItem.getPrice());
        holder.txt_bookmark_book_url.setText(bItem.getUrl());
        holder.btn_del_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookMarkDB.getInstance().delBookMark(bItem.getIsbn13());
                items.remove(bItem.getIsbn13());
                setDatas(items);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class Holder {
        @BindView(R.id.img_bookmark_book_detail) ImageView img_bookmark_book_detail;
        @BindView(R.id.txt_bookmark_book_isbn13) TextView txt_bookmark_book_isbn13;
        @BindView(R.id.txt_bookmark_book_title) TextView txt_bookmark_book_title;
        @BindView(R.id.txt_bookmark_book_subtitle) TextView txt_bookmark_book_subtitle;
        @BindView(R.id.txt_bookmark_book_price) TextView txt_bookmark_book_price;
        @BindView(R.id.txt_bookmark_book_url) TextView txt_bookmark_book_url;
        @BindView(R.id.btn_del_bookmark) Button btn_del_bookmark;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
