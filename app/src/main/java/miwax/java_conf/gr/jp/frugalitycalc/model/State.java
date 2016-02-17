package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.io.Serializable;

public interface State extends Serializable {
    void onInputNumber(StateContext context, CalcNumber input);
    void onInputOperator(StateContext context, Operation operator);
    void onInputEqual(StateContext context);
    void onInputClearEnd(StateContext context);
    void onInputAllClear(StateContext context);
}
