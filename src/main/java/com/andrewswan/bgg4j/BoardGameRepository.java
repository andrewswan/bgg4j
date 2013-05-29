package com.andrewswan.bgg4j;

/**
 * A DDD-style repository for the {@link BoardGame} domain type. This is the main interface that client programs will
 * use to access BGG.
 *
 * @since 1.0
 */
public interface BoardGameRepository {
    /**
     * Returns the board game with the given BGG ID.
     *
     * @param bggId the BGG ID of the game to retrieve
     * @return null if there is no such board game
     */
    BoardGame get(int bggId);
}
