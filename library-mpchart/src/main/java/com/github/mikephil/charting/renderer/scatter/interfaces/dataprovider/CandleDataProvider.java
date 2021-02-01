package com.github.mikephil.charting.renderer.scatter.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
