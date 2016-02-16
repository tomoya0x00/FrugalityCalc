package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

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
        BigDecimal a = new BigDecimal(context.getDisplay().getString());
        context.setA(a);
        context.setOperation(operator);
        context.setState(OperatorState.getInstance());
    }

    @Override
    public void onInputEqual(StateContext context) {
        BigDecimal a = new BigDecimal(context.getDisplay().getString());
        context.setA(a);
        context.setState(ResultState.getInstance());
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
