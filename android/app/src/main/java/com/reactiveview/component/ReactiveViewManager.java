package com.reactiveview.component;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by rajiv on 04/02/18.
 */

public class ReactiveViewManager extends SimpleViewManager<ReactiveView> {

    private static final String REACT_CLASS = "ReactiveView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ReactiveView createViewInstance(ThemedReactContext reactContext) {
        return new ReactiveView(reactContext);
    }

}
