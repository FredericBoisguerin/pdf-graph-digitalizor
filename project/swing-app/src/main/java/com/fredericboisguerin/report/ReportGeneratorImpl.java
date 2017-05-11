package com.fredericboisguerin.report;

import com.fredericboisguerin.pdf.graph.Coord;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.XYPoint;
import com.fredericboisguerin.pdf.graph.XYPointSeries;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.TransformerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jxl.biff.FormatRecord.logger;

public class ReportGeneratorImpl implements ReportGenerator {

    private static final String DYNAMIC_COLUMNS_DEMO_XML_CONFIG = "template_descriptor.xml";
    private static final String TEMPLATE = "report_template.xls";

    @Override
    public File generateReport(XYGraph graph) throws Exception {
        ReportModel reportModel = convertGraphToReportModel(graph);
        return exportModel(reportModel);
    }

    private ReportModel convertGraphToReportModel(XYGraph graph) {
        int i = 0;
        ReportModel reportModel = new ReportModel();
        for (XYPointSeries series : graph) {
            String seriesName = String.format("Série %d", i);
            ReportSeries reportSeries = new ReportSeries(seriesName);
            reportModel.add(reportSeries);
            for (XYPoint point : series) {
                Coord x = point.getX();
                Coord y = point.getY();
                ReportPoint reportPoint = new ReportPoint(x.getCoord(), y.getCoord());
                reportSeries.add(reportPoint);
            }
            i++;
        }
        return reportModel;
    }

    private File exportModel(ReportModel reportModel) throws IOException {


        List<ReportSeries> reportSeriesList = reportModel.getReportSeriesList();
        List<String> headers = reportSeriesList
                .stream()
                .map(ReportSeries::getName)
                .collect(Collectors.toList());

        List<List<ReportPoint>> rows = new ArrayList<>();

        for (int i = 0; i < reportSeriesList
                .size(); i++) {
            ReportSeries reportSeries = reportSeriesList.get(i);
            List<ReportPoint> reportPoints = reportSeries.getReportPoints();
            for (int j = 0; j < reportPoints.size(); j++) {
                if (rows.size() < j + 1) {
                    rows.add(j, new ArrayList<>());
                }
                ReportPoint reportPoint = reportPoints.get(j);
                rows.get(j)
                    .add(i, reportPoint);
            }
        }

        File tempFile = File.createTempFile("report", ".xls");
        // loading areas and commands using XmlAreaBuilder
        try (InputStream is = this.getClass()
                                  .getResourceAsStream(TEMPLATE)) {
            try (OutputStream os = new FileOutputStream(tempFile)) {
                Transformer transformer = TransformerFactory.createTransformer(is, os);
                InputStream configInputStream = this.getClass()
                                                    .getResourceAsStream(DYNAMIC_COLUMNS_DEMO_XML_CONFIG);
                AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
                List<Area> xlsAreaList = areaBuilder.build();
                Area xlsArea = xlsAreaList.get(0);
                // creating context
                Context context = PoiTransformer.createInitialContext();
                context.putVar("headers", headers);
                context.putVar("rows", rows);
                // applying transformation
                logger.info("Applying area " + xlsArea.getAreaRef() + " at cell " + new CellRef("Result!A1"));
                xlsArea.applyAt(new CellRef("Result!A1"), context);
                // saving the results to file
                transformer.write();
                logger.info("Complete");
                logger.info(tempFile.getAbsolutePath());
            }
        }

        return tempFile;
    }
}
