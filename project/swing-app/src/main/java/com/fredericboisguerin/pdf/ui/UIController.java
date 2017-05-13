package com.fredericboisguerin.pdf.ui;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.util.IOUtils;

import com.fredericboisguerin.pdf.DrawingActionLogger;
import com.fredericboisguerin.pdf.actions.ExportDataExcel;
import com.fredericboisguerin.pdf.actions.extract.ExtractGraphFromPDFFile;
import com.fredericboisguerin.pdf.drawlines.converter.DrawingActionsToDrawLinesConverter;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.Serie;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;
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

        try {
            byte[] file = IOUtils.toByteArray(new FileInputStream(selectedFile));
            ExtractGraphFromPDFFile extractGraphFromPDFFile = new ExtractGraphFromPDFFile(
                    new PDFFile(selectedFile.getName(), file));
            XYGraph graph = extractGraphFromPDFFile.execute();

            GraphEditor graphEditorForm = mainUI.getGraphEditorForm();
            graphEditorForm.addGoButtonListener(() -> onGoButtonClicked(graphEditorForm, graph));

            List<Serie> xyPointSeries = new ArrayList<>();
            for (Serie series : graph.getSeriesBySizeDesc()) {
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
        List<Serie> selectedElements = graphEditorForm.getSelectedElements();

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
