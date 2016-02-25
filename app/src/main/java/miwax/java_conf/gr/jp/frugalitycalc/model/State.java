package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.io.Serializable;

public interface State extends Serializable {
    void onInputNumber(CalcModel context, CalcNumber input);
    void onInputOperator(CalcModel context, Operation operator);
    void onInputEqual(CalcModel context);
    void onInputClearEnd(CalcModel context);
    void onInputAllClear(CalcModel context);
    void onInputMemoryRead(CalcModel context);
    void onInputMemoryClear(CalcModel context);
    void onInputMemoryPlus(CalcModel context);
    void onInputMemoryMinus(CalcModel context);
}
