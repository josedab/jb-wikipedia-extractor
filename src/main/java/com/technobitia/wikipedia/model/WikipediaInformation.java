package com.technobitia.wikipedia.model;

public class WikipediaInformation {

    private String url;
    private String prettyHtml;

    private WikipediaInformation(Builder builder) {
        this.url = builder.url;
        this.prettyHtml = builder.prettyHtml;
    }

    public String getUrl() {
        return url;
    }

    public String getPrettyHtml() {
        return prettyHtml;
    }

    public static class Builder {
        private final String url;
        private String prettyHtml;

        public Builder(String url) {
            this.url = url;
        }

        public Builder withPrettyHtml(String prettyHtml) {
            this.prettyHtml = prettyHtml;
            return this;
        }

        public WikipediaInformation build() {
            return new WikipediaInformation(this);
        }
    }

}
