package com.loncark.guitarapp.model.instrument;

public interface PluggableInstrument {
    default void play() {
        System.out.println("\nPlugging the instrument in...");
        System.out.println("\nPlaying the instrument...");
    }
}
