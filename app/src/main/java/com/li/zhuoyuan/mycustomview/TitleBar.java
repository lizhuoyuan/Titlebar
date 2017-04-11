package com.li.zhuoyuan.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zhuoy on 2017/4/11.
 */

public class TitleBar extends RelativeLayout {

    private Button leftbtn, rightbtn;
    private TextView tvtitle;

    private int lefttextcolor;
    private String lefttext;
    private Drawable leftbackground;

    private int righttextcolor;
    private String righttext;
    private Drawable rightbackground;

    private float titleTextSize;
    private String titletext;
    private int titleColor;
    private Context context;
    private LayoutParams leftparams, rightparams, titleparams;
    private titlebarClickListener listener;

    public interface titlebarClickListener {
        public void rightclick();

        public void leftclick();
    }

    public void setTitleBarOnClickListener(titlebarClickListener listener) {
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        lefttextcolor = typedArray.getColor(R.styleable.TopBar_lefttextColor, 0);
        lefttext = typedArray.getString(R.styleable.TopBar_leftText);
        leftbackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);

        rightbackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        righttext = typedArray.getString(R.styleable.TopBar_rightText);
        righttextcolor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);

        titleColor = typedArray.getColor(R.styleable.TopBar_titleColor, 0);
        titletext = typedArray.getString(R.styleable.TopBar_title);
        titleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 0);

        typedArray.recycle();

        leftbtn = new Button(context);
        rightbtn = new Button(context);
        tvtitle = new TextView(context);

        leftbtn.setText(lefttext);
        leftbtn.setTextColor(lefttextcolor);
        leftbtn.setBackground(leftbackground);

        rightbtn.setText(righttext);
        rightbtn.setTextColor(righttextcolor);
        rightbtn.setBackground(rightbackground);

        tvtitle.setTextColor(titleColor);
        tvtitle.setText(titletext);
        tvtitle.setTextSize(titleTextSize);
        tvtitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xff59563);
        setview();
        addevent();
    }

    private void addevent() {
        leftbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftclick();

                // Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
            }
        });
        rightbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightclick();
            }

        });
    }

    private void setview() {


        leftparams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftbtn, leftparams);


        rightparams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightbtn, rightparams);

        titleparams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleparams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvtitle, titleparams);
    }

    /**
     * 传入flag设置是否显示leftbtn  true为显示 false不显示
     *
     * @param flag
     */
    public void setLeftisvisible(boolean flag) {
        if (flag) {
            leftbtn.setVisibility(VISIBLE);
        } else {
            leftbtn.setVisibility(GONE);
        }
    }

    /**
     * 传入flag设置是否显示rightbtn  true为显示 false不显示
     *
     * @param flag
     */
    public void setRightisvisible(boolean flag) {
        if (flag) {
            rightbtn.setVisibility(VISIBLE);
        } else {
            rightbtn.setVisibility(GONE);
        }
    }
}
