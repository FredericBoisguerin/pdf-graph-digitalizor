package com.fredericboisguerin.graph.report;

import com.fredericboisguerin.graph.XYGraph;

import java.io.File;

/**
 * Created by fred on 12/01/17.
 */
public interface ReportGenerator {

    File generateReport(XYGraph graph) throws Exception;
}
