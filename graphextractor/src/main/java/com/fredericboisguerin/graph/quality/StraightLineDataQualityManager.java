package com.fredericboisguerin.graph.quality;

import com.fredericboisguerin.graph.model.input.DrawLine;
import com.fredericboisguerin.graph.model.input.DrawingPoint;

public class StraightLineDataQualityManager implements DataQualityManager<DrawLine> {

    @Override
    public DrawLine cleanData(DrawLine data) {
        DrawLine cleanedData = new DrawLine();
        for (DrawingPoint datum : data) {
            if (!data.hasRedondant(datum)) {
                cleanedData.add(datum);
            }
        }
        return cleanedData;
    }
}
