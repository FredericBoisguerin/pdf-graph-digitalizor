package com.fredericboisguerin.pdf.ui.datasheet.extract;

class SerieViewModel {
    private final String name;
    private final int nbOfPoints;

    public SerieViewModel(String name, int nbOfPoints) {
        this.name = name;
        this.nbOfPoints = nbOfPoints;
    }

    @Override
    public String toString() {
        return String.format("%s (%s points)", name, nbOfPoints);
    }
}
