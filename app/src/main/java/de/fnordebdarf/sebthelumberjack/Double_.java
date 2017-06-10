package de.fnordebdarf.sebthelumberjack;

/**
 * Created by sammann on 10.06.17.
 *
 */

class Double_ {

    private Double value;

    Double_(int i) {
        this(Double.valueOf(i));
    }

    private Double_(Double value) {
        this.value = value;
    }

    private static Double valueOf(String string) {
        return Double.valueOf(string);
    }

    static Double_ with(String string) {
        return new Double_(valueOf(string));
    }


    double $(int power) {
        return Math.pow(value, power);
    }

    Double $() {
        return value;
    }
}
