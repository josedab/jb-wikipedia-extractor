package com.technobitia.wikipedia.service;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.request.WikipediaRequest;

public class DocumentService {
    
    public Document getDocument(WikipediaRequest request)
            throws WikipediaException {
        checkNotNull(request);

        Document doc = null;
        String url = request.getUrl();
        
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new WikipediaException("I/O exception when requesting information to url:" + url);
        }

        return doc;
    }
}
