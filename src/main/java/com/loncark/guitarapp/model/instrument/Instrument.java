package com.loncark.guitarapp.model.instrument;

public interface Instrument {
    default void play() {
        System.out.println("\nPlaying the instrument...");
    }
}

