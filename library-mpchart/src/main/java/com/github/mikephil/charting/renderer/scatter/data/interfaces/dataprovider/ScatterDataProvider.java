package com.github.mikephil.charting.renderer.scatter.data.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
