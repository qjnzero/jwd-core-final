package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum MissionResult {

    CANCELLED(1),
    FAILED(2),
    PLANNED(3),
    IN_PROGRESS(4),
    COMPLETED(5);

    MissionResult(int id) {
    }

    public static MissionResult resolveMissionResultById(int id) {
        MissionResult missionResult;

        switch (id) {
            case 1:
                missionResult = MissionResult.CANCELLED;
                break;
            case 2:
                missionResult = MissionResult.FAILED;
                break;
            case 3:
                missionResult = MissionResult.PLANNED;
                break;
            case 4:
                missionResult = MissionResult.IN_PROGRESS;
                break;
            case 5:
                missionResult = MissionResult.COMPLETED;
                break;
            default:
                throw new UnknownEntityException("Cannot resolve mission result");
        }

        return missionResult;
    }
}
