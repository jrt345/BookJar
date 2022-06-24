package com.example.bookjar.library;

import java.io.Serializable;
import java.util.Objects;

public class Chapter implements Serializable {

    private int page;
    private String htmlText;

    public Chapter(int page) {
        this(page, "");
    }

    public Chapter(int page, String htmlText) {
        this.page = page;
        this.htmlText = htmlText;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        if (page != chapter.page) return false;
        return Objects.equals(htmlText, chapter.htmlText);
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + (htmlText != null ? htmlText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "page=" + page +
                ", htmlText='" + htmlText + '\'' +
                '}';
    }
}
