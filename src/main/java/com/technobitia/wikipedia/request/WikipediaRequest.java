package com.technobitia.wikipedia.request;

public class WikipediaRequest {
    
    public static final String SIDEBAR_REQUEST_TYPE = "sidebar";
    public static final String CONTENT_TYPE = "html";
    
    private final String url;
    private final String requestedType;
    private final String contentType;

    private WikipediaRequest(Builder requestBuilder) {
        this.url = requestBuilder.url;
        this.requestedType = requestBuilder.requestedType;
        this.contentType = requestBuilder.contentType;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestedType() {
        return requestedType;
    }

    public String getContentType() {
        return contentType;
    }

    public static class Builder {
        private final String url;
        private String requestedType = SIDEBAR_REQUEST_TYPE;
        private String contentType = CONTENT_TYPE;

        public Builder(String url) {
            this.url = url;
        }

        public Builder withRequestedType(String type) {
            this.requestedType = type;
            return this;
        }
        
        public Builder withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public WikipediaRequest build() {
            return new WikipediaRequest(this);
        }
    }
}
