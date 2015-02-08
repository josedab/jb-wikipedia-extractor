package com.technobitia.wikipedia.extractors;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.request.WikipediaRequest;

@RunWith(MockitoJUnitRunner.class)
public class SidebarExtractorTest {

    @Mock
    private Document documentMock;
    
    @Mock
    private WikipediaRequest wikipediaRequestMock;
    
    @InjectMocks
    private SidebarExtractor sidebarExtractor;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() throws WikipediaException {
        sidebarExtractor.extract(null, documentMock);
    }

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullDocument_thenThrowNPE() throws WikipediaException {
        sidebarExtractor.extract(wikipediaRequestMock, null);
    }
}
