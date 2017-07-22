package com.fredericboisguerin.pdf.ui.graph.create.vaadin.graphinfo;

import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.ParameterViewModel;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

class DatasheetGraphParameterEditor extends VerticalLayout {
    private final Binder<ParameterViewModel> binder = new Binder<>();

    DatasheetGraphParameterEditor() {
        TextField nameTextField = new TextField("Variable parameter");
        binder.forField(nameTextField)
              .asRequired("Variable parameter name required")
              .bind(ParameterViewModel::getName, ParameterViewModel::setName);

        addComponent(nameTextField);
        setMargin(false);
    }

    public void setModel(ParameterViewModel model) {
        binder.setBean(model);
    }

    boolean validate() {
        return binder.validate().isOk();
    }
}
