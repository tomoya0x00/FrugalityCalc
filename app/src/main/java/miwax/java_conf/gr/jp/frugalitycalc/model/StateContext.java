package miwax.java_conf.gr.jp.frugalitycalc.model;

import java.math.BigDecimal;

public class StateContext {
    private State state;
    private BigDecimal A;
    private BigDecimal B;
    private Operation operation;
    private Display display;

    public StateContext() {
        this.A = new BigDecimal("0");
        this.B = new BigDecimal("0");
        setState(InputAState.getInstance());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public BigDecimal getA() {
        return A;
    }

    public void setA(BigDecimal a) {
        A = a;
    }

    public void clearA() {
        setA(new BigDecimal(CalcNumber.ZERO.getString()));
    }

    public BigDecimal getB() {
        return B;
    }

    public void setB(BigDecimal b) {
        B = b;
    }

    public void clearB() {
        setB(new BigDecimal(CalcNumber.ZERO.getString()));
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void clearOperation() {
        setOperation(null);
    }

    public Display getDisplay() {
        return this.display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void doCalc() {
        BigDecimal result = this.operation.apply(this.A, this.B);
        this.display.setString(result.toString());
    }

    public void onInputNumber(CalcNumber input) {
        this.state.onInputNumber(this, input);
    }

    public void onInputOperator(Operation operator) {
        this.state.onInputOperator(this, operator);
    }

    public void onInputEqual() {
        this.state.onInputEqual(this);
    }

    public void onInputClearEnd() {
        this.state.onInputClearEnd(this);
    }

    public void onInputAllClear() {
        this.state.onInputAllClear(this);
    }
}
