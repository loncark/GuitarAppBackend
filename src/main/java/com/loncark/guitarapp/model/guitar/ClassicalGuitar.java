package com.loncark.guitarapp.model.guitar;

import com.loncark.guitarapp.model.instrument.PolishableInstrument;
import com.loncark.guitarapp.model.instrument.TunableInstrument;
import com.loncark.guitarapp.model.strings.NylonStrings;
import com.loncark.guitarapp.model.Material;
import com.loncark.guitarapp.model.strings.Strings;

import java.math.BigDecimal;

public class ClassicalGuitar extends Guitar implements TunableInstrument, PolishableInstrument {
    Strings strings;
    String shape;

    public ClassicalGuitar(Material body, Material neck, String name, BigDecimal price, Long stock, String code) {
        super(body, neck, name, price, stock, code);
        this.strings = new NylonStrings();
        this.shape = "Round";
    }
}
