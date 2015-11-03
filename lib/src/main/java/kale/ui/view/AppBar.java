package kale.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import kale.lib.appbar.R;


/**
 * @author Jack Tony
 * @date 2015/7/25
 */
public class AppBar extends Toolbar {

    private static final int START = 0xabc;

    public static final int MENU01 = START + 1;

    public static final int Menu02 = MENU01 + 1;

    public static final int MENU03 = Menu02 + 1;

    public static final int MENU04 = MENU03 + 1;

    public static final int MENU05 = MENU04 + 1;

    private Resources res;

    public AppBar(Context context) {
        this(context, null);
    }

    public AppBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, R.attr.toolbarStyle);
        res = getResources();
        init(context, attrs, defStyleAttr);
    }

    private String getStr(int id) {
        try {
            String str = res.getString(id);
            if (str.indexOf("res") == 0) {
                // 是图片
                return null;
            } else {
                return str;
            }
        } catch (Resources.NotFoundException ex) {
            return null;
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.AppBar, defStyleAttr, 0);

        int menu01Id = a.getResourceId(R.styleable.AppBar_menu1, 0);
        int menu02Id = a.getResourceId(R.styleable.AppBar_menu2, 0);
        int menu03Id = a.getResourceId(R.styleable.AppBar_menu3, 0);
        int menu04Id = a.getResourceId(R.styleable.AppBar_menu4, 0);
        int menu05Id = a.getResourceId(R.styleable.AppBar_menu5, 0);

        a.recycle();

        //设定布局的各种参数
        Toolbar.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.RIGHT);

        int[] menuIds = {menu01Id, menu02Id, menu03Id, menu04Id, menu05Id};

        View menuV;
        for (int menuId : menuIds) {
            if (menuId == 0) {
                continue;
            }

            String text;
            if ((text = getStr(menuId)) != null) {
                menuV = new TextView(context, null, R.attr.menuTextStyle);
                ((TextView) menuV).setText(text);
            } else {
                menuV = new ImageView(context, null, R.attr.menuImageStyle);
                ((ImageView) menuV).setImageResource(menuId);
            }

            menuV.setId(START + 1);
            menuV.setLayoutParams(params);
            addView(menuV);
        }
    }

    public void canFinishActivity() {
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public <T extends View> T getMenu01() {
        return (T) findViewById(MENU01);
    }

    public <T extends View> T getMenu02() {
        return (T) findViewById(Menu02);
    }

    public <T extends View> T getMenu03() {
        return (T) findViewById(MENU03);
    }

    public <T extends View> T getMenu04() {
        return (T) findViewById(MENU04);
    }

    public <T extends View> T getMenu05() {
        return (T) findViewById(MENU05);
    }


    /**
     * 得到标题按钮
     */
    public TextView getTitleView() {
        return (TextView) getSubView("mTitleTextView");
    }

    /**
     * 得到子标题
     */
    public TextView getSubtitleView() {
        return ((TextView) getSubView("mSubtitleTextView"));
    }

    /**
     * 得到左边的导航按钮
     */
    public ImageButton getNavButton() {
        return (ImageButton) getSubView("mNavButtonView");
    }

    /**
     * 得到logo的视图
     */
    public ImageView getLogoView() {
        return ((ImageView) getSubView("mLogoView"));
    }

    /**
     * 得到右边的折叠按钮视图
     */
    public ImageButton getCollapseButton() {
        return (ImageButton) getSubView("mCollapseButtonView");
    }


    private View getSubView(String name) {
        Field field;
        try {
            field = Toolbar.class.getDeclaredField(name);
            field.setAccessible(true);
            View v = (View) field.get(this);
            field.setAccessible(false);
            return v;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}