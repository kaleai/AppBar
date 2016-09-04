package kale.appbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.FitWindowsLinearLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Optional;

import kale.ui.view.AppBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppBar appBar = (AppBar) findViewById(R.id.app_bar);

        initAppbar();

        appBar.getMenu01(); // 可以通过appbar来获得menu对象

        appBar.getTitleView().getId(); // not null
        appBar.getNavButton().getId(); // not null

        appBar.getLogoView();
        appBar.getSubtitleView();
        appBar.getCollapseButton();

        appBar.canFinishActivity(); // 调用此方法后，点击toolbar左边按钮会让activity finish
        // 还有各种toolbar本身的方法……

        View customMenu = appBar.getMenu03();
        ((TextView) customMenu.findViewById(R.id.menu_tv)).setText("kale");

        appBar.addMenu(R.drawable.nav_icon_add_red);
        appBar.inflateMenu(R.menu.menu_main); // 因为本身就是toolbar，所以仍旧可以装入menu资源
        appBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                return id == R.id.action_settings;
            }
        });
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
