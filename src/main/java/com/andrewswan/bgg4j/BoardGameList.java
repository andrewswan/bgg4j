package com.andrewswan.bgg4j;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.andrewswan.bgg4j.impl.JaxbUnmarshallerFactory.getUnmarshaller;
import static java.util.Collections.emptyList;

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
        return getFirstEntry().map(canary -> boardGames).orElse(emptyList());
    }

    private Optional<BoardGame> getFirstEntry() {
        if (boardGames == null) {
            return Optional.empty();
        }
        return boardGames.stream().filter(BoardGame::isRealEntry).findFirst();
    }

    /**
     * Returns the only game in this list, if any.
     *
     * @return see above
     * @throws IllegalStateException if there are more than one
     */
    public Optional<BoardGame> getOnlyEntry() {
        final Optional<BoardGame> firstGame = getFirstEntry();
        if (firstGame.isPresent()) {
            if (boardGames.size() > 1) {
                throw new IllegalStateException("Expected at most one game but found " + boardGames.size());
            }
            return firstGame;
        }
        return Optional.empty();
    }

    public List<BoardGameSummary> getSummaries() {
        final List<BoardGameSummary> summaries = new ArrayList<>();
        for (final BoardGame boardGame : getBoardGames()) {
            summaries.add(boardGame.getSummary());
        }
        return summaries;
    }
}
