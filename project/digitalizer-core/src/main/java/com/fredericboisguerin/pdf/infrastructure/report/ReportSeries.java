package com.fredericboisguerin.pdf.infrastructure.report;

import com.fredericboisguerin.pdf.model.AxisName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportSeries {

    private final String name;
    private final AxisName xAxis;
    private final AxisName yAxis;
    private final List<ReportPoint> reportPoints = new ArrayList<>();

    public ReportSeries(String name, AxisName xAxis, AxisName yAxis) {
        this.name = name;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public boolean add(ReportPoint reportPoint) {
        return reportPoints.add(reportPoint);
    }

    public String getName() {
        return name;
    }

    public AxisName getxAxis() {
        return xAxis;
    }

    public AxisName getyAxis() {
        return yAxis;
    }

    public List<ReportPoint> getReportPoints() {
        return Collections.unmodifiableList(reportPoints);
    }
}
