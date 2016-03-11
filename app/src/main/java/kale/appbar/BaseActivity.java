package kale.appbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import kale.ui.view.AppBar;

/**
 * @author Kale
 * @date 2016/3/10
 */
public abstract class BaseActivity extends Activity {

    AppBar appBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        appBar = initAppbar();
    }

    protected abstract int getLayoutResId();

    protected AppBar getAppBar() {
        return appBar;
    }

    private
    @Nullable
    AppBar initAppbar() {
        AppBar appBar = (AppBar) findViewById(R.id.app_bar);
        if (appBar != null) {
            ((ViewGroup) appBar.getParent()).removeView(appBar);
            ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
            View childAt = content.getChildAt(0);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            params.setMargins(0, appBar.getLayoutParams().height, 0, 0);
            content.addView(appBar);
        }
        return appBar;
    }
}
