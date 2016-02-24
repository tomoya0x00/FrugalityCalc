package miwax.java_conf.gr.jp.frugalitycalc.util.messenger;

public class ShowAlertDialogMessage implements Message {
    private final int titleId;
    private final int textId;

    public ShowAlertDialogMessage(int titleId, int textId) {
        this.titleId = titleId;
        this.textId = textId;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getTextId() {
        return textId;
    }
}
