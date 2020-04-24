package com.wiktorkielar.task.model;

public class Response {
    private boolean published;

    public Response() {
    }

    public Response(boolean published) {
        this.published = published;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        return published == response.published;
    }

    @Override
    public int hashCode() {
        return (published ? 1 : 0);
    }
}
