package miwax.java_conf.gr.jp.frugalitycalc.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import miwax.java_conf.gr.jp.frugalitycalc.Dialog;
import miwax.java_conf.gr.jp.frugalitycalc.R;
import miwax.java_conf.gr.jp.frugalitycalc.model.CalcNumber;
import miwax.java_conf.gr.jp.frugalitycalc.model.Display;
import miwax.java_conf.gr.jp.frugalitycalc.model.Operation;
import miwax.java_conf.gr.jp.frugalitycalc.model.StateContext;

/**
 * View model for the MainActivity
 */
public class MainViewModel {
    public ObservableField<String> result;
    public ObservableField<String> memory;

    private Context context;
    private StateContext stateContext;

    public MainViewModel(Context context) {
        this.context = context;
        stateContext = new StateContext(this.context);
        result = new ObservableField<>("0");
        memory = new ObservableField<>("M:0");

        stateContext.setDisplay(new Display(result, memory));
        stateContext.setDialog(new Dialog(this.context));
    }

    public void onClickNumber(View view) {
        CalcNumber number = null;

        switch (view.getId()) {
            case R.id.zero_button:
                number = CalcNumber.ZERO;
                break;
            case R.id.one_button:
                number = CalcNumber.ONE;
                break;
            case R.id.two_button:
                number = CalcNumber.TWO;
                break;
            case R.id.three_button:
                number = CalcNumber.THREE;
                break;
            case R.id.four_button:
                number = CalcNumber.FOUR;
                break;
            case R.id.five_button:
                number = CalcNumber.FIVE;
                break;
            case R.id.six_button:
                number = CalcNumber.SIX;
                break;
            case R.id.seven_button:
                number = CalcNumber.SEVEN;
                break;
            case R.id.eight_button:
                number = CalcNumber.EIGHT;
                break;
            case R.id.nine_button:
                number = CalcNumber.NINE;
                break;
            case R.id.dot_button:
                number = CalcNumber.DOT;
                break;
            default:
                break;
        }
        stateContext.onInputNumber(number);
    }

    public void onClickOperator(View view) {
        Operation operation = null;

        switch (view.getId()) {
            case R.id.add_button:
                operation = Operation.PLUS;
                break;
            case R.id.sub_button:
                operation = Operation.MINUS;
                break;
            case R.id.multiple_button:
                operation = Operation.MULTIPLE;
                break;
            case R.id.div_button:
                operation = Operation.DIVIDE;
                break;
            default:
                break;
        }
        stateContext.onInputOperator(operation);
    }

    public void onClickClearEnd(View view) {
        stateContext.onInputClearEnd();
    }

    public void onClickAllClear(View view) {
        stateContext.onInputAllClear();
    }

    public void onClickEqual(View view) {
        stateContext.onInputEqual();
    }

    public void onClickMemoryClear(View view) {
        stateContext.onInputMemoryClear();
    }

    public void onClickMemoryRead(View view) {
        stateContext.onInputMemoryRead();
    }

    public void onClickMemoryPlus(View view) {
        stateContext.onInputMemoryPlus();
    }

    public void onClickMemoryMinus(View view) {
        stateContext.onInputMemoryMinus();
    }
}
