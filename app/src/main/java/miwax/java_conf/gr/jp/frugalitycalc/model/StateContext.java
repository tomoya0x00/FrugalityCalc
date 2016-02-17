package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class StateContext implements Parcelable {
    private State state;
    private BigDecimal A;
    private BigDecimal B;
    private Operation operation;
    private Display display;

    public StateContext() {
        clearA();
        clearB();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(state);
        dest.writeString(A.toString());
        dest.writeString(B.toString());
        dest.writeSerializable(operation);
    }

    public static final Parcelable.Creator<StateContext> CREATOR
            = new Parcelable.Creator<StateContext>() {
        @Override
        public StateContext createFromParcel(Parcel parcel) {
            return new StateContext(parcel);
        }

        @Override
        public StateContext[] newArray(int i) {
            return new StateContext[i];
        }
    };

    private StateContext(Parcel parcel) {
        this.state = (State)parcel.readSerializable();
        this.A = new BigDecimal(parcel.readString());
        this.B = new BigDecimal(parcel.readString());
        this.operation =  (Operation)parcel.readSerializable();
    }
}

