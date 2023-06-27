package com.loncark.guitarapp.model.instrument;

public interface TunableInstrument {
    default void tune() {
        System.out.println("\nTuning the instrument...");
    }
}
