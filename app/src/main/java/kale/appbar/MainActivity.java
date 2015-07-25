package kale.appbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import kale.ui.view.AppBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppBar appBar = (AppBar) findViewById(R.id.app_bar);
        
        TextView view = (TextView) findViewById(AppBar.MENU01);// 可以通过id直接找到meu控件
        View view1 = appBar.getMenu01(); // 通过appbar来获得menu对象
        
        appBar.getTitleView();
        appBar.getNavButton();
        appBar.getSubtitleView(); 
        // 还有各种toolbar本身的方法……
        //appBar.getTitleView().setText("kale");
        //appBar.getTitleView().setBackgroundColor(0xffff0000);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
