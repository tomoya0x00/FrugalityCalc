package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

/**
 * 計算結果表示状態
 */
public enum ResultState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(CalcModel context, CalcNumber input) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getEditor().setNumber(input);
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputOperator(CalcModel context, Operation operator) {
        try {
            BigDecimal a = new BigDecimal(context.getEditor().getString());
            context.setA(a);
        } catch (NumberFormatException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.INPUT);
            return;
        }
        context.clearB();
        context.setOperation(operator);
        context.setState(OperatorState.INSTANCE);
    }

    @Override
    public void onInputEqual(CalcModel context) {

    }

    @Override
    public void onInputClearEnd(CalcModel context) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getEditor().clear();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputAllClear(CalcModel context) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getEditor().clear();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputMemoryRead(CalcModel context) {
        BigDecimal m = context.getMemory();
        context.getEditor().setString(m.toString());
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.setState(InputAState.INSTANCE);
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
