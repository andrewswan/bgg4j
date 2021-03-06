package com.andrewswan.bgg4j.impl.v1;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameRepository;
import com.andrewswan.bgg4j.BoardGameSummary;
import com.andrewswan.bgg4j.impl.v1.XmlBoardGameRepositoryV1;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static com.andrewswan.bgg4j.TestUtils.assertDieMacher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration test of reading from the live BGG XML API.
 *
 * Will not be run by Maven but can be run via the IDE.
 */
@Ignore("Requires BGG to be up and reachable")
public class XmlBoardGameRepositoryV1Test {

    // Fixture
    private BoardGameRepository repository;

    @Before
    public void setUp() {
        repository = new XmlBoardGameRepositoryV1();
    }

    @Test
    public void nonExistentGameShouldBeNull() {
        assertNull(repository.get(Integer.MAX_VALUE));
    }

    @Test
    public void existingGameShouldContainRequiredValues() {
        // Set up
        final int gameId = 1;

        // Invoke
        final BoardGame dieMacher = repository.get(gameId).orElseThrow(IllegalStateException::new);

        // Check
        assertDieMacher(dieMacher);
    }

    @Test
    public void gameWithMultipleNamesShouldHaveCorrectPrimaryName() {
        // Invoke
        final BoardGame samurai = repository.get(3).orElseThrow(IllegalStateException::new);

        // Check
        assertEquals("Samurai", samurai.getPrimaryName());
    }

    @Test
    public void searchingForGamesWithBogusNameShouldReturnEmptyList() {
        // Invoke
        final List<?> games = repository.search("Surely there's no game called this???");

        // Check
        assertNotNull(games);
        assertEquals(0, games.size());
    }

    @Test
    public void searchingForGamesWithSpaceInNameShouldWork() {
        // Set up
        final String name = "Die Macher";

        // Invoke
        final List<BoardGameSummary> matches = repository.search(name);

        // Check
        assertNotNull(matches);
        assertEquals(1, matches.size());
        final BoardGameSummary boardGame = matches.get(0);
        assertEquals(1, boardGame.getBggId());
        assertEquals(name, boardGame.getPrimaryName());
        assertEquals(1986, boardGame.getYearPublished());
    }

    @Test
    public void searchingForGamesWithSubstringInCommonShouldReturnMultipleHits() {
        // Invoke
        final List<?> games = repository.search("Steam");

        // Check
        assertTrue("Actual hits = " + games.size(), games.size() > 1);
    }

    @Test
    public void searchingForExactNameThatIsPartOfOtherNamesShouldReturnOneHit() {
        // Set up
        final String name = "Steam";
        assertTrue(repository.search(name).size() > 1);

        // Invoke
        final BoardGameSummary summary = repository.searchExact(name).orElseThrow(IllegalStateException::new);

        // Check
        assertNotNull(summary);
        assertEquals(name, summary.getPrimaryName());
    }
}
