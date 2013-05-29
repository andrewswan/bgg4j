package com.andrewswan.bgg4j;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * A board game on BGG.
 *
 * @since 1.0
 */
public class BoardGame {

    @XmlAttribute(name = "objectid", required = true)
    private int bggId;

    @XmlElement(name = "maxplayers")
    private int maxPlayers;

    @XmlElement(name = "minplayers")
    private int minPlayers;

    @XmlElement(name = "yearpublished")
    private int yearPublished;

    @XmlElement(name = "name")
    private List<String> names;

    @XmlElement(name = "image")
    private String imageUrl;

    @XmlElement(name = "thumbnail")
    private String thumbnailUrl;

    public int getBggId() {
        return bggId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public String getPrimaryName() {
        return names == null || names.isEmpty() ? null : names.get(0);
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    /**
     * Returns summary information about this game.
     *
     * @return a non-null summary
     */
    public BoardGameSummary getSummary() {
        return new BoardGameSummary(bggId, yearPublished, getPrimaryName());
    }
}
