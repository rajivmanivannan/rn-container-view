package com.reactiveview.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.reactiveview.R;

public class DetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    ViewPager viewPager = (ViewPager) findViewById(R.id.vp_detail);
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_detail_tabs);

    viewPager.setAdapter(new DetailFragmentAdapter(this, getSupportFragmentManager()));
    tabLayout.setupWithViewPager(viewPager);
  }
}
