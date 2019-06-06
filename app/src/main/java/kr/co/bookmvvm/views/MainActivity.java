package kr.co.bookmvvm.views;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.bookmvvm.R;
import kr.co.bookmvvm.VPMainAdapter;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_page_0) Button btn_page_0;
    @BindView(R.id.btn_page_1) Button btn_page_1;
    @BindView(R.id.btn_page_2) Button btn_page_2;
    @BindView(R.id.btn_page_3) Button btn_page_3;
    @BindView(R.id.vp_main) ViewPager viewPager;
    VPMainAdapter vpMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        vpMainAdapter = new VPMainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vpMainAdapter);
    }

    @OnClick(R.id.btn_page_0)
    public void onClickBtnPage0(View view) {
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.btn_page_1)
    public void onClickBtnPage1(View view) {
        viewPager.setCurrentItem(1);
    }

    @OnClick(R.id.btn_page_2)
    public void onClickBtnPage2(View view) {
        viewPager.setCurrentItem(2);
    }

    @OnClick(R.id.btn_page_3)
    public void onClickBtnPage3(View view) {
        viewPager.setCurrentItem(3);
    }
}
