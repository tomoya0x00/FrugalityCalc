package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.content.res.Resources;

import java.math.BigDecimal;

import miwax.java_conf.gr.jp.frugalitycalc.R;

/**
 * B入力中状態
 */
public enum InputBState implements State {
    INSTANCE;

    @Override
    public void onInputNumber(StateContext context, CalcNumber input) {
        context.getDisplay().addNumber(input);
    }

    @Override
    public void onInputOperator(StateContext context, Operation operator) {
        BigDecimal b = new BigDecimal(context.getDisplay().getString());
        context.setB(b);
        try {
            context.doCalc();
        } catch (ArithmeticException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.calcerror_message));
            onInputAllClear(context);
            return;
        }

        try {
            BigDecimal result = new BigDecimal(context.getDisplay().getString());
            context.setA(result);
        } catch (NumberFormatException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
            return;
        }
        context.clearB();
        context.setState(OperatorState.INSTANCE);
    }

    @Override
    public void onInputEqual(StateContext context) {
        try {
            BigDecimal b = new BigDecimal(context.getDisplay().getString());
            context.setB(b);
        } catch (NumberFormatException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.inputerror_message));
            onInputAllClear(context);
            return;
        }

        try {
            context.doCalc();
        } catch (ArithmeticException e) {
            Resources res = context.getAppContext().getResources();
            context.getDialog().show(res.getString(R.string.error_title), res.getString(R.string.calcerror_message));
            onInputAllClear(context);
            return;
        }
        context.setState(ResultState.INSTANCE);
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
        context.setState(InputAState.INSTANCE);
    }

    @Override
    public void onInputMemoryRead(StateContext context) {
        BigDecimal m = context.getMemory();
        context.getDisplay().setString(m.toString());
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
