package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Rank implements BaseEntity {

    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return name();
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Rank resolveRankById(int id) {
        if (id == 1L) {
            return Rank.TRAINEE;
        } else if (id == 2L) {
            return Rank.SECOND_OFFICER;
        } else if (id == 3L) {
            return Rank.FIRST_OFFICER;
        } else if (id == 4L) {
            return Rank.CAPTAIN;
        } else {
            throw new UnknownEntityException(String.valueOf(id));
        }
    }
}
