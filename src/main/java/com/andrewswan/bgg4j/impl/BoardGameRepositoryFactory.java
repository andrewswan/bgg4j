package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGameRepository;

/**
 * Factory for {@link BoardGameRepository} instances. Allows clients to remain decoupled
 * from concrete implementation classes and whether they are thread-safe.
 *
 * @since 2.0
 */
public final class BoardGameRepositoryFactory {

    /**
     * Returns a repository that uses the latest version of the BGG XML API.
     *
     * @return see above
     */
    public static BoardGameRepository getInstance() {
        return getInstance(BggXmlApiVersion.getLatest());
    }

    /**
     * Returns a repository that uses the given version of the BGG XML API.
     *
     * @param apiVersion the API version to use
     * @return see above
     */
    public static BoardGameRepository getInstance(final BggXmlApiVersion apiVersion) {
        return apiVersion.getBoardGameRepository();
    }

    private BoardGameRepositoryFactory() {}
}
