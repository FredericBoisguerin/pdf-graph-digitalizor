package com.fredericboisguerin.pdf.ui.datasheet.extract;

class SerieViewModel {
    private final int id;
    private final RawPoints rawPoints;

    SerieViewModel(int id, RawPoints rawPoints) {
        this.id = id;
        this.rawPoints = rawPoints;
    }

    RawPoints getRawPoints() {
        return rawPoints;
    }

    int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Série n°%d (%s points)", id, rawPoints.size());
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
