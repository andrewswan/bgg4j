package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class XmlBoardGameRepositoryTest
{
    // Fixture
    private BoardGameRepository repository;

    @Before
    public void setUp() throws Exception
    {
        repository = new XmlBoardGameRepository();
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
        final BoardGame dieMacher = repository.get(gameId);

        // Check
        assertNotNull(dieMacher);
        assertEquals(1, dieMacher.getBggId());
        assertEquals("Die Macher", dieMacher.getPrimaryName());
        assertEquals(1986, dieMacher.getYearPublished());
        assertEquals("http://cf.geekdo-images.com/images/pic159509.jpg", dieMacher.getImageUrl());
        assertEquals("http://cf.geekdo-images.com/images/pic159509_t.jpg", dieMacher.getThumbnailUrl());
        assertEquals(5, dieMacher.getMaxPlayers());
        assertEquals(3, dieMacher.getMinPlayers());
    }

    @Test
    public void gameWithMultipleNamesShouldHaveCorrectPrimaryName() {
        // Invoke
        final BoardGame samurai = repository.get(3);

        // Check
        assertEquals("Samurai", samurai.getPrimaryName());
    }

    @Test
    public void searchingForNonExistentGameByNameShouldReturnEmptyList() {
        // Invoke
        final List<BoardGame> games = repository.search("Surely there's no game called this???");

        // Check
        assertNotNull(games);
        assertEquals(0, games.size());
    }

    @Test
    public void searchingForGameWithSpaceInNameShouldWork() {
        // Set up
        final String name = "Die Macher";

        // Invoke
        final List<BoardGame> matches = repository.search(name);

        // Check
        assertNotNull(matches);
        assertEquals(1, matches.size());
        final BoardGame boardGame = matches.get(0);
        assertEquals(1, boardGame.getBggId());
        assertEquals(name, boardGame.getPrimaryName());
        assertEquals(1986, boardGame.getYearPublished());
    }
}
