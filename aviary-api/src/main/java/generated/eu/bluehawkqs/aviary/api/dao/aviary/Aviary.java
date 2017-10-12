/*
 * This file is generated by jOOQ.
*/
package eu.bluehawkqs.aviary.api.dao.aviary;


import eu.bluehawkqs.aviary.api.dao.DefaultCatalog;
import eu.bluehawkqs.aviary.api.dao.aviary.tables.Persons;
import eu.bluehawkqs.aviary.api.dao.aviary.tables.Tournaments;
import eu.bluehawkqs.aviary.api.dao.aviary.tables.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Aviary extends SchemaImpl {

    private static final long serialVersionUID = 497989491;

    /**
     * The reference instance of <code>aviary</code>
     */
    public static final Aviary AVIARY = new Aviary();

    /**
     * The table <code>aviary.persons</code>.
     */
    public final Persons PERSONS = eu.bluehawkqs.aviary.api.dao.aviary.tables.Persons.PERSONS;

    /**
     * The table <code>aviary.tournaments</code>.
     */
    public final Tournaments TOURNAMENTS = eu.bluehawkqs.aviary.api.dao.aviary.tables.Tournaments.TOURNAMENTS;

    /**
     * The table <code>aviary.users</code>.
     */
    public final Users USERS = eu.bluehawkqs.aviary.api.dao.aviary.tables.Users.USERS;

    /**
     * No further instances allowed
     */
    private Aviary() {
        super("aviary", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.SYSTEM_SEQUENCE_130D8F20_07E6_4847_9D62_313DA21A6054,
            Sequences.SYSTEM_SEQUENCE_BF7F1866_B96A_4888_A4C9_5D570F10B92B);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Persons.PERSONS,
            Tournaments.TOURNAMENTS,
            Users.USERS);
    }
}
