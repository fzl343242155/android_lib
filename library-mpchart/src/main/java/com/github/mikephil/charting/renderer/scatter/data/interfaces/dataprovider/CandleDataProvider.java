package com.github.mikephil.charting.renderer.scatter.data.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
