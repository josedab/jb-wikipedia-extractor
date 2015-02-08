package com.technobitia.wikipedia.extractors;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.technobitia.wikipedia.request.WikipediaRequest;

public class SidebarExtractor implements Extractor {

    private static final String INFOBOX_SELECTOR = "table.infobox";
    
    private static final String STYLE_ATTRIBUTE = "style";

    public String extract(WikipediaRequest request, Document doc) {
        checkNotNull(request);
        checkNotNull(doc);
        
        String result = null;
        Elements results = doc.select(INFOBOX_SELECTOR);
        Element infoBox = results.first();
        if (infoBox != null) {
            infoBox = postProcessElement(infoBox);
            if (request.getContentType().equalsIgnoreCase("html")) {
                result = infoBox.toString();
            } else {
                result = infoBox.text();
            }
        }

        return result;
    }
    
    private Element postProcessElement(Element element) {
        element.removeAttr(STYLE_ATTRIBUTE);
        return element;
    }

}
