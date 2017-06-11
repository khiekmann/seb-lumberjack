package de.fnordebdarf.sebthelumberjack;

/**
 * Created by sammann on 10.06.17.
 *
 */

class Double_ {

    private final Double value;

    Double_(Double value) {
        this.value = value;
    }

    static Double_ from(String text) {
        Double value;
        try {
            value = Double.parseDouble(text);
        } catch (Exception e) {
            value = Double.NaN;
        }
        return new Double_(value);
    }

    double $(int power) {
        return Math.pow(value, power);
    }

    Double $() {
        return value;
    }
}