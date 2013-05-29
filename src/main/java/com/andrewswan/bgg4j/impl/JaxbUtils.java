package com.andrewswan.bgg4j.impl;

import com.andrewswan.bgg4j.BoardGameList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * JAXB-related utility methods for use only within this library. Client apps should not even be aware of XML.
 *
 * @since 1.0
 */
public final class JaxbUtils {

    /**
     * Returns a new XML unmarshaller for the given class, which is expected to be annotated with @XmlRootElement.
     *
     * @param payloadClass the class representing the root XML element
     * @return a non-null unmarshaller
     */
    public static Unmarshaller getUnmarshaller(final Class<?> payloadClass) {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(payloadClass);
            return jaxbContext.createUnmarshaller();
        }
        catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Constructor is private to prevent instantiation.
     */
    private JaxbUtils() {}
}
