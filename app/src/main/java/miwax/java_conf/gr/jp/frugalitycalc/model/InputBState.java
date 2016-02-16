package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

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
        context.getDisplay().addNumber(input);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
        BigDecimal b = new BigDecimal(context.getDisplay().getString());
        context.setB(b);
        context.doCalc();

        BigDecimal result = new BigDecimal(context.getDisplay().getString());
        context.setA(result);
        context.clearB();
        context.setState(OperatorState.getInstance());
    }

    @Override
    public void onInputEqual(StateContext context) {
        BigDecimal b = new BigDecimal(context.getDisplay().getString());
        context.setB(b);
        context.doCalc();
        context.setState(ResultState.getInstance());
    }

    @Override
    public void onInputClearEnd(StateContext context) {
        context.getDisplay().clearEnd();
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.clearA();
        context.clearB();
        context.clearOperation();
        context.getDisplay().clear();
        context.setState(InputAState.getInstance());
    }
}
