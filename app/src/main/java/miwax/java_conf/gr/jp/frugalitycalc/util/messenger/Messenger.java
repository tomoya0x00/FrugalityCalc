package miwax.java_conf.gr.jp.frugalitycalc.util.messenger;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * This file is not under the MIT License
 * original:
 * https://github.com/amay077/StopWatchSample/blob/android_data_binding/StopWatchAppAndroid/app/src/main/java/com/amay077/stopwatchapp/frameworks/messengers/Messenger.java
 */
public class Messenger {
    private final Subject<Message, Message> _bus =
            new SerializedSubject<>(PublishSubject.<Message>create());

    public void send(Message message) {
        _bus.onNext(message);
    }

    public <T extends Message> Observable<T> register(final Class<? extends T> messageClazz) {
        return _bus
                .ofType(messageClazz)
                .map(new Func1<Message, T>() {
                    @Override
                    public T call(Message message) {
                        return (T) message;
                    }
                });
    }
}
