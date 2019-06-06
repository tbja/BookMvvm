package kr.co.bookmvvm.views.searchbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.bookmvvm.GlideApp;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.model.Book;
import kr.co.bookmvvm.model.Books;

public class SearchAdapter extends BaseAdapter {
    private ArrayList<Book> books;
    private Context context;

    public SearchAdapter(Context context,ArrayList<Book> books) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_books, parent, false);
            holder = new Holder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Book item = (Book) getItem(position);

        GlideApp.with(context)
                .load(item.getImage())
                .centerInside()
                .into(holder.img_search_book_detail);
        holder.txt_search_book_title.setText(item.getTitle());
        holder.txt_search_book_subtitle.setText(item.getSubtitle());
        holder.txt_search_book_isbn13.setText(item.getIsbn13());
        holder.txt_search_book_price.setText(item.getPrice());
        holder.txt_search_book_url.setText(item.getUrl());

        return convertView;
    }

    static class Holder {
        @BindView(R.id.img_search_book_detail) ImageView img_search_book_detail;
        @BindView(R.id.txt_search_book_isbn13) TextView txt_search_book_isbn13;
        @BindView(R.id.txt_search_book_title) TextView txt_search_book_title;
        @BindView(R.id.txt_search_book_subtitle) TextView txt_search_book_subtitle;
        @BindView(R.id.txt_search_book_price) TextView txt_search_book_price;
        @BindView(R.id.txt_search_book_url) TextView txt_search_book_url;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
