package com.andrewswan.bgg4j;

import org.apache.commons.lang3.Validate;

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

    /**
     * Returns the only game in this list, if any.
     *
     * @return null if there are no games
     * @throws IllegalStateException if there are more than one
     */
    public BoardGame getOnlyEntry() {
        if (boardGames.isEmpty()) {
            return null;
        }
        Validate.validState(boardGames.size() == 1, "Expected at most one game but found " + boardGames.size());
        // Have one result, but is it what we asked for, or an error placeholder? BGG does not return 404s.
        final BoardGame boardGame = boardGames.get(0);
        if (boardGame.getBggId() == 0) {
            return null;
        }
        return boardGame;
    }
}
