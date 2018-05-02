package com.reactiveview.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.reactiveview.R;
import com.reactiveview.component.ReactiveView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReactiveViewFragment extends Fragment {

  public ReactiveViewFragment() {
    // Required empty public constructor
  }

  public static ReactiveViewFragment newInstance(String componentName,
      String jSBundleFilePath) {
    Bundle bundle = new Bundle();
    bundle.putString("componentName", componentName);
    bundle.putString("jSBundleFilePath", jSBundleFilePath);
    ReactiveViewFragment fragment = new ReactiveViewFragment();
    fragment.setArguments(bundle);
    return fragment;
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_reactive_view, container, false);
    ReactiveView reactiveView = (ReactiveView) view.findViewById(R.id.rv_container);
    reactiveView.setReactModule(getArguments().getString("componentName"),getArguments().getString("jSBundleFilePath"));
    return view;
  }

}
