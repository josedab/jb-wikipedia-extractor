package com.technobitia.wikipedia.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.technobitia.wikipedia.exceptions.WikipediaException;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Test(expected = NullPointerException.class)
    public void whenGettingDocument_givenNullRequest_thenThrowNPE() throws WikipediaException {
        documentService.getDocument(null);
    }
}
