package com.andrewswan.bgg4j.impl;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BggXmlApiVersionTest {

    @Test
    public void everyVersionShouldHaveABoardGameRepository() {
        Stream.of(BggXmlApiVersion.values()).forEach(
                apiVersion -> assertThat(apiVersion.getBoardGameRepository(), is(notNullValue())));
    }
}