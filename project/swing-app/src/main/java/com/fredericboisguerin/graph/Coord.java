package com.fredericboisguerin.graph;

public class Coord {
    private final float coord;

    private Coord(float coord) {
        this.coord = coord;
    }

    Coord translation(float offset) {
        return new Coord(coord + offset);
    }

    float diff(Coord other) {
        return this.coord - other.coord;
    }

    float ratio(Coord other) {
        return this.coord / other.coord;
    }

    public static Coord of(float coord) {
        return new Coord(coord);
    }

    public float getCoord() {
        return coord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Coord coord1 = (Coord) o;

        return Float.compare(coord1.coord, coord) == 0;
    }

    @Override
    public int hashCode() {
        return (coord != +0.0f ? Float.floatToIntBits(coord) : 0);
    }

    @Override
    public String toString() {
        return String.format("%.2f",coord);
    }
}
