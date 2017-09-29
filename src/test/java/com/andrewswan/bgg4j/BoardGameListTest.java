package com.andrewswan.bgg4j;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

import static com.andrewswan.bgg4j.TestUtils.assertDieMacher;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Integration test of the unmarshalling of various sample BGG XML responses into {@link BoardGameList}s.
 */
public class BoardGameListTest {

    @Test
    public void listShouldBeEmptyWhenGettingInvalidBggId() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("id_not_found.xml");

        // Check
        assertThat(boardGameList.getBoardGames(), is(emptyList()));
        assertThat(boardGameList.getSummaries(), is(emptyList()));
        assertThat(boardGameList.getOnlyEntry(), is(empty()));
    }

    @Test
    public void listShouldBeEmptyWhenUnmarshallingEmptySearchResults() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("no_search_results.xml");

        // Check
        assertThat(boardGameList.getBoardGames(), is(emptyList()));
        assertThat(boardGameList.getSummaries(), is(emptyList()));
        assertThat(boardGameList.getOnlyEntry(), is(empty()));
    }

    @Test
    public void unmarshallerShouldUnmarshalGameDetailsFromBggFormatXmlFile() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("die_macher.xml");

        // Check
        assertNotNull(boardGameList);
        assertDieMacher(boardGameList.getOnlyEntry().orElseThrow(IllegalStateException::new));
    }

    @Test
    public void unmarshallerShouldUnmarshalPrimaryNameWhenOtherNamesExist() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("samurai.xml");

        // Check
        assertNotNull(boardGameList);
        final BoardGame boardGame = boardGameList.getOnlyEntry().orElseThrow(IllegalStateException::new);
        assertEquals("Samurai", boardGame.getPrimaryName());
    }

    @Test
    public void unmarshallerShouldUnmarshalGameSummaryFromExactSearchResults() throws JAXBException {
        // Invoke
        final BoardGameList boardGameList = unmarshal("steam_exact.xml");

        // Check
        assertNotNull(boardGameList);
        final BoardGameSummary gameSummary = boardGameList.getOnlyEntry()
                .map(BoardGame::getSummary)
                .orElseThrow(IllegalStateException::new);
        assertEquals(27833, gameSummary.getBggId());
        assertEquals(2009, gameSummary.getYearPublished());
        assertEquals("Steam", gameSummary.getPrimaryName());
    }

    private BoardGameList unmarshal(final String xmlFile) throws JAXBException {
        final InputStream xmlStream = getClass().getResourceAsStream(xmlFile);
        return (BoardGameList) BoardGameList.UNMARSHALLER.unmarshal(xmlStream);
    }
}
