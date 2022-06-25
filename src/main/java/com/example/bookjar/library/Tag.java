package com.example.bookjar.library;

public class Tag {

    private String string;

    public Tag(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        return string.equals(tag.string);
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @Override
    public String toString() {
        return "Tag{" +
                "string='" + string + '\'' +
                '}';
    }
}
