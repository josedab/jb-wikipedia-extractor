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
        removeUnnecessaryAttributes(element);
        convertLinksToAbsoluteUrls(element);
        addExternalTargetToLinks(element);
        return element;
    }

    /**
     * Removal of the syle attribute at top level.
     * This will let the sidebar to be rendered on fluid elements.
     * @param element
     */
    private void removeUnnecessaryAttributes(Element element) {
        element.removeAttr(STYLE_ATTRIBUTE);
    }

    /**
     * Replaces relative urls with absolute urls.
     * This will let the user click on any link even if they are not on wikipedia
     * @param element
     */
    private void convertLinksToAbsoluteUrls(Element element) {
        Elements urls = element.select("a[href]");
        for (Element urlElement : urls) {
            urlElement.attr("href", urlElement.absUrl("href"));
        }
    }
    
    /**
     * Adds the target:_blank to every link contained on the element
     * This will let links to be clicked and open a new tab instead of using the same one.
     * @param element
     */
    private void addExternalTargetToLinks(Element element) {
        Elements urls = element.select("a[href]");
        for (Element urlElement : urls) {
            urlElement.attr("target", "_blank");
        }
    }

}
