package com.andrewswan.bgg4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test-related utility methods.
 */
public final class TestUtils {

    /**
     * Asserts that the given board game is Die Macher.
     *
     * @param game the game to check
     */
    public static void assertDieMacher(final BoardGame game) {
        assertNotNull(game);
        assertEquals(1, game.getBggId());
        assertEquals("Die Macher", game.getPrimaryName());
        assertEquals(1986, game.getYearPublished());
        assertEquals("http://cf.geekdo-images.com/images/pic159509.jpg", game.getImageUrl());
        assertEquals("http://cf.geekdo-images.com/images/pic159509_t.jpg", game.getThumbnailUrl());
        assertEquals(5, game.getMaxPlayers());
        assertEquals(3, game.getMinPlayers());
        assertEquals("Karl-Heinz Schmiel", game.getDesigner());
    }

    private TestUtils() {}
}
