package com.fredericboisguerin.graph.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fred on 12/01/17.
 */
public class ReportModel {

    private final List<ReportSeries> reportSeriesList = new ArrayList<>();

    public boolean add(ReportSeries reportSeries) {
        return reportSeriesList.add(reportSeries);
    }

    public List<ReportSeries> getReportSeriesList() {
        return Collections.unmodifiableList(reportSeriesList);
    }
}
