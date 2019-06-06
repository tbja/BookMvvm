package kr.co.bookmvvm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kr.co.bookmvvm.views.bookmark.BookMarkFragment;
import kr.co.bookmvvm.views.history.HistoryFragment;
import kr.co.bookmvvm.views.newbook.NewBookFragment;
import kr.co.bookmvvm.views.searchbook.SearchBookFragment;

public class VPMainAdapter extends FragmentStatePagerAdapter {
    public VPMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) return NewBookFragment.newInstance();
        else if (i == 1) return SearchBookFragment.newInstance();
        else if (i == 2) return BookMarkFragment.newInstance();
        else if (i == 3) return HistoryFragment.newInstance();
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
