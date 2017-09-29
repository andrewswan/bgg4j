package com.andrewswan.bgg4j;

/**
 * A board game on BGG.
 *
 * @since 1.0
 */
public interface BoardGame {

    int getBggId();

    String getDesigner();

    String getImageUrl();

    int getMaxPlayers();

    int getMinPlayers();

    String getPrimaryName();

    int getYearPublished();

    String getThumbnailUrl();

    /**
     * Returns summary information about this game.
     *
     * @return a non-null summary
     */
    BoardGameSummary getSummary();

    /**
     * Indicates whether this instance represents a real game in BGG. This is
     * necessary because BGG can return a "game" with an invalid ID to
     * indicate "no results found" (instead of a 404).
     *
     * @return <code>false</code> if this is a dummy "not found" entry
     */
    default boolean isRealEntry() {
        return getBggId() > 0;
    }
}
