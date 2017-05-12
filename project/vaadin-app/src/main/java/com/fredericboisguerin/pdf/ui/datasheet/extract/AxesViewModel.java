package com.fredericboisguerin.pdf.ui.datasheet.extract;

class AxesViewModel {

    private final AxisViewModel axisX;
    private final AxisViewModel axisY;

    AxesViewModel(AxisViewModel axisX, AxisViewModel axisY) {
        this.axisX = axisX;
        this.axisY = axisY;
    }

    public AxisViewModel getAxisX() {
        return axisX;
    }

    public AxisViewModel getAxisY() {
        return axisY;
    }
}
