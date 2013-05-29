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

    @XmlAttribute(name = "objectid")
    private int bggId;

    @XmlElement(name = "yearpublished")
    private int yearPublished;

    @XmlElement(name = "image")
    private String imageUrl;

    @XmlElement(name = "name")
    private List<String> names;

    @XmlElement(name = "thumbnail")
    private String thumbnailUrl;

    public int getBggId() {
        return bggId;
    }

    public String getImageUrl() {
        return imageUrl;
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
}
