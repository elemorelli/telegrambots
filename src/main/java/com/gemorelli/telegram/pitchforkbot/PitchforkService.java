package com.gemorelli.telegram.pitchforkbot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PitchforkService {

    private static List<PitchforkEnum> pitchforks;

    private static PitchforkService instance = new PitchforkService();

    private PitchforkService() {
        pitchforks = new ArrayList<PitchforkEnum>();

        Collections.addAll(pitchforks, PitchforkEnum.values());
    }

    static List<PitchforkEnum> getPitchforks() {
        if (instance == null)
            instance = new PitchforkService();
        return pitchforks;
    }
}
