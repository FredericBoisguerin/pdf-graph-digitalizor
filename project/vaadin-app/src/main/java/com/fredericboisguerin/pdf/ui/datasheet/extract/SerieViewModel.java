package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.util.UUID;

class SerieViewModel {
    private final UUID uuid;
    private final int displayedId;
    private final RawPoints rawPoints;

    SerieViewModel(UUID uuid, int displayedId, RawPoints rawPoints) {
        this.uuid = uuid;
        this.displayedId = displayedId;
        this.rawPoints = rawPoints;
    }

    RawPoints getRawPoints() {
        return rawPoints;
    }

    UUID getUUID() {
        return uuid;
    }

    @Override
    public String toString() {
        return String.format("Série n°%d (%s points)", displayedId, rawPoints.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerieViewModel that = (SerieViewModel) o;

        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
