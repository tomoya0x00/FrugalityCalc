package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.app.AlertDialog;
import android.content.Context;

public class Dialog {
    private Context context;

    public Dialog(Context context) {
        this.context = context;
    }

    public void show(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", null).show();
    }
}
