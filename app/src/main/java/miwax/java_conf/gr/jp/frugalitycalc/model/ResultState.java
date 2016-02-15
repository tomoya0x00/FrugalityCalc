package miwax.java_conf.gr.jp.frugalitycalc.model;

/**
 * 計算結果表示状態
 */
public class ResultState implements State {
    private static ResultState ourInstance = new ResultState();

    public static ResultState getInstance() {
        return ourInstance;
    }

    private ResultState() {
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
