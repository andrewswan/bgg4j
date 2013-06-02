Introduction
~~~~~~~~~~~~

This is a Java library for programmatic access to boardgamegeek.com. It
provides a programmer-friendly abstraction over the BGG XML API. If you want to
access BGG from your Java program, this is the library for you!

Initially I will be implementing those parts of the BGG API that I require for
my own Java apps, but I'll happily accept contributions (via pull requests) for
other parts of the API as well.

This project is neither affiliated with, sponsored by, nor approved by
boardgamegeek.com. Hopefully they would contact me if they had any concerns
about it.

Usage
~~~~~
1. Add this library to your application's classpath. For Maven users, here's the
dependency to add to your POM:

    <dependency>
         <groupId>com.andrewswan</groupId>
         <artifactId>bgg4j</artifactId>
         <version>1.0</version>
    </dependency>

Ant/Scala users can use the above coordinates in their own way.

This library is very lightweight, being about 10kb in size and with zero
dependencies of its own.

2. The entry point to this library is the BoardGameRepository interface, of
which there is only one implementation, namely XmlBoardGameRepository. Your
application will need to create an instance of this class in whichever way you
prefer, e.g. using the "new" operator, or as a Spring bean, etc. For example:

    import com.andrewswan.bgg4j.BoardGameRepository;
    import com.andrewswan.bgg4j.BoardGameSummary;
    import com.andrewswan.bgg4j.impl.XmlBoardGameRepository;

    public class MyApplication {

        // This class is thread-safe, so you only need one instance of it
        private final BoardGameRepository boardGameRepository =
                new XmlBoardGameRepository();

        public void myMethod() {
            ...
            // Get game by its BGG ID
            BoardGame dieMacher = boardGameRepository.get(1);
            ...
            // Exact name search
            BoardGameSummary steam = boardGameRepository.searchExact("steam");
            ...
            // Search for games whose names contain the given string
            List<BoardGameSummary> gamesWithSteamInName =
                    boardGameRepository.search("steam");
            ...
        }
    }

3. JavaDoc and source code archives are available from Maven Central for those
who might need them.

Building
~~~~~~~~

This section is only for people who want to build this library from source; most
people will only need to use the prebuilt JAR file, in the manner described
above.

For those who do wish to build it, e.g. to see how it works or to make a
contribution to it, it is a standard Maven project that produces a JAR file.
Building it is simply a case of installing Maven in the usual way (if you
have a Mac, I recommend Homebrew) and typing:

  mvn install

Feedback
~~~~~~~~
Please provide any bug reports, enhancement requests, or other feedback via the
project home page, https://bitbucket.org/AndrewSwan_au/bgg4j. Thanks to
Atlassian for the free hosting!