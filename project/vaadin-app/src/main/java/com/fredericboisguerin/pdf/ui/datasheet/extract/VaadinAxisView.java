package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.util.Collection;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToFloatConverter;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.Setter;
import com.vaadin.shared.Registration;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

class VaadinAxisView extends VerticalLayout {
    private static final String DECIMAL_NUMBER_EXPECTED = "Decimal number expected";
    private static final String SCALE = "Scale";
    private static final String MIN = "Min";
    private static final String MAX = "Max";
    private static final String MANDATORY_FIELD = "Mandatory field";

    private final Binder<AxisViewModel> binder = new Binder<>();
    private final RadioButtonGroup<AxisKind> axisKindRadioButtonGroup = new RadioButtonGroup<>(
            SCALE);
    private final TextField minTextField;

    VaadinAxisView() {
        minTextField = new TextField(MIN);
        bindWithModel(minTextField, AxisViewModel::getMin, AxisViewModel::setMin);

        TextField maxTextField = new TextField(MAX);
        bindWithModel(maxTextField, AxisViewModel::getMax, AxisViewModel::setMax);

        binder.forField(axisKindRadioButtonGroup)
              .asRequired("")
              .bind(AxisViewModel::getSelectedAxisKind, AxisViewModel::setSelectedAxisKind);

        addComponents(minTextField, maxTextField, axisKindRadioButtonGroup);
    }

    private void bindWithModel(TextField textField, ValueProvider<AxisViewModel, Float> getter,
            Setter<AxisViewModel, Float> setter) {
        StringToFloatConverter converter = new EmptyForNullStringToFloatConverter(DECIMAL_NUMBER_EXPECTED);
        binder.forField(textField)
              .withConverter(converter)
              .asRequired(MANDATORY_FIELD)
              .bind(getter, setter);
    }

    void setModel(AxisViewModel model) {
        Collection<AxisKind> selectableAxisKinds = model.getSelectableAxisKinds();
        axisKindRadioButtonGroup.setDataProvider(DataProvider.ofCollection(selectableAxisKinds));
        binder.setBean(model);
    }

    public boolean isValid() {
        return binder.isValid();
    }

    public Registration addStatusChangeListener(StatusChangeListener listener) {
        return binder.addStatusChangeListener(listener);
    }

    public void getFocus() {
        minTextField.focus();
    }
}
