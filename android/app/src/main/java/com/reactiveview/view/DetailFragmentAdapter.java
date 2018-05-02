package com.reactiveview.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class DetailFragmentAdapter extends FragmentStatePagerAdapter {

  static final int ITEMS = 2;
  private Activity activity;

  public DetailFragmentAdapter(Activity activity, FragmentManager fragmentManager) {
    super(fragmentManager);
    this.activity = activity;
  }

  @Override
  public int getCount() {
    return ITEMS;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return (position == 0 ? "LOCAL" : "REMOTE");
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return ReactiveViewFragment.newInstance("ReactiveView",
            "assets://index.android.bundle");
      case 1:
        return ReactiveViewFragment.newInstance("ReactiveView",
            activity.getFilesDir() + "/index.android.bundle");
      default:
        throw new RuntimeException("Don't have fragment for position : " + position);
    }
  }
}