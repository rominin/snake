package ru.rominin.snakegame;

import java.util.Objects;

public class Section {
    private int x, y;

    public Section(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return x == section.x &&
                y == section.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
