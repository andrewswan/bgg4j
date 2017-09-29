package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGameRepository;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BoardGameRepositoryFactoryTest {

    @Test
    public void shouldProvideBoardGameRepositoryForLatestApiVersion() {
        // Invoke
        final BoardGameRepository repository = BoardGameRepositoryFactory.getInstance();

        // Check
        assertThat(repository, is(notNullValue()));
    }

    @Test
    public void shouldProvideBoardGameRepositoryForEveryApiVersion() {
        Stream.of(BggXmlApiVersion.values()).forEach(
                apiVersion -> assertThat(BoardGameRepositoryFactory.getInstance(apiVersion), is(notNullValue())));
    }
}