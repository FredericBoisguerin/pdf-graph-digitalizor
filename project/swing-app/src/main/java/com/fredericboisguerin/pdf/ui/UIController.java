package com.fredericboisguerin.pdf.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fredericboisguerin.pdf.DrawingActionLogger;
import com.fredericboisguerin.pdf.actions.ExportDataExcel;
import com.fredericboisguerin.pdf.drawlines.converter.DrawingActionsToDrawLinesConverter;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.XYPointSeries;
import com.fredericboisguerin.pdf.parser.PDFDrawingActionsParser;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import com.fredericboisguerin.pdf.parser.model.DrawingActionVisitor;
import com.fredericboisguerin.pdf.infrastructure.wrapper.CoordComparator;
import com.fredericboisguerin.pdf.infrastructure.wrapper.DrawingLinesToXYGraphConverter;

public class UIController {

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("PDF file", "pdf"));
        jFileChooser.setAcceptAllFileFilterUsed(false);

        jFileChooser.showOpenDialog(mainUI);
        File selectedFile = jFileChooser.getSelectedFile();

        PDFDrawingActionsParser parser = new PDFDrawingActionsParser();
        try {
            List<DrawingAction> drawingActions = parser.parseDrawingActions(selectedFile);
            DrawingActionVisitor logger = new DrawingActionLogger();
            drawingActions.forEach(drawingAction -> drawingAction.accept(logger));
            DrawingActionsToDrawLinesConverter linesConverter = new DrawingActionsToDrawLinesConverter();
            DrawLines drawLines = linesConverter.convert(drawingActions);
            DrawingLinesToXYGraphConverter graphConverter = new DrawingLinesToXYGraphConverter(
                    new CoordComparator());
            XYGraph graph = graphConverter.convert(drawLines);

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

    private static void onGoButtonClicked(GraphEditor graphEditorForm, XYGraph graph) {
        Axis xAxis = graphEditorForm.getxAxisEditorForm().getAxis();
        Axis yAxis = graphEditorForm.getyAxisEditorForm().getAxis();
        List<XYPointSeries> selectedElements = graphEditorForm.getSelectedElements();

        ExportDataExcel exportDataExcel = new ExportDataExcel(graph, xAxis, yAxis, selectedElements);
        try {
            File file = exportDataExcel.execute();
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException e) {
                NotificationUtils.showError(graphEditorForm.getMainPanel(),
                        "Cannot open file " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            NotificationUtils.showError(graphEditorForm.getMainPanel(),
                    "Cannot export to file: " + e.getMessage());
        }
    }
}
