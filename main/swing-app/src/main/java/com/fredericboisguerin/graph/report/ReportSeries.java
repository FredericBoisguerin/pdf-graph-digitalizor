package com.fredericboisguerin.graph.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fred on 12/01/17.
 */
public class ReportSeries {

    private final String name;
    private final List<ReportPoint> reportPoints = new ArrayList<>();

    public ReportSeries(String name) {
        this.name = name;
    }

    public boolean add(ReportPoint reportPoint) {
        return reportPoints.add(reportPoint);
    }

    public String getName() {
        return name;
    }

    public List<ReportPoint> getReportPoints() {
        return Collections.unmodifiableList(reportPoints);
    }
}
