package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameList;
import com.andrewswan.bgg4j.BoardGameRepository;
import org.apache.commons.lang3.Validate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * A BoardGameRepository that uses the BGG XML API behind the scenes.
 *
 * @since 1.0
 */
public class XmlBoardGameRepository implements BoardGameRepository {

    private static final String BGG_XML_API_BASE = "http://www.boardgamegeek.com/xmlapi/";

    @Override
    public BoardGame get(final int bggId)
    {
        try {
            final Unmarshaller unmarshaller = JaxbUtils.getUnmarshaller(BoardGameList.class);
            final BoardGameList boardGameList = (BoardGameList) unmarshaller.unmarshal(
                    new URL(BGG_XML_API_BASE + "boardgame/" + bggId));
            final List<BoardGame> boardGames = boardGameList.getBoardGames();
            if (boardGames.isEmpty()) {
                return null;
            }
            Validate.validState(boardGames.size() == 1, "BGG returned more than one game with an ID of %d", bggId);
            // Have one result, but is it what we asked for or an error placeholder? BGG does not return 404s.
            final BoardGame boardGame = boardGames.get(0);
            if (boardGame.getBggId() == 0) {
                return null;
            }
            return boardGame;
        }
        catch (final JAXBException e) {
            throw new IllegalStateException(e);
        }
        catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}
