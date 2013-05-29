package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameList;
import com.andrewswan.bgg4j.BoardGameRepository;
import org.apache.commons.lang3.Validate;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * A BoardGameRepository that uses the BGG XML API behind the scenes.
 *
 * @since 1.0
 */
public class XmlBoardGameRepository implements BoardGameRepository {

    private static final String BGG_XML_API_BASE = "http://www.boardgamegeek.com/xmlapi/";
    private static final String URL_ENCODING = "UTF-8";
    private static final Unmarshaller GAME_LIST_UNMARSHALLER = JaxbUtils.getUnmarshaller(BoardGameList.class);

    @Override
    public BoardGame get(final int bggId) {
        final List<BoardGame> boardGames = getGames("boardgame/" + bggId);
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

    @Override
    public List<BoardGame> search(final String name) {
        try {
            return getGames("/search?search=" + URLEncoder.encode(name, URL_ENCODING));
        }
        catch (final UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<BoardGame> getGames(final String urlPath) {
        try {
            final URL bggUrl = new URL(BGG_XML_API_BASE + urlPath);
            final BoardGameList gameList = (BoardGameList) GAME_LIST_UNMARSHALLER.unmarshal(bggUrl);
            return gameList.getBoardGames();
        }
        catch (final JAXBException e) {
            throw new IllegalStateException(e);
        }
        catch (final MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}
