package com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.interfaces.datasets;

import com.github.mikephil.charting.renderer.scatter.data.renderer.scatter.data.Entry;

/**
 * Created by philipp on 21/10/15.
 */
public interface IBarLineScatterCandleBubbleDataSet<T extends Entry> extends IDataSet<T> {

    /**
     * Returns the color that is used for drawing the highlight indicators.
     *
     * @return
     */
    int getHighLightColor();
}
