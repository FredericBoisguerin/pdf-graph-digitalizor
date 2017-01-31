package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.graph.*;
import com.fredericboisguerin.graph.report.ReportGenerator;
import com.fredericboisguerin.graph.report.ReportGeneratorImpl;
import com.fredericboisguerin.pdf.DrawingActionLogger;
import com.fredericboisguerin.pdf.parser.DrawingActionsParser;
import com.fredericboisguerin.pdf.parser.DrawingActionsParserFactory;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import com.fredericboisguerin.pdf.parser.model.DrawingActionVisitor;
import com.fredericboisguerin.pdf.wrapper.DrawLines;
import com.fredericboisguerin.pdf.wrapper.DrawingActionsToDrawLinesConverter;
import com.fredericboisguerin.pdf.wrapper.DrawingActionsToDrawLinesConverterImpl;
import com.fredericboisguerin.wrapper.DrawingLinesToXYGraphConverter;
import com.fredericboisguerin.wrapper.DrawingLinesToXYGraphConverterImpl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by fred on 11/01/17.
 */
public class UIController {

    public static final ReportGenerator REPORT_GENERATOR = new ReportGeneratorImpl();

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("PDF file", "pdf"));
        jFileChooser.setAcceptAllFileFilterUsed(false);

        jFileChooser.showOpenDialog(mainUI);
        File selectedFile = jFileChooser.getSelectedFile();

        DrawingActionsParserFactory factory = new DrawingActionsParserFactory();
        DrawingActionsParser parser = factory.build();
        try {
            List<DrawingAction> drawingActions = parser.parseDrawingActions(selectedFile);
//            DrawingActionVisitor logger = new DrawingActionLogger();
//            drawingActions.forEach(drawingAction -> drawingAction.accept(logger));
            DrawingActionsToDrawLinesConverter linesConverter = new DrawingActionsToDrawLinesConverterImpl();
            DrawLines drawLines = linesConverter.convert(drawingActions);
            DrawingLinesToXYGraphConverter graphConverter = new DrawingLinesToXYGraphConverterImpl();
            EditableXYGraph graph = graphConverter.convert(drawLines);

            GraphEditor graphEditorForm = mainUI.getGraphEditorForm();
            graphEditorForm.addGoButtonListener(() -> {
                // On go button clicked
                onGoButtonClicked(graphEditorForm, graph);
            });

            List<XYPointSeries> xyPointSeries = new ArrayList<>();
            for (XYPointSeries series : graph) {
                xyPointSeries.add(series);
            }
            graphEditorForm.setElements(xyPointSeries);

            String message = String.format("XYGraph imported !\n%s", graph);
            NotificationUtils.showInfo(mainUI, message);
        } catch (Exception e) {
            e.printStackTrace();
            NotificationUtils.showError(mainUI, e.getMessage());
            mainUI.dispose();
        }
    }

    private static void onGoButtonClicked(GraphEditor graphEditorForm, EditableXYGraph graph) {
        Axis xAxis = graphEditorForm.getxAxisEditorForm()
                                    .getAxis();
        Axis yAxis = graphEditorForm.getyAxisEditorForm()
                                    .getAxis();

        graph.removeAll();
        Enumeration<XYPointSeries> selectedElements = graphEditorForm.getSelectedElements();
        while (selectedElements.hasMoreElements()) {
            XYPointSeries xyPointSeries = selectedElements.nextElement();
            graph.add(xyPointSeries);
        }

        XYGraph resizedGraph = graph.changeAxes(xAxis, yAxis, new CoordConverterProviderImpl());

        File file;
        try {
            file = REPORT_GENERATOR.generateReport(resizedGraph);
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException e) {
                NotificationUtils.showError(graphEditorForm.getMainPanel(), "Cannot open file " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            NotificationUtils.showError(graphEditorForm.getMainPanel(), "Cannot export to file: " + e.getMessage());
        }

    }
}
