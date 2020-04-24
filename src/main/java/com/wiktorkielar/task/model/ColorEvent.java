package com.wiktorkielar.task.model;

public class ColorEvent {

    private String color;

    public ColorEvent() {
    }

    public ColorEvent(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorEvent that = (ColorEvent) o;

        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
