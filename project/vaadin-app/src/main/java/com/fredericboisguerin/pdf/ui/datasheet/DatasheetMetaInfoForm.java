package com.fredericboisguerin.pdf.ui.datasheet;

import com.vaadin.data.Binder;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class DatasheetMetaInfoForm extends FormLayout {

    private final Binder<Model> binder;

    public DatasheetMetaInfoForm() {
        binder = new Binder<>();

        TextField referenceTextField = new TextField("Reference");
        binder.forField(referenceTextField)
              .bind(Model::getReference, Model::setReference);

        TextField supplierTextField = new TextField("Supplier");
        binder.forField(supplierTextField)
              .bind(Model::getSupplier, Model::setSupplier);

        addComponents(referenceTextField, supplierTextField);
    }

    public void clearFields() {
        binder.setBean(new Model("", ""));
    }

    public Model getViewModel() {
        return binder.getBean();
    }

    public void setModel(Model model) {
        binder.setBean(model);
    }

    public static class Model {
        private String reference;
        private String supplier;

        public Model(String reference, String supplier) {
            this.reference = reference;
            this.supplier = supplier;
        }

        public String getReference() {
            return reference;
        }

        private void setReference(String reference) {
            this.reference = reference;
        }

        public String getSupplier() {
            return supplier;
        }

        private void setSupplier(String supplier) {
            this.supplier = supplier;
        }
    }
}
