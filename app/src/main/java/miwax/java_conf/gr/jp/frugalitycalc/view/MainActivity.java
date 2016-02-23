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

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new MainViewModel(this);
        binding.setViewModel(mainViewModel);

        // ダイアログ表示のメッセージ受信
        subscriptions.add(
                mainViewModel.getMessenger().register(ShowAlertDialogMessage.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShowAlertDialogMessage>() {
                    @Override
                    public void call(ShowAlertDialogMessage message) {
                        showAlertDialog(message.getTitle(), message.getText());
                    }
                })
        );


        /*
        if (savedInstanceState != null) {
            context = savedInstanceState.getParcelable(CONTEXT);
            resultText.setText(savedInstanceState.getString(RESULT_TEXT));
            memoryText.setText(savedInstanceState.getString(MEMORY_TEXT));
        } else {
            context = new StateContext(getApplicationContext());
        }
        */
   }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO: Support onSaveInstance
        /*
        outState.putParcelable(CONTEXT, context);
        outState.putString(RESULT_TEXT, resultText.getText().toString());
        outState.putString(MEMORY_TEXT, memoryText.getText().toString());
        */
    }

    @Override
    protected void onDestroy() {
        mainViewModel.unsubscribe();
        super.onDestroy();
    }

    private void showAlertDialog(String title, String text) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("OK", null)
                .show();
    }
}
