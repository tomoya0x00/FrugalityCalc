package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

/**
 * B入力中状態
 */
public enum InputBState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.getEditor().addNumber(input);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
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
        context.setState(OperatorState.INSTANCE);
    }

    @Override
    public void onInputEqual(StateContext context) {
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
    public void onInputClearEnd(StateContext context) {
        context.getEditor().clearEnd();
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getEditor().clear();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputMemoryRead(StateContext context) {
        BigDecimal m = context.getMemory();
        context.getEditor().setString(m.toString());
    }

    @Override
    public void onInputMemoryClear(StateContext context) {
        context.clearMemory();
    }

    @Override
    public void onInputMemoryPlus(StateContext context) {
        try {
            BigDecimal decimal = new BigDecimal(context.getEditor().getString());
            context.setMemory(context.getMemory().add(decimal));
        } catch (NumberFormatException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.INPUT);
        }
    }

    @Override
    public void onInputMemoryMinus(StateContext context) {
        try {
            BigDecimal decimal = new BigDecimal(context.getEditor().getString());
            context.setMemory(context.getMemory().subtract(decimal));
        } catch (NumberFormatException e) {
            onInputAllClear(context);
            context.notifyError(CalcError.INPUT);
        }
    }
}
