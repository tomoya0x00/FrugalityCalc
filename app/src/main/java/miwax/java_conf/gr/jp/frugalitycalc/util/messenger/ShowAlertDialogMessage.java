package miwax.java_conf.gr.jp.frugalitycalc.util.messenger;

public class ShowAlertDialogMessage implements Message {
    private final String title;
    private final String text;

    public ShowAlertDialogMessage(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
