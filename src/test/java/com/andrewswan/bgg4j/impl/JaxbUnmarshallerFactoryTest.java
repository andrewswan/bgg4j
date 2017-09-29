package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.impl.v1.BoardGameListV1;
import org.junit.Test;

import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.assertNotNull;

public class JaxbUnmarshallerFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToGetUnmarshallerForNonXmlRootElement() {
        // Invoke
        JaxbUnmarshallerFactory.getUnmarshaller(Object.class);
    }

    @Test
    public void shouldBeAbleToGetUnmarshallerForXmlRootElement() {
        // Invoke
        final Unmarshaller unmarshaller = JaxbUnmarshallerFactory.getUnmarshaller(BoardGameListV1.class);

        // Check
        assertNotNull(unmarshaller);
    }
}