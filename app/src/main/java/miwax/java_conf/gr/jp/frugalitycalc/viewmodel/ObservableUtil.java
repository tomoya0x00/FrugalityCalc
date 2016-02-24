package miwax.java_conf.gr.jp.frugalitycalc.viewmodel;

import android.databinding.ObservableField;

import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * This file is not under the MIT License
 * original:
 * https://github.com/amay077/StopWatchSample/blob/android_data_binding/StopWatchAppAndroid/app/src/main/java/com/amay077/stopwatchapp/viewmodel/ObservableUtil.java
 */
public final class ObservableUtil {
    private ObservableUtil() {
    }

    /**
     * rx.Observable から ObservableField への変換をおこなう
     */
    static public <T> ObservableField<T> toObservableField(Observable<T> source, CompositeSubscription subscriptions) {
        final ObservableField<T> field = new ObservableField<>();

        subscriptions.add(
                source.subscribe(new Action1<T>() {
                    @Override
                    public void call(T x) {
                        field.set(x);
                    }
                })
        );

        return field;
    }
}