package com.andrewswan.bgg4j;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

import static com.andrewswan.bgg4j.TestUtils.assertDieMacher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BoardGameListTest {

    @Test
    public void unmarshallerShouldUnmarshalFromBggFormatXmlFile() throws JAXBException {
        // Set up
        final InputStream xmlStream = getClass().getResourceAsStream("die_macher.xml");

        // Invoke
        final Object object = BoardGameList.UNMARSHALLER.unmarshal(xmlStream);

        // Check
        assertNotNull(object);
        final BoardGameList boardGameList = (BoardGameList) object;
        assertDieMacher(boardGameList.getOnlyEntry());
    }
}
