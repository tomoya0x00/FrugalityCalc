package miwax.java_conf.gr.jp.frugalitycalc.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import java.math.BigDecimal;

import miwax.java_conf.gr.jp.frugalitycalc.R;
import miwax.java_conf.gr.jp.frugalitycalc.model.CalcError;
import miwax.java_conf.gr.jp.frugalitycalc.model.CalcNumber;
import miwax.java_conf.gr.jp.frugalitycalc.model.Operation;
import miwax.java_conf.gr.jp.frugalitycalc.model.StateContext;
import miwax.java_conf.gr.jp.frugalitycalc.util.messenger.Messenger;
import miwax.java_conf.gr.jp.frugalitycalc.util.messenger.ShowAlertDialogMessage;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * View model for the MainActivity
 */
public class MainViewModel implements Subscription {
    public ObservableField<String> result;
    public ObservableField<BigDecimal> memory;

    private StateContext stateContext;
    private Messenger messenger = new Messenger();

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    public MainViewModel(final Context context) {
        stateContext = new StateContext();

        result = ObservableUtil.toObservableField(stateContext.getObservableResult(), subscriptions);
        memory = ObservableUtil.toObservableField(stateContext.getObservableMemory(), subscriptions);

        subscriptions.add(
                stateContext.getObservableError().subscribe(new Action1<CalcError>() {
                    @Override
                    public void call(CalcError calcError) {
                        // 該当するエラーがあればダイアログ表示
                        switch (calcError) {
                            case CALCULATION:
                                messenger.send(new ShowAlertDialogMessage(
                                        context.getString(R.string.error_title),
                                        context.getString(R.string.calcerror_message)));
                                break;
                            case INPUT:
                                messenger.send(new ShowAlertDialogMessage(
                                        context.getString(R.string.error_title),
                                        context.getString(R.string.inputerror_message)));
                                break;
                        }
                    }
                })
        );
    }

    public Messenger getMessenger() {
        return messenger;
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

    @Override
    public void unsubscribe() {
        subscriptions.unsubscribe();
    }

    @Override
    public boolean isUnsubscribed() {
        return subscriptions.isUnsubscribed();
    }
}
