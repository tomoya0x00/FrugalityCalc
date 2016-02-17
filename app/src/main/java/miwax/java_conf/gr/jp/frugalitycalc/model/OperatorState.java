package miwax.java_conf.gr.jp.frugalitycalc.model;

/**
 * 演算子選択状態
 */
public class OperatorState implements State {
    private static OperatorState ourInstance = new OperatorState();

    public static OperatorState getInstance() {
        return ourInstance;
    }

    private OperatorState() {
    }

    private Object readResolve() {
        return ourInstance;
    }

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.getDisplay().setNumber(input);
        context.setState(InputBState.getInstance());
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
        context.setOperation(operator);
    }

    @Override
    public void onInputEqual(StateContext context) {
        context.setOperation(null);
        context.setState(ResultState.getInstance());
    }

    @Override
    public void onInputClearEnd(StateContext context) {
        context.clearOperation();
        context.setState(InputAState.getInstance());
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.clearA();
        context.clearB();
        context.getDisplay().clear();
        context.setState(InputAState.getInstance());
    }
}
