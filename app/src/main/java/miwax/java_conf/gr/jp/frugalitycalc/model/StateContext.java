package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class StateContext implements Parcelable {

    private State state = InputAState.INSTANCE;
    private BigDecimal A = new BigDecimal(CalcNumber.ZERO.getString());
    private BigDecimal B = new BigDecimal(CalcNumber.ZERO.getString());
    private final BehaviorSubject<Operation> operation = BehaviorSubject.create(Operation.NONE);
    private Editor editor = new Editor();
    private final BehaviorSubject<BigDecimal> memory = BehaviorSubject.create(new BigDecimal(CalcNumber.ZERO.getString()));
    private final PublishSubject<CalcError> error = PublishSubject.create();

    public StateContext() {
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Observable<Operation> getObservableOperation() {
        return operation;
    }

    public Observable<BigDecimal> getObservableMemory() {
        return memory;
    }

    public Observable<String> getObservableResult() {
        return getEditor().getObservable();
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
        return operation.getValue();
    }

    public void setOperation(Operation operation) {
        this.operation.onNext(operation);
    }

    public void clearOperation() {
        setOperation(Operation.NONE);
    }

    public Editor getEditor() {
        return this.editor;
    }

    public BigDecimal getMemory() {
        return memory.getValue();
    }

    public void setMemory(BigDecimal memory) {
        this.memory.onNext(memory);
    }

    public void clearMemory() {
        setMemory(new BigDecimal(CalcNumber.ZERO.getString()));
    }

    public void notifyError(CalcError error) {
        this.error.onNext(error);
    }

    public Observable<CalcError> getObservableError() {
        return error;
    }

    public void doCalc() {
        BigDecimal result = this.operation.getValue().apply(this.A, this.B);
        this.editor.setString(result.toString());
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

    public void onInputMemoryRead() {
        this.state.onInputMemoryRead(this);
    }

    public void onInputMemoryClear() {
        this.state.onInputMemoryClear(this);
    }

    public void onInputMemoryPlus() {
        this.state.onInputMemoryPlus(this);
    }

    public void onInputMemoryMinus() {
        this.state.onInputMemoryMinus(this);
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
        dest.writeSerializable(operation.getValue());
        dest.writeParcelable(editor, flags);
        dest.writeString(memory.getValue().toString());
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
        this.operation.onNext((Operation)parcel.readSerializable());
        this.editor = parcel.readParcelable(Editor.class.getClassLoader());
        this.memory.onNext(new BigDecimal(parcel.readString()));
    }
}

