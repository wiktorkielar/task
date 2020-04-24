package com.wiktorkielar.task.model;

public class ColorRequest {

    private boolean publish;
    private String color;

    public ColorRequest() {
    }

    public ColorRequest(boolean publish, String color) {
        this.publish = publish;
        this.color = color;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
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

        ColorRequest that = (ColorRequest) o;

        if (publish != that.publish) return false;
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = (publish ? 1 : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
