package com.andrewswan.bgg4j.impl.v1;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameSummary;
import org.junit.Test;

import java.io.InputStream;

import static com.andrewswan.bgg4j.TestUtils.assertDieMacher;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Integration test of the unmarshalling of various sample BGG XML responses into {@link BoardGameListV1}s.
 */
public class BoardGameListV1Test {

    @Test
    public void listShouldBeEmptyWhenGettingInvalidBggId() throws Exception {
        // Invoke
        final BoardGameListV1 boardGameList = unmarshal("id_not_found.xml");

        // Check
        assertThat(boardGameList.getBoardGames(), is(emptyList()));
        assertThat(boardGameList.getSummaries(), is(emptyList()));
        assertThat(boardGameList.getOnlyEntry(), is(empty()));
    }

    @Test
    public void listShouldBeEmptyWhenUnmarshallingEmptySearchResults() throws Exception {
        // Invoke
        final BoardGameListV1 boardGameList = unmarshal("no_search_results.xml");

        // Check
        assertThat(boardGameList.getBoardGames(), is(emptyList()));
        assertThat(boardGameList.getSummaries(), is(emptyList()));
        assertThat(boardGameList.getOnlyEntry(), is(empty()));
    }

    @Test
    public void unmarshallerShouldUnmarshalGameDetailsFromBggFormatXmlFile() throws Exception {
        // Invoke
        final BoardGameListV1 boardGameList = unmarshal("die_macher.xml");

        // Check
        assertNotNull(boardGameList);
        assertDieMacher(boardGameList.getOnlyEntry().orElseThrow(IllegalStateException::new));
    }

    @Test
    public void unmarshallerShouldUnmarshalPrimaryNameWhenOtherNamesExist() throws Exception {
        // Invoke
        final BoardGameListV1 boardGameList = unmarshal("samurai.xml");

        // Check
        assertNotNull(boardGameList);
        final com.andrewswan.bgg4j.BoardGame boardGame = boardGameList.getOnlyEntry().orElseThrow(IllegalStateException::new);
        assertEquals("Samurai", boardGame.getPrimaryName());
    }

    @Test
    public void unmarshallerShouldUnmarshalGameSummaryFromExactSearchResults() throws Exception {
        // Invoke
        final BoardGameListV1 boardGameList = unmarshal("steam_exact.xml");

        // Check
        assertNotNull(boardGameList);
        final BoardGameSummary gameSummary = boardGameList.getOnlyEntry()
                .map(BoardGame::getSummary)
                .orElseThrow(IllegalStateException::new);
        assertEquals(27833, gameSummary.getBggId());
        assertEquals(2009, gameSummary.getYearPublished());
        assertEquals("Steam", gameSummary.getPrimaryName());
    }

    private BoardGameListV1 unmarshal(final String xmlFile) throws Exception {
        try (final InputStream xmlStream = getClass().getResourceAsStream(xmlFile)) {
            return (BoardGameListV1) BoardGameListV1.UNMARSHALLER.unmarshal(xmlStream);
        }
    }
}
