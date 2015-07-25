package kale.appbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import kale.ui.view.AppBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        AppBar appBar = (AppBar) findViewById(R.id.app_bar);

        TextView view = (TextView) findViewById(AppBar.MENU01);// 可以通过id直接找到meu控件
        View view1 = appBar.getMenu01(); // 通过appbar来获得menu对象

        appBar.getTitleView();
        appBar.getNavButton();
        appBar.getSubtitleView();
        // 调用此方法后，点击toolbar左边按钮会让activity finish
        appBar.canfinishActivity();
        // 还有各种toolbar本身的方法……

        appBar.inflateMenu(R.menu.menu_main); // 因为本身就是toolbar，所以仍旧可以装入menu资源
        appBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    return true;
                }
                return false;
            }
        });
       

    }

}
