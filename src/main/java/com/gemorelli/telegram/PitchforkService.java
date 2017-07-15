package com.gemorelli.telegram;

import java.util.ArrayList;
import java.util.List;

public class PitchforkService {

    private static List<PitchforkEnum> pitchforks;

    private static PitchforkService instance = new PitchforkService();

    private PitchforkService() {
        this.pitchforks = new ArrayList<PitchforkEnum>();

        for (PitchforkEnum pitchfork : PitchforkEnum.values()) {
            this.pitchforks.add(pitchfork);
        }
    }

    public static List<PitchforkEnum> getPitchforks() {
        if (instance == null)
            instance = new PitchforkService();
        return pitchforks;
    }
}
