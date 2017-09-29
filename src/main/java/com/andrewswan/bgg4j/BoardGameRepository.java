package com.andrewswan.bgg4j;

import java.util.List;
import java.util.Optional;

/**
 * A DDD-style repository for the {@link BoardGame} domain type. This is the main interface that client programs will
 * use to access BGG.
 *
 * @since 1.0
 */
public interface BoardGameRepository {

    /**
     * Returns the details of the board game with the given BGG ID.
     *
     * @param bggId the BGG ID of the game to retrieve
     * @return see above
     */
    Optional<BoardGame> get(int bggId);

    /**
     * Searches for board games whose names contain the given string, case-insensitive.
     *
     * @param name the name for which to search (required)
     * @return a non-null list of games (might be empty)
     */
    List<BoardGameSummary> search(String name);

    /**
     * Searches for the game with the given exact name, case-insensitive.
     *
     * @param name the name for which to search (required)
     * @return see above
     */
    Optional<BoardGameSummary> searchExact(String name);
}
