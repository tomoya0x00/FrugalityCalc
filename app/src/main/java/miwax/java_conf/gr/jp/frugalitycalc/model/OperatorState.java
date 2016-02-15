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

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {

    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {

    }

    @Override
    public void onInputEqual(StateContext context) {

    }

    @Override
    public void onInputClearEnd(StateContext context) {

    }

    @Override
    public void onInputAllClear(StateContext context) {

    }
}
