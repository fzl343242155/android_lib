package com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.interfaces.dataprovider;

import com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.components.YAxis;
import com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
