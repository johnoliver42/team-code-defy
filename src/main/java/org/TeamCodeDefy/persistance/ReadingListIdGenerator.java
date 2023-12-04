package org.TeamCodeDefy.persistance;

import org.TeamCodeDefy.entities.ReadingList;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * This class is used to generate a unique id for a ReadingList so that the
 * reading list id can have a minimum of 8 digits.
 */
public class ReadingListIdGenerator implements IdentifierGenerator {

    public static final String generatorName = "ReadingListIdGenerator";
    private static final long serialVersionUID = 1L;

    private static final int MINIMUM_ID = 10000000;

    public ReadingListIdGenerator() {}

    /**
     * Generate a unique id for a ReadingList.
     *
     * @param sharedSessionContractImplementor the shared session contract implementor
     * @param object  the object
     * @return the serializable
     */
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor , Object object) {
        // Generate a random number with a minimum of 10000000 and a maximum of integer max value.
        int id;

        do {
            id = (int)(Math.random() * Integer.MAX_VALUE) + MINIMUM_ID;
        } while (new GenericDao<>(ReadingList.class).exists(id));

        return id;
    }

}
