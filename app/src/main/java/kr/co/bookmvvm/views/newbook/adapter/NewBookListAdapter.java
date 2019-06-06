package kr.co.bookmvvm.views.newbook.adapter;

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

public class NewBookListAdapter extends BaseAdapter {
    private Books books;
    private Context context;

    public NewBookListAdapter(Books books,Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.getTotal();
    }

    @Override
    public Object getItem(int position) {
        return books.getBooks().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
            holder = new Holder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Book item = (Book) getItem(position);

        GlideApp.with(context)
                .load(item.getImage())
                .centerCrop()
                .into(holder.img_book);
        holder.txt_book_name.setText(item.getTitle());

        return convertView;
    }

    static class Holder {
        @BindView(R.id.img_book) ImageView img_book;
        @BindView(R.id.txt_book_name) TextView txt_book_name;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
