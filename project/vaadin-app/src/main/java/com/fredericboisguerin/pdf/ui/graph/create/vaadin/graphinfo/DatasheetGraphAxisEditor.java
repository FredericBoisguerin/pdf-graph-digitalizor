package com.fredericboisguerin.pdf.ui.graph.create.vaadin.graphinfo;

import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.AxisViewModel;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

class DatasheetGraphAxisEditor extends VerticalLayout {
    private final Binder<AxisViewModel> binder = new Binder<>();

    DatasheetGraphAxisEditor(String captionPrefix) {
        TextField nameTextField = new TextField(captionPrefix + " axis");
        binder.forField(nameTextField)
              .asRequired("Axis name required")
              .bind(AxisViewModel::getName, AxisViewModel::setName);

        addComponent(nameTextField);
        setMargin(false);
    }

    public void setModel(AxisViewModel model) {
        binder.setBean(model);
    }

    boolean validate() {
        return binder.validate().isOk();
    }
}
