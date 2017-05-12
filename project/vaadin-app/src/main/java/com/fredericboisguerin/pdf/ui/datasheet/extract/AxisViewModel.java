package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

class AxisViewModel implements Serializable {

    private final Collection<AxisKind> selectableAxisKinds;
    private Float min ;
    private Float max;
    private AxisKind selectedAxisKind;

    public AxisViewModel(Collection<AxisKind> selectableAxisKinds) {
        this.selectableAxisKinds = Collections.unmodifiableCollection(selectableAxisKinds);
    }

    public Collection<AxisKind> getSelectableAxisKinds() {
        return selectableAxisKinds;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public AxisKind getSelectedAxisKind() {
        return selectedAxisKind;
    }

    public void setSelectedAxisKind(AxisKind selectedAxisKind) {
        this.selectedAxisKind = selectedAxisKind;
    }
}
