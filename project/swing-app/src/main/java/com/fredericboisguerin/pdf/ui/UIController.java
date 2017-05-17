package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.pdf.actions.ExportDataExcel;
import com.fredericboisguerin.pdf.actions.extract.ExtractGraphFromPDFFile;
import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.Serie;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;
import org.apache.poi.util.IOUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

            List<Serie> series = new ArrayList<>();
            series.addAll(graph.getSeriesBySizeDesc());
            graphEditorForm.setElements(series);

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
        List<UUID> selectedIds = selectedElements.stream()
                                                 .map(Serie::getUuid)
                                                 .collect(Collectors.toList());
        ExportDataExcel exportDataExcel = new ExportDataExcel(graph, xAxis, yAxis, selectedIds);
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
