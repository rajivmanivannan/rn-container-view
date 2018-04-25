package com.reactiveview.component;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.reactiveview.BuildConfig;
import com.reactiveview.MainApplication;

/**
 * Created by rajiv on 04/02/18.
 */
public class ReactiveView extends ReactRootView {
  private String componentName;
  private String moduleName;

  public ReactiveView(Context context) {
    super(context);
  }

  public ReactiveView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ReactiveView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setReactModule(String componentName, String moduleName) {
    this.componentName = componentName;
    this.moduleName = moduleName;
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    loadJsBundle();
  }

  private void loadJsBundle() {
    ReactInstanceManagerBuilder builder = ReactInstanceManager.builder()
        .setApplication(MainApplication.getInstance())
        .addPackage(new MainReactPackage())
        .addPackage(new ReactiveViewReactPackage())
        .setUseDeveloperSupport(BuildConfig.DEBUG)
        .setInitialLifecycleState(LifecycleState.BEFORE_RESUME);
    builder.setJSBundleFile(moduleName);
    ReactInstanceManager reactInstanceManager = builder.build();
    this.startReactApplication(reactInstanceManager, componentName, null);
  }

}
