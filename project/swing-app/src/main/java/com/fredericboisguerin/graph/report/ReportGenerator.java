package com.fredericboisguerin.graph.report;

import com.fredericboisguerin.graph.XYGraph;

import java.io.File;

public interface ReportGenerator {

    File generateReport(XYGraph graph) throws Exception;
}
