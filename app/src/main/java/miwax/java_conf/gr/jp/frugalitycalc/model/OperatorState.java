package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

/**
 * 演算子選択状態
 */
public enum OperatorState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(CalcModel context, CalcNumber input) {
        context.getEditor().setNumber(input);
        context.setState(InputBState.INSTANCE);
    }

    @Override
    public void onInputOperator(CalcModel context, Operation operator) {
        context.setOperation(operator);
    }

    @Override
    public void onInputEqual(CalcModel context) {
        context.clearOperation();
        context.setState(ResultState.INSTANCE);
    }

    @Override
    public void onInputClearEnd(CalcModel context) {
        context.clearOperation();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputAllClear(CalcModel context) {
        context.clearA();
        context.clearB();
        context.getEditor().clear();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputMemoryRead(CalcModel context) {
        BigDecimal m = context.getMemory();
        context.getEditor().setString(m.toString());
        context.setState(InputBState.INSTANCE);
    }

    @Override
    public void onInputMemoryClear(CalcModel context) {
        context.clearMemory();
    }

    @Override
    public void onInputMemoryPlus(CalcModel context) {
        try {
            BigDecimal decimal = new BigDecimal(context.getEditor().getString());
            context.setMemory(context.getMemory().add(decimal));
        } catch (NumberFormatException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.INPUT);
        }
    }

    @Override
    public void onInputMemoryMinus(CalcModel context) {
        try {
            BigDecimal decimal = new BigDecimal(context.getEditor().getString());
            context.setMemory(context.getMemory().subtract(decimal));
        } catch (NumberFormatException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.INPUT);
        }
    }
}
