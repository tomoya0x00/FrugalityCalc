package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

/**
 * 計算結果表示状態
 */
public enum ResultState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getDisplay().setNumber(input);
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
        BigDecimal a = new BigDecimal(context.getDisplay().getString());
        context.setA(a);
        context.clearB();
        context.setOperation(operator);
        context.setState(OperatorState.INSTANCE);
    }

    @Override
    public void onInputEqual(StateContext context) {

    }

    @Override
    public void onInputClearEnd(StateContext context) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getDisplay().clear();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getDisplay().clear();
        context.setState(InputAState.INSTANCE);
    }
}
