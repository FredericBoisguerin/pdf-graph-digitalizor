package com.fredericboisguerin.pdf.ui.graph.list;

import com.fredericboisguerin.pdf.model.AxisName;

class DatasheetGraphViewModel {
    private final String id;
    private final AxisName yAxisName;
    private final AxisName xAxisName;
    private final String filename;

    public DatasheetGraphViewModel(String id, AxisName yAxisName, AxisName xAxisName, String filename) {
        this.id = id;
        this.filename = filename;
        this.yAxisName = yAxisName;
        this.xAxisName = xAxisName;
    }

    public String getId() {
        return id;
    }

    public AxisName getyAxisName() {
        return yAxisName;
    }

    public AxisName getxAxisName() {
        return xAxisName;
    }

    public String getFilename() {
        return filename;
    }
}
