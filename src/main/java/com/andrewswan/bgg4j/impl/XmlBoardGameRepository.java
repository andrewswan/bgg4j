package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGame;
import com.andrewswan.bgg4j.BoardGameList;
import com.andrewswan.bgg4j.BoardGameRepository;
import com.andrewswan.bgg4j.BoardGameSummary;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * A BoardGameRepository that uses the BGG XML API behind the scenes.
 *
 * This class is thread-safe and can be a singleton in your application.
 *
 * @since 1.0
 */
public class XmlBoardGameRepository implements BoardGameRepository {

    private static final String BGG_XML_API_BASE = "http://www.boardgamegeek.com/xmlapi/";
    private static final String URL_ENCODING = "UTF-8";

    @Override
    public Optional<BoardGame> get(final int bggId) {
        return getGames("boardgame/" + bggId).getOnlyEntry();
    }

    @Override
    public List<BoardGameSummary> search(final String name) {
        return getGames(getSearchUrl(name, false)).getSummaries();
    }

    @Override
    public Optional<BoardGameSummary> searchExact(String name) {
        return getGames(getSearchUrl(name, true)).getOnlyEntry().map(BoardGame::getSummary);
    }

    private String getSearchUrl(final String query, final boolean exact) {
        final StringBuilder url = new StringBuilder("search?search=");
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
            return (BoardGameList) BoardGameList.UNMARSHALLER.unmarshal(bggUrl);
        }
        catch (final JAXBException | MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}
