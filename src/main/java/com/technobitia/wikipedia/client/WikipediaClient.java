package com.technobitia.wikipedia.client;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.technobitia.wikipedia.request.WikipediaRequest.SIDEBAR_REQUEST_TYPE;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.collect.ImmutableMap;
import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.extractors.Extractor;
import com.technobitia.wikipedia.extractors.SidebarExtractor;
import com.technobitia.wikipedia.request.WikipediaRequest;

public class WikipediaClient {
    
    private ImmutableMap<String, Extractor> extractors;
    public WikipediaClient() {
        extractors = ImmutableMap.<String, Extractor>of(
                        SIDEBAR_REQUEST_TYPE, new SidebarExtractor());
        
    }
    
    public String extractInformation(WikipediaRequest request) throws WikipediaException {
        checkNotNull(request);
        String result = null;

        String url = request.getUrl();
        String requestType = request.getRequestedType();
        try {
            Document doc = Jsoup.connect(url).get();
            Extractor extractor = extractors.get(request.getRequestedType());
            if (extractor == null) {
                throw new IllegalStateException("Request type not supported: " + requestType);
            }
            result = extractor.extract(request, doc);
        } catch (IOException e) {
            throw new WikipediaException("I/O exception when requesting information to url:" + url);
        }
        
        return result;
    }
}
