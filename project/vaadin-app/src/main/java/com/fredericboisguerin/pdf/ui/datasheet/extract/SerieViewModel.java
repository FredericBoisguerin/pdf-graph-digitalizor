package com.fredericboisguerin.pdf.ui.datasheet.extract;

class SerieViewModel {
    private final int id;
    private final String name;
    private final int nbOfPoints;

    public SerieViewModel(int currentSerieId, String name, int nbOfPoints) {
        this.id = currentSerieId;
        this.name = name;
        this.nbOfPoints = nbOfPoints;
    }

    @Override
    public String toString() {
        return String.format("%s (%s points)", name, nbOfPoints);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SerieViewModel that = (SerieViewModel) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
