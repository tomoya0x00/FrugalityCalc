package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

public enum Operation {
    PLUS     { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.add(y);} },
    MINUS    { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.subtract(y);} },
    MULTIPLE { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.multiply(y);} },
    DIVIDE   { BigDecimal apply(BigDecimal x, BigDecimal y) {return x.divide(y, 8, BigDecimal.ROUND_HALF_UP);} };

    abstract BigDecimal apply(BigDecimal x, BigDecimal y);
}
