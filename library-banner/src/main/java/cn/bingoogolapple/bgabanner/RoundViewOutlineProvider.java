package cn.bingoogolapple.bgabanner;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * @Author: chichapaofan
 * @CreateDate: 2020/4/27
 * @Description:
 */
public class RoundViewOutlineProvider extends ViewOutlineProvider {

    private float mRadius;//圆角弧度

    public RoundViewOutlineProvider(float radius) {

        this.mRadius = radius;

    }

    @Override
    public void getOutline(View view, Outline outline) {

        Rect rect = new Rect();

        view.getGlobalVisibleRect(rect);//将view的区域保存在rect中

        Rect selfRect = new Rect(0, 0, rect.right - rect.left, rect.bottom - rect.top);//绘制区域

        outline.setRoundRect(selfRect, mRadius);

    }

}