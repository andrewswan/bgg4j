package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGameRepository;
import com.andrewswan.bgg4j.impl.v1.XmlBoardGameRepositoryV1;

/**
 * The supported versions of the BGG XML API.
 *
 * @since 2.0
 */
public enum BggXmlApiVersion {

    // Keep these in chronological order (newest last)

    V1 {
        @Override
        public BoardGameRepository getBoardGameRepository() {
            return new XmlBoardGameRepositoryV1();
        }
    };

    /**
     * Returns the latest version.
     *
     * @return see above
     */
    public static BggXmlApiVersion getLatest() {
        final BggXmlApiVersion[] values = values();
        return values[values.length - 1];
    }

    /**
     * Returns a {@link BoardGameRepository} implementation for this version of the API.
     *
     * @return see above
     */
    public abstract BoardGameRepository getBoardGameRepository();
}
