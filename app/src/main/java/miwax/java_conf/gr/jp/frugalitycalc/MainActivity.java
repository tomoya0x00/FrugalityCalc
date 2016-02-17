package miwax.java_conf.gr.jp.frugalitycalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import miwax.java_conf.gr.jp.frugalitycalc.model.CalcNumber;
import miwax.java_conf.gr.jp.frugalitycalc.model.Dialog;
import miwax.java_conf.gr.jp.frugalitycalc.model.Display;
import miwax.java_conf.gr.jp.frugalitycalc.model.Operation;
import miwax.java_conf.gr.jp.frugalitycalc.model.StateContext;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.result_text) TextView resultText;

    private static final String CONTEXT = "context";
    private static final String RESULT_TEXT= "result_text";

    private StateContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            context = savedInstanceState.getParcelable(CONTEXT);
            resultText.setText(savedInstanceState.getString(RESULT_TEXT));
        }

        context = new StateContext(getApplicationContext(), new Display(resultText), new Dialog(this));
   }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CONTEXT, context);
        outState.putString(RESULT_TEXT, resultText.getText().toString());
    }

    @OnClick(R.id.zero_button)
    public void ZeroButton() {
        context.onInputNumber(CalcNumber.ZERO);
    }

    @OnClick(R.id.one_button)
    public void OneButton() {
        context.onInputNumber(CalcNumber.ONE);
    }

    @OnClick(R.id.two_button)
    public void TwoButton() {
        context.onInputNumber(CalcNumber.TWO);
    }

    @OnClick(R.id.three_button)
    public void ThreeButton() {
        context.onInputNumber(CalcNumber.THREE);
    }

    @OnClick(R.id.four_button)
    public void FourButton() {
        context.onInputNumber(CalcNumber.FOUR);
    }

    @OnClick(R.id.five_button)
    public void FiveButton() {
        context.onInputNumber(CalcNumber.FIVE);
    }

    @OnClick(R.id.six_button)
    public void SixButton() {
        context.onInputNumber(CalcNumber.SIX);
    }

    @OnClick(R.id.seven_button)
    public void SevenButton() {
        context.onInputNumber(CalcNumber.SEVEN);
    }

    @OnClick(R.id.eight_button)
    public void EightButton() {
        context.onInputNumber(CalcNumber.EIGHT);
    }

    @OnClick(R.id.nine_button)
    public void NineButton() {
        context.onInputNumber(CalcNumber.NINE);
    }

    @OnClick(R.id.dot_button)
    public void DotButton() {
        context.onInputNumber(CalcNumber.DOT);
    }

    @OnClick(R.id.clearend_button)
    public void ClearEndButton() {
        context.onInputClearEnd();
    }

    @OnClick(R.id.allclear_button)
    public void AllClearButton() {
        context.onInputAllClear();
    }

    @OnClick(R.id.add_button)
    public void AddButton() {
        context.onInputOperator(Operation.PLUS);
    }

    @OnClick(R.id.sub_button)
    public void SubButton() {
        context.onInputOperator(Operation.MINUS);
    }

    @OnClick(R.id.multiple_button)
    public void MultipleButton() {
        context.onInputOperator(Operation.MULTIPLE);
    }

    @OnClick(R.id.div_button)
    public void DivButton() {
        context.onInputOperator(Operation.DIVIDE);
    }

    @OnClick(R.id.equal_button)
    public void EqualButton() {
        context.onInputEqual();
    }
}
