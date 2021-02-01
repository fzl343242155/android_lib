package com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
