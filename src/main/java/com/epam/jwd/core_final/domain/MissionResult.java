package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum MissionResult {
    CANCELLED(1),
    FAILED(2),
    PLANNED(3),
    IN_PROGRESS(4),
    COMPLETED(5);

    MissionResult(Integer id) {
    }

    public static MissionResult resolveMissionResultById(int id) {
        if (id == 1) {
            return MissionResult.CANCELLED;
        } else if (id == 2) {
            return MissionResult.FAILED;
        } else if (id == 3) {
            return MissionResult.PLANNED;
        } else if (id == 4) {
            return MissionResult.IN_PROGRESS;
        } else if (id == 5) {
            return MissionResult.COMPLETED;
        } else {
            throw new UnknownEntityException(String.valueOf(id));
        }
    }

}
