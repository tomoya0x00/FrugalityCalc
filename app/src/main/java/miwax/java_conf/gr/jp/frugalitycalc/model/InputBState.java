package miwax.java_conf.gr.jp.frugalitycalc.model;

/**
 * B入力中状態
 */
public class InputBState implements State {
    private static InputBState ourInstance = new InputBState();

    public static InputBState getInstance() {
        return ourInstance;
    }

    private InputBState() {
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
