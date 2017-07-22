package com.fredericboisguerin.pdf.ui.graph.create.vaadin.graphinfo;

import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.DatasheetGraphInfoViewModel;
import com.vaadin.ui.VerticalLayout;

public class VaadinDatasheetGraphInfoEditor extends VerticalLayout {

    private final DatasheetGraphAxisEditor xAxisEditor = new DatasheetGraphAxisEditor(
            "X");
    private final DatasheetGraphAxisEditor yAxisEditor = new DatasheetGraphAxisEditor(
            "Y");
    private final DatasheetGraphParameterEditor parameterEditor = new DatasheetGraphParameterEditor();

    public VaadinDatasheetGraphInfoEditor() {
        addComponents(xAxisEditor, yAxisEditor, parameterEditor);
        setMargin(false);
    }

    public void setModel(DatasheetGraphInfoViewModel model) {
        xAxisEditor.setModel(model.getxAxis());
        yAxisEditor.setModel(model.getyAxis());
        parameterEditor.setModel(model.getParameter());
    }

    public boolean valid() {
        boolean xAxisValid = xAxisEditor.validate();
        boolean yAxisValid = yAxisEditor.validate();
        boolean parameterValid = parameterEditor.validate();
        return xAxisValid && yAxisValid && parameterValid;
    }
}
