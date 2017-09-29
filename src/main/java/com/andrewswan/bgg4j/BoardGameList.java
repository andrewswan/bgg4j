package com.andrewswan.bgg4j;

import com.andrewswan.bgg4j.impl.JaxbUnmarshallerFactory;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.andrewswan.bgg4j.impl.JaxbUnmarshallerFactory.getUnmarshaller;

/**
 * A wrapper around a list of {@link BoardGame}s. This class only exists because JAXB requires a class to match the root
 * element of an XML file from which objects are to be unmarshalled.
 *
 * @since 1.0
 */
@XmlRootElement(name = "boardgames")
public class BoardGameList {

    /**
     * A JAXB XML unmarshaller for this class.
     */
    public static final Unmarshaller UNMARSHALLER = getUnmarshaller(BoardGameList.class);

    @XmlElement(name = "boardgame")
    private List<BoardGame> boardGames;

    /**
     * Returns the games in this list.
     *
     * @return a non-null list
     */
    public List<BoardGame> getBoardGames() {
        return getFirstEntry() == null ? Collections.<BoardGame>emptyList() : boardGames;
    }

    private BoardGame getFirstEntry() {
        if (boardGames == null || boardGames.isEmpty()) {
            return null;
        }
        // Have one or more results, but is it the first an error placeholder? BGG does not return 404s.
        final BoardGame boardGame = boardGames.get(0);
        if (boardGame.getBggId() == 0) {
            return null;
        }
        return boardGame;
    }

    /**
     * Returns the only game in this list, if any.
     *
     * @return null if there are no games
     * @throws IllegalStateException if there are more than one
     */
    public BoardGame getOnlyEntry() {
        final BoardGame firstGame = getFirstEntry();
        if (firstGame == null) {
            return null;
        }
        if (boardGames.size() > 1) {
            throw new IllegalStateException("Expected at most one game but found " + boardGames.size());
        }
        return firstGame;
    }

    public List<BoardGameSummary> getSummaries() {
        final List<BoardGameSummary> summaries = new ArrayList<BoardGameSummary>();
        for (final BoardGame boardGame : getBoardGames()) {
            summaries.add(boardGame.getSummary());
        }
        return summaries;
    }
}
