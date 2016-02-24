package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operation {
    NONE("")      { BigDecimal apply(BigDecimal x, BigDecimal y) {return new BigDecimal("0");} },
    PLUS("+")     { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.add(y);} },
    MINUS("-")    { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.subtract(y);} },
    MULTIPLE("×") { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.multiply(y);} },
    DIVIDE("÷")   { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.divide(y, MathContext.DECIMAL128);} };

    private String string;

    Operation(String s) {
        this.string = s;
    }

    public String getString() {
        return this.string;
    }

    abstract BigDecimal apply(BigDecimal x, BigDecimal y);
}
