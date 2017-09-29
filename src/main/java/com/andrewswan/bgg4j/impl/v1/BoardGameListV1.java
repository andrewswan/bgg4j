package com.andrewswan.bgg4j.impl.v1;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameSummary;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Optional;

import static com.andrewswan.bgg4j.impl.JaxbUnmarshallerFactory.getUnmarshaller;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * A wrapper around a list of {@link BoardGameV1}s. This class only exists because JAXB requires a class to match the root
 * element of an XML file from which objects are to be unmarshalled.
 *
 * @since 1.0
 */
@XmlRootElement(name = "boardgames")
public class BoardGameListV1 {

    /**
     * A JAXB XML unmarshaller for this class.
     */
    static final Unmarshaller UNMARSHALLER = getUnmarshaller(BoardGameListV1.class);

    @XmlElement(name = "boardgame")
    private List<BoardGameV1> boardGames;

    /**
     * Returns the games in this list.
     *
     * @return a non-null list
     */
    List<BoardGameV1> getBoardGames() {
        return getFirstEntry()
                .map(canary -> boardGames)
                .orElse(emptyList());
    }

    private Optional<BoardGame> getFirstEntry() {
        if (boardGames == null) {
            return Optional.empty();
        }
        return boardGames.stream()
                .filter(BoardGameV1::isRealEntry)
                .findFirst()
                .map(BoardGame.class::cast);
    }

    /**
     * Returns the only game in this list, if any.
     *
     * @return see above
     * @throws IllegalStateException if there are more than one
     */
    Optional<BoardGame> getOnlyEntry() {
        final Optional<BoardGame> firstGame = getFirstEntry();
        if (firstGame.isPresent()) {
            if (boardGames.size() > 1) {
                throw new IllegalStateException("Expected at most one game but found " + boardGames.size());
            }
            return firstGame;
        }
        return Optional.empty();
    }

    List<BoardGameSummary> getSummaries() {
        return getBoardGames().stream()
                .map(BoardGame::getSummary)
                .collect(toList());
    }
}
