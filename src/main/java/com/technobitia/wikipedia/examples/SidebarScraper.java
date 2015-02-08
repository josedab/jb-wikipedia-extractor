package com.technobitia.wikipedia.examples;

import com.technobitia.wikipedia.client.WikipediaClient;
import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.request.WikipediaRequest;

public class SidebarScraper {

    public static void main(String[] args) throws WikipediaException {
        String url = "http://en.wikipedia.org/wiki/Baltimore_Ravens";
        WikipediaRequest request = new WikipediaRequest.Builder(url)
                                                        .withRequestedType(WikipediaRequest.SIDEBAR_REQUEST_TYPE)
                                                        .build();
        WikipediaClient client = new WikipediaClient();
        String text = client.extractInformation(request);
        System.out.println(text);

    }

}
