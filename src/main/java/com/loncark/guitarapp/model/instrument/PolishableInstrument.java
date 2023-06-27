package com.loncark.guitarapp.model.instrument;

public interface PolishableInstrument {
    default void polish() {
        System.out.println("\nPolishing the instrument...");
    }
}
