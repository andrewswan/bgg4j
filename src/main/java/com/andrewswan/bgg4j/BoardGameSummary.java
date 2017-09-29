package com.andrewswan.bgg4j;

/**
 * A board game's summary details on BGG.
 *
 * @since 1.0
 */
public interface BoardGameSummary {

    /**
     * Returns the BGG ID of this game.
     *
     * @return see above
     */
    int getBggId();

    /**
     * Returns this game's primary name.
     *
     * @return see above
     */
    String getPrimaryName();

    /**
     * Returns the year in which this game was published.
     *
     * @return see above
     */
    int getYearPublished();
}
