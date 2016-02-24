package miwax.java_conf.gr.jp.frugalitycalc.view;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import miwax.java_conf.gr.jp.frugalitycalc.R;
import miwax.java_conf.gr.jp.frugalitycalc.databinding.ActivityMainBinding;
import miwax.java_conf.gr.jp.frugalitycalc.util.messenger.ShowAlertDialogMessage;
import miwax.java_conf.gr.jp.frugalitycalc.viewmodel.MainViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private final String VIEW_MODEL = "VIEW_MODEL";

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        if (savedInstanceState != null) {
            mainViewModel = savedInstanceState.getParcelable(VIEW_MODEL);
        } else {
            mainViewModel = new MainViewModel();
        }
        binding.setViewModel(mainViewModel);

        // ダイアログ表示のメッセージ受信
        subscriptions.add(
                mainViewModel.getMessenger().register(ShowAlertDialogMessage.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShowAlertDialogMessage>() {
                    @Override
                    public void call(ShowAlertDialogMessage message) {
                        showAlertDialog(message.getTitleId(), message.getTextId());
                    }
                })
        );
   }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(VIEW_MODEL, mainViewModel);
    }

    @Override
    protected void onDestroy() {
        mainViewModel.unsubscribe();
        super.onDestroy();
    }

    private void showAlertDialog(int titleId, int textId) {
        new AlertDialog.Builder(this)
                .setTitle(this.getString(titleId))
                .setMessage(this.getString(textId))
                .setPositiveButton("OK", null)
                .show();
    }
}
