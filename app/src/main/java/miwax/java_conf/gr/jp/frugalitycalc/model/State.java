package miwax.java_conf.gr.jp.frugalitycalc.model;

public interface State {
    void onInputNumber(StateContext context, CalcNumber input);
    void onInputOperator(StateContext context, Operation operator);
    void onInputEqual(StateContext context);
    void onInputClearEnd(StateContext context);
    void onInputAllClear(StateContext context);
}
