package com.technobitia.wikipedia.request;

public class WikipediaRequest {
    private final String url;
    private final String requestedType;

    private WikipediaRequest(Builder requestBuilder) {
        this.url = requestBuilder.url;
        this.requestedType = requestBuilder.requestedType;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestedType() {
        return requestedType;
    }

    public static class Builder {
        private final String url;
        private String requestedType = "sidebar";

        private Builder(String url) {
            this.url = url;
        }

        private Builder withRequestedType(String type) {
            this.requestedType = type;
            return this;
        }

        public WikipediaRequest build() {
            return new WikipediaRequest(this);
        }
    }
}
