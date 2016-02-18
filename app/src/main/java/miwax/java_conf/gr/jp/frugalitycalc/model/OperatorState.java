package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

/**
 * 演算子選択状態
 */
public enum OperatorState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.getDisplay().setNumber(input);
        context.setState(InputBState.INSTANCE);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
        context.setOperation(operator);
    }

    @Override
    public void onInputEqual(StateContext context) {
        context.setOperation(null);
        context.setState(ResultState.INSTANCE);
    }

    @Override
    public void onInputClearEnd(StateContext context) {
        context.clearOperation();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.clearA();
        context.clearB();
        context.getDisplay().clear();
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputMemoryRead(StateContext context) {
        BigDecimal m = context.getMemory();
        context.getDisplay().setString(m.toString());
        context.setState(InputBState.INSTANCE);
    }

    @Override
    public void onInputMemoryClear(StateContext context) {
        context.clearMemory();
    }

    @Override
    public void onInputMemoryPlus(StateContext context) {
        BigDecimal decimal = new BigDecimal(context.getDisplay().getString());
        context.setMemory(context.getMemory().add(decimal));
    }

    @Override
    public void onInputMemoryMinus(StateContext context) {
        BigDecimal decimal = new BigDecimal(context.getDisplay().getString());
        context.setMemory(context.getMemory().subtract(decimal));
    }
}
