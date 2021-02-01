package com.github.mikephil.charting.renderer.scatter.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.BubbleData;

public interface BubbleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BubbleData getBubbleData();
}
