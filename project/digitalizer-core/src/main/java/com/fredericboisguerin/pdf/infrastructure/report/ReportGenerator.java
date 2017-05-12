package com.fredericboisguerin.pdf.infrastructure.report;


import com.fredericboisguerin.pdf.graph.XYGraph;

import java.io.File;

public interface ReportGenerator {

    File generateReport(XYGraph graph) throws Exception;
}
