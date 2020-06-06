package com.chichapaofan.library.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chichapaofan.library.R;


/**
 * @Author: chichapaofan
 * @CreateDate: 2020/4/3
 * @Description:
 */
public class ButtomDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private int layoutRes;
    private View view;
    private int[] clickIds;   //需要设置点击事件的ID.需要其他ID,在dialog实例化后在dialog上fbc.
    private Window window;

    public ButtomDialog(Context context, int layoutRes, int[] clickIds) {
        super(context, R.style.dialog_full);    //设置主题
        this.context = context;
        this.layoutRes = layoutRes;
        this.clickIds = clickIds;
    }

    public ButtomDialog(Context context, View view, int[] clickIds) {
        super(context, R.style.dialog_full);    //设置主题
        this.context = context;
        this.view = view;
        this.clickIds = clickIds;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window = getWindow();
        //底部弹出的Dialog
        window.setGravity(Gravity.BOTTOM);
        //底部弹出的动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        if (view != null) {
            setContentView(view);
        } else {
            setContentView(layoutRes);
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //覆盖状态栏
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= 0x00001000;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                window.getDecorView().setSystemUiVisibility(uiOptions);
            }
        });

        //点击Dialog外部消失
        setCanceledOnTouchOutside(true);
        //禁用返回键
        setCancelable(true);
        //设置点击事件
        if (clickIds != null) {
            for (int id : clickIds) {
                findViewById(id).setOnClickListener(this);
            }
        }
    }

    public View getView() {
        if (view == null) {
            return getLayoutInflater().inflate(layoutRes, null);
        }
        return view;
    }

    private OnBottomItemClickListener listener;

    public interface OnBottomItemClickListener {
        void onBottomItemClick(ButtomDialog dialog, View view);
    }

    public void setOnBottomItemClickListener(OnBottomItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onBottomItemClick(this, v);
    }
}

