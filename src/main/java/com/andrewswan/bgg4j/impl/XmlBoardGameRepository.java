package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameList;
import com.andrewswan.bgg4j.BoardGameRepository;
import com.andrewswan.bgg4j.BoardGameSummary;

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
        return getGames("boardgame/" + bggId).getOnlyEntry();
    }

    @Override
    public List<BoardGameSummary> search(final String name) {
        return getGames(getSearchUrl(name, false)).getSummaries();
    }

    @Override
    public BoardGameSummary searchExact(String name) {
        return getGames(getSearchUrl(name, true)).getOnlyEntry().getSummary();
    }

    private String getSearchUrl(final String query, final boolean exact) {
        final StringBuilder url = new StringBuilder("/search?search=");
        try {
            url.append(URLEncoder.encode(query, URL_ENCODING));
        }
        catch (final UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        if (exact) {
            url.append("&exact=1");
        }
        return url.toString();
    }

    private BoardGameList getGames(final String urlPath) {
        try {
            final URL bggUrl = new URL(BGG_XML_API_BASE + urlPath);
            return (BoardGameList) GAME_LIST_UNMARSHALLER.unmarshal(bggUrl);
        }
        catch (final JAXBException e) {
            throw new IllegalStateException(e);
        }
        catch (final MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}
