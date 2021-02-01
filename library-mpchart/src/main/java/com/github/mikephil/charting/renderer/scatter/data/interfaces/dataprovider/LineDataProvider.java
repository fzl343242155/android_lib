package com.github.mikephil.charting.renderer.scatter.data.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.components.YAxis;
import com.github.mikephil.charting.renderer.scatter.data.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
