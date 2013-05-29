package com.andrewswan.bgg4j;

public interface BoardGameRepository
{
    /**
     * Returns the board game with the given BGG ID.
     *
     * @param bggId the BGG ID of the game to retrieve
     * @return null if there is no such board game
     */
    BoardGame get(int bggId);
}
