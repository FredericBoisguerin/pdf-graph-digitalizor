package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.io.Serializable;

class AxisKind implements Serializable {
    private final String name;

    public AxisKind(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AxisKind axisKind = (AxisKind) o;

        return name != null ? name.equals(axisKind.name) : axisKind.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }

    public AxisKind duplicate() {
        return new AxisKind(name);
    }
}
