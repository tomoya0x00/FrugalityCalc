package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

/**
 * B入力中状態
 */
public enum InputBState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(CalcModel context, CalcNumber input) {
        context.getEditor().addNumber(input);
    }

    @Override
    public void onInputOperator(CalcModel context, Operation operator) {
        BigDecimal b = new BigDecimal(context.getEditor().getString());
        context.setB(b);
        try {
            context.doCalc();
        } catch (ArithmeticException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.CALCULATION);
            return;
        }

        try {
            BigDecimal result = new BigDecimal(context.getEditor().getString());
            context.setA(result);
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
        try {
            BigDecimal b = new BigDecimal(context.getEditor().getString());
            context.setB(b);
        } catch (NumberFormatException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.INPUT);
            return;
        }

        try {
            context.doCalc();
        } catch (ArithmeticException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.CALCULATION);
            return;
        }
        context.clearOperation();
        context.setState(ResultState.INSTANCE);
    }

    @Override
    public void onInputClearEnd(CalcModel context) {
        context.getEditor().clearEnd();
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
