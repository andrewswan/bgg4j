package com.andrewswan.bgg4j;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

import static com.andrewswan.bgg4j.TestUtils.assertDieMacher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BoardGameListTest {

    @Test
    public void listShouldBeEmptyWhenGettingInvalidBggId() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("id_not_found.xml");

        // Check
        assertEquals(0, boardGameList.getBoardGames().size());
        assertEquals(0, boardGameList.getSummaries().size());
        assertNull(boardGameList.getOnlyEntry());
    }

    @Test
    public void listShouldBeEmptyWhenUnmarshallingEmptySearchResults() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("no_search_results.xml");

        // Check
        assertEquals(0, boardGameList.getBoardGames().size());
        assertEquals(0, boardGameList.getSummaries().size());
        assertNull(boardGameList.getOnlyEntry());
    }

    @Test
    public void unmarshallerShouldUnmarshalGameDetailsFromBggFormatXmlFile() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("die_macher.xml");

        // Check
        assertNotNull(boardGameList);
        assertDieMacher(boardGameList.getOnlyEntry());
    }

    @Test
    public void unmarshallerShouldUnmarshalPrimaryNameWhenOtherNamesExist() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("samurai.xml");

        // Check
        assertNotNull(boardGameList);
        final BoardGame boardGame = boardGameList.getOnlyEntry();
        assertEquals("Samurai", boardGame.getPrimaryName());
    }

    @Test
    public void unmarshallerShouldUnmarshalGameSummaryFromExactSearchResults() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("steam_exact.xml");

        // Check
        assertNotNull(boardGameList);
        final BoardGameSummary gameSummary = boardGameList.getOnlyEntry().getSummary();
        assertEquals(27833, gameSummary.getBggId());
        assertEquals(2009, gameSummary.getYearPublished());
        assertEquals("Steam", gameSummary.getPrimaryName());
    }

    private BoardGameList unmarshal(final String xmlFile) throws JAXBException {
        final InputStream xmlStream = getClass().getResourceAsStream(xmlFile);
        return (BoardGameList) BoardGameList.UNMARSHALLER.unmarshal(xmlStream);
    }
}
