package miwax.java_conf.gr.jp.frugalitycalc.model;

/**
 * A入力状態
 */
public class InputAState implements State {
    private static InputAState ourInstance = new InputAState();

    public static InputAState getInstance() {
        return ourInstance;
    }

    private InputAState() {
    }

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.getDisplay().addNumber(input);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {

    }

    @Override
    public void onInputEqual(StateContext context) {
    }

    @Override
    public void onInputClearEnd(StateContext context) {
        context.getDisplay().clearEnd();
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.getDisplay().clear();
    }
}
