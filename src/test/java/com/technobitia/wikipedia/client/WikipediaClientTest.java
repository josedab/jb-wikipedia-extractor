package com.technobitia.wikipedia.client;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.wikipedia.exceptions.WikipediaException;
import com.technobitia.wikipedia.request.WikipediaRequest;
import com.technobitia.wikipedia.service.DocumentService;

@RunWith(MockitoJUnitRunner.class)
public class WikipediaClientTest {

    private static final String WIKIPEDIA_URL = "http://en.wikipedia.org/wiki/Barack_Obama";
    private static final WikipediaRequest WIKIPEDIA_REQUEST = 
                                new WikipediaRequest.Builder(WIKIPEDIA_URL).build();
    @Mock
    private DocumentService documentServiceMock;
    
    @InjectMocks
    private WikipediaClient wikipediaClient;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() throws WikipediaException {
        wikipediaClient.extractInformation(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenRequestingSidebarInformation_givenNullRequest_thenThrowNPE() throws WikipediaException {
        wikipediaClient.extractSidebarInformation(null);
    }
    
    @Test(expected = WikipediaException.class)
    public void whenRequestingDoc_givenWikipediaException_thenThrowWikipediaException() throws WikipediaException {
        when(documentServiceMock.getDocument(WIKIPEDIA_REQUEST)).thenThrow(WikipediaException.class);
        wikipediaClient.extractSidebarInformation(WIKIPEDIA_REQUEST);
    }
}
