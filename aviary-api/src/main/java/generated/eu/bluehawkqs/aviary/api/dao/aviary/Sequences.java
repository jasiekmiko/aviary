/*
 * This file is generated by jOOQ.
*/
package eu.bluehawkqs.aviary.api.dao.aviary;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in aviary
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>aviary.system_sequence_8a9cb323_ca4a_4926_9072_fc78d4435d3c</code>
     */
    public static final Sequence<Long> SYSTEM_SEQUENCE_8A9CB323_CA4A_4926_9072_FC78D4435D3C = new SequenceImpl<Long>("system_sequence_8a9cb323_ca4a_4926_9072_fc78d4435d3c", Aviary.AVIARY, org.jooq.impl.SQLDataType.BIGINT);
}
