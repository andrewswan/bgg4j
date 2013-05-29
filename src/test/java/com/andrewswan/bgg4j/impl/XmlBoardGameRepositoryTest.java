package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameRepository;
import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void gameWithMultipleNamesShouldHaveCorrectPrimaryName() {
        // Invoke
        final BoardGame samurai = repository.get(3);

        // Check
        assertEquals("Samurai", samurai.getPrimaryName());
    }
}
