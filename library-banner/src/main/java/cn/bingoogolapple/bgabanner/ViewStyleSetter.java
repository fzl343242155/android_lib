package cn.bingoogolapple.bgabanner;

import android.view.View;

/**
 * @Author: chichapaofan
 * @CreateDate: 2020/4/27
 * @Description:对View进行裁剪为的是让ViewPager里的item
 * 在圆角矩形里滚动，指不定啥时候又出幺蛾子先写个setOval放这，@99999别给删了
 */
public class ViewStyleSetter {
    private View mView;

    public ViewStyleSetter(View view) {

        this.mView = view;

    }

    public void setRound(float radius) {

        this.mView.setClipToOutline(true);//用outline裁剪内容区域

        this.mView.setOutlineProvider(new RoundViewOutlineProvider(radius));

    }

    public void setOval() {

        this.mView.setClipToOutline(true);//用outline裁剪内容区域

        this.mView.setOutlineProvider(new OvalViewOutlineProvider());

    }

    public void clearShapeStyle() {

        this.mView.setClipToOutline(false);

    }
}
