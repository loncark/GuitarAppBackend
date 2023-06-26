package com.loncark.guitarapp.model.guitar;

import com.loncark.guitarapp.model.strings.CopperStrings;
import com.loncark.guitarapp.model.instrument.Instrument;
import com.loncark.guitarapp.model.Material;
import com.loncark.guitarapp.model.strings.Strings;

import java.math.BigDecimal;

public class ClassicalGuitar extends Guitar implements Instrument {
    Strings strings;

    public ClassicalGuitar(Material body, Material neck, String name, BigDecimal price, Long stock, String code) {
        super(body, neck, name, price, stock, code);
        this.strings = new CopperStrings();
    }
}
