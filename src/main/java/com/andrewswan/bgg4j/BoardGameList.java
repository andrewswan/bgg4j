package com.andrewswan.bgg4j;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * A wrapper around a list of {@link BoardGame}s. This class only exists because JAXB requires a class to match the root
 * element of an XML file from which objects are to be unmarshalled.
 *
 * @since 1.0
 */
@XmlRootElement(name = "boardgames")
public class BoardGameList {

    @XmlElement(name = "boardgame")
    private List<BoardGame> boardGames;

    /**
     * Returns the games in this list.
     *
     * @return a non-null list
     */
    public List<BoardGame> getBoardGames() {
        return boardGames == null ? Collections.<BoardGame>emptyList() : boardGames;
    }
}
