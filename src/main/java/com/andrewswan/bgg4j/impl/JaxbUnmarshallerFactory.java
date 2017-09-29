package com.andrewswan.bgg4j.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import static java.lang.String.format;

/**
 * Factory for JAXB {@link Unmarshaller}s used internally by this library.
 * Client apps should not even be aware of XML.
 *
 * @since 1.0
 */
public final class JaxbUnmarshallerFactory {

    /**
     * Returns a new XML unmarshaller for the given class, which is expected to be annotated with @XmlRootElement.
     *
     * @param payloadClass the class representing the root XML element
     * @return a non-null unmarshaller
     * @throws IllegalArgumentException if the given class is not annotated with {@link XmlRootElement}
     */
    public static Unmarshaller getUnmarshaller(final Class<?> payloadClass) {
        validateXmlRootElement(payloadClass);
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(payloadClass);
            return jaxbContext.createUnmarshaller();
        }
        catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void validateXmlRootElement(final Class<?> payloadClass) {
        if (payloadClass.getAnnotation(XmlRootElement.class) == null) {
            throw new IllegalArgumentException(
                    format("%s is not annotated with @XmlRootElement", payloadClass.getName()));
        }
    }

    /**
     * Constructor is private to prevent instantiation.
     */
    private JaxbUnmarshallerFactory() {}
}
