package miwax.java_conf.gr.jp.frugalitycalc.model;

public enum CalcNumber {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    DOT(".");

    private String string;

    CalcNumber(String s) {
        this.string = s;
    }

    public String getString() {
        return this.string;
    }
}
