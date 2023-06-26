package com.loncark.guitarapp.model.guitar;

import com.loncark.guitarapp.model.Material;
import com.loncark.guitarapp.model.instrument.PluggableInstrument;
import com.loncark.guitarapp.model.strings.SteelStrings;
import com.loncark.guitarapp.model.strings.Strings;

import java.math.BigDecimal;

public class ElectricGuitar extends Guitar implements PluggableInstrument {
    Strings strings;

    public ElectricGuitar(Material body, Material neck, String name, BigDecimal price, Long stock, String code) {
        super(body, neck, name, price, stock, code);
        this.strings = new SteelStrings();
    }
}
