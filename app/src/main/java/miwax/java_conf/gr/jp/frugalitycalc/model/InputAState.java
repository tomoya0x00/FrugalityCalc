package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.content.res.Resources;

import java.math.BigDecimal;

import miwax.java_conf.gr.jp.frugalitycalc.R;

/**
 * A入力状態
 */
public enum InputAState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.getEditor().addNumber(input);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
        try {
            BigDecimal a = new BigDecimal(context.getEditor().getString());
            context.setA(a);
        } catch (ArithmeticException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
            return;
        }
        context.setOperation(operator);
        context.setState(OperatorState.INSTANCE);
    }

    @Override
    public void onInputEqual(StateContext context) {
        try {
            BigDecimal a = new BigDecimal(context.getEditor().getString());
            context.setA(a);
        } catch (ArithmeticException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
            return;
        }
        context.setState(ResultState.INSTANCE);
    }

    @Override
    public void onInputClearEnd(StateContext context) {
        context.getEditor().clearEnd();
    }

    @Override
    public void onInputAllClear(StateContext context) {
        context.getEditor().clear();
    }

    @Override
    public void onInputMemoryRead(StateContext context) {
        BigDecimal m = context.getMemory();
        context.getEditor().setString(m.toString());
    }

    @Override
    public void onInputMemoryClear(StateContext context) {
        context.clearMemory();
    }

    @Override
    public void onInputMemoryPlus(StateContext context) {
        try {
            BigDecimal decimal = new BigDecimal(context.getEditor().getString());
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
            BigDecimal decimal = new BigDecimal(context.getEditor().getString());
            context.setMemory(context.getMemory().subtract(decimal));
        } catch (NumberFormatException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
        }
    }
}
