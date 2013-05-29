Introduction
~~~~~~~~~~~~

This is a Java library for programmatic access to boardgamegeek.com. It provides a domain model (BoardGame, Designer,
etc.) and a programmer-friendly abstraction over the BGG XML API. If you want to access BGG from your Java program, this
is the library for you!

Initially I will be implementing those parts of the BGG API that I require for my own Java apps, but I'll happily accept
contributions (via pull requests) for other parts of the API as well.

This project is neither affiliated with, sponsored by, nor approved by boardgamegeek.com. Hopefully they would contact
me if they had any concerns about it.

Building
~~~~~~~~

This is a standard Maven project that produces a JAR file. To build it, simply install Maven in the usual way (if you
have a Mac, I recommend Homebrew) and type:

  mvn install

Usage
~~~~~

The main interface to this library is com.andrewswan.bgg4j.BoardGameRepository. Hopefully the method names are
self-documenting, but if not, the JavaDoc should explain all. There is only one implementation, namely
com.andrewswan.bgg4j.impl.XmlBoardGameRepository, which you will need to instantiate in whatever way suits your
application, e.g. using the "new" operator or as a Spring bean, etc.