package com.technobitia.wikipedia.extractors;

import org.jsoup.nodes.Document;

import com.technobitia.wikipedia.request.WikipediaRequest;

public interface Extractor {
    public String extract(WikipediaRequest request, Document doc);
}
