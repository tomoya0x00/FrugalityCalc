package miwax.java_conf.gr.jp.frugalitycalc.model;

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
}
