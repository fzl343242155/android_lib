package com.github.mikephil.charting.renderer.scatter.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isHighlightFullBarEnabled();
}
