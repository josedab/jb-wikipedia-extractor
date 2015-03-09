package com.technobitia.wikipedia.client;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.technobitia.wikipedia.request.WikipediaRequest.SIDEBAR_REQUEST_TYPE;

import org.jsoup.nodes.Document;

import com.google.common.collect.ImmutableMap;
import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.extractors.Extractor;
import com.technobitia.wikipedia.extractors.SidebarExtractor;
import com.technobitia.wikipedia.model.WikipediaInformation;
import com.technobitia.wikipedia.request.WikipediaRequest;
import com.technobitia.wikipedia.service.DocumentService;

public class WikipediaClient {
    
    private DocumentService documentService;
    private ImmutableMap<String, Extractor> extractors;
    
    public WikipediaClient() {
        extractors = ImmutableMap.<String, Extractor>of(
                        SIDEBAR_REQUEST_TYPE, new SidebarExtractor());
        documentService = new DocumentService();
        
    }
    
    public String extractInformation(WikipediaRequest request) throws WikipediaException {
        checkNotNull(request);
        String result = null;

        String requestType = request.getRequestedType();
        Document doc = documentService.getDocument(request);
        Extractor extractor = extractors.get(request.getRequestedType());
        
        if (extractor == null) {
            throw new IllegalStateException("Request type not supported: " + requestType);
        }
        result = extractor.extract(request, doc);

        return result;
    }
    public WikipediaInformation extractSidebarInformation(WikipediaRequest request) throws WikipediaException {
        checkNotNull(request);
        
        String sidebarHtml = extractInformation(request);
        WikipediaInformation wikipediaInformation = new WikipediaInformation.Builder(request.getUrl())
                                                                            .withPrettyHtml(sidebarHtml)
                                                                            .build();
        
        return wikipediaInformation;
    }
}
