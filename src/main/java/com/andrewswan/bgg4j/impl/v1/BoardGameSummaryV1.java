package com.andrewswan.bgg4j.impl.v1;

import com.andrewswan.bgg4j.BoardGameSummary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * A board game's summary details on BGG.
 *
 * @since 1.0
 */
public class BoardGameSummaryV1 implements BoardGameSummary {

    @XmlAttribute(name = "objectid", required = true)
    private int bggId;

    @XmlElement(name = "yearpublished")
    private int yearPublished;

    @XmlElement(name = "primaryName")
    private String primaryName;

    /**
     * No-arg constructor required by JAXB.
     */
    @SuppressWarnings("unused")
    private BoardGameSummaryV1() {
        this(0, 0, "overwritten by JAXB");
    }

    /**
     * Constructor.
     *
     * @param bggId the ID of this game on BGG
     * @param yearPublished the year in which this game was published
     * @param primaryName the primary name of this game (required)
     */
    public BoardGameSummaryV1(
            final int bggId, final int yearPublished, final String primaryName) {
        if (primaryName == null || "".equals(primaryName.trim())) {
            throw new IllegalArgumentException("Invalid name '" + primaryName + "'");
        }
        this.bggId = bggId;
        this.primaryName = primaryName;
        this.yearPublished = yearPublished;
    }

    public int getBggId() {
        return bggId;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public int getYearPublished() {
        return yearPublished;
    }
}
