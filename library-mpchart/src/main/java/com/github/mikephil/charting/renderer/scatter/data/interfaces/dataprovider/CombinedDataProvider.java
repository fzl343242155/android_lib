package com.github.mikephil.charting.renderer.scatter.data.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.data.CombinedData;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
