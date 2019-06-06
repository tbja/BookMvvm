package kr.co.bookmvvm.views.bookdetail.adapter;

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
import butterknife.OnClick;
import kr.co.bookmvvm.GlideApp;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.db.MemoDB;
import kr.co.bookmvvm.model.Memo;

public class MemoAdapter extends BaseAdapter {
    HashMap<Long,Memo> items = new HashMap<>();
    ArrayList mItems = new ArrayList<>();

    public MemoAdapter(HashMap<Long,Memo> items) {
        this.items = items;
        mItems.addAll(items.entrySet());
    }

    public void setDatas(HashMap<Long,Memo> items) {
        mItems.clear();
        mItems.addAll(items.entrySet());
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);
            holder = new Holder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Map.Entry<String, String> item = (Map.Entry<String, String>) getItem(position);

        final Memo mItem = (Memo) items.get(item.getKey());

        holder.txt_memo.setText(mItem.getMemo());

        holder.btn_del_memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoDB.getInstance().delMemo(mItem.getIsbn13(), mItem.getId());
                items.remove(mItem.getId().longValue());
                setDatas(items);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class Holder {
        @BindView(R.id.txt_memo) TextView txt_memo;
        @BindView(R.id.btn_del_memo) Button btn_del_memo;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
