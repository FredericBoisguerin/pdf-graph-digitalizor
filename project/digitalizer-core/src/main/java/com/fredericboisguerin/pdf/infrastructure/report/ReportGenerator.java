package com.fredericboisguerin.pdf.infrastructure.report;


import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;

import java.io.File;

public interface ReportGenerator {

    File generateReport(XYGraph graph, DatasheetGraphExtraInfo graphExtraInfo) throws Exception;
}
