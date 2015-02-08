package com.technobitia.wikipedia.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WikipediaClientTest {

    @InjectMocks
    private WikipediaClient wikipediaClient;

    @Test(expected = NullPointerException.class)
    public void whenExtractingInformation_givenNullRequest_thenThrowNPE() {
        wikipediaClient.extractInformation(null);
    }
}
