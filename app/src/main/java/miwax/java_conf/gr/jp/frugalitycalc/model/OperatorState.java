package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.content.res.Resources;

import java.math.BigDecimal;

import miwax.java_conf.gr.jp.frugalitycalc.R;

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
        try {
            BigDecimal decimal = new BigDecimal(context.getDisplay().getString());
            context.setMemory(context.getMemory().add(decimal));
        } catch (NumberFormatException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
        }
    }

    @Override
    public void onInputMemoryMinus(StateContext context) {
        try {
            BigDecimal decimal = new BigDecimal(context.getDisplay().getString());
            context.setMemory(context.getMemory().subtract(decimal));
        } catch (NumberFormatException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
        }
    }
}
