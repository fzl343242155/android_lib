package cn.bingoogolapple.bgabanner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ViewBannerUtil {
    /**
     * @date 2019/8/8 上午9:49
     * @author yinsh
     * @description 首页适配
     */
    public static void setHomeImageView(Context c, View bannerLayout) {
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        float radio = ((float) 422 / (float) 750);
        ll.width = Screen.getWidth(c);
        ll.height = (int) (Screen.getWidth(c) * radio);
        bannerLayout.setLayoutParams(ll); // 设置焦点图展示大小
    }
    /**
     * @date 2019/8/8 上午9:49
     * @author yinsh
     * @description 首页适配
     */
    public static void setHomeIndicatorLayout(Context c, View bannerLayout) {
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        float radio = ((float) 128 / (float) 750);
        ll.width = Screen.getWidth(c);
        ll.height = (int) (Screen.getWidth(c) * radio);
        ll.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLayout.setLayoutParams(ll); // 设置焦点图展示大小
    }
}

