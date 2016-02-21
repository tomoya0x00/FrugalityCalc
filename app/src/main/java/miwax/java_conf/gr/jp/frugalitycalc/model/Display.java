package miwax.java_conf.gr.jp.frugalitycalc.model;

import android.databinding.ObservableField;

import java.math.BigDecimal;

public class Display {
    private ObservableField<String> result;
    private ObservableField<String> memory;

    public Display(ObservableField<String> result, ObservableField<String> memory) {
        this.result = result;
        this.memory = memory;
    }

    // 文字列セット
    public void setString(String str) {
        result.set(str);
    }

    // 文字列ゲット
    public String getString() {
        return result.get();
    }

    // 全消去
    public void clear() {
        setString(CalcNumber.ZERO.getString());
    }

    // メモリー表示部にセット
    public void setMemory(BigDecimal decimal) {
        memory.set("M:" + decimal.toString());
    }

    // 末尾一文字削除
    public void clearEnd() {
        String str = getString();
        if (str.length() > 1) {
            str = str.substring(0, str.length()-1);
        } else {
            str = CalcNumber.ZERO.getString();
        }
        setString(str);
    }

    // 末尾にCalcNuber追加
    public void addNumber(CalcNumber num) {
        String nowStr = getString();

        // すでにドットが表示済みかチェック
        if (num == CalcNumber.DOT && nowStr.contains(CalcNumber.DOT.getString())) {
            return;
        }

        // 表示が"0"かつ入力が数字であれば、追加では無く置き換える
        if (num != CalcNumber.DOT && nowStr.equals(CalcNumber.ZERO.getString())) {
            setString(num.getString());
            return;
        }

        setString(nowStr + num.getString());
    }

    // CalcNumberセット
    public void setNumber(CalcNumber num) {
        if (num == CalcNumber.DOT) {
            setString("0.");
            return;
        }

        setString(num.getString());
    }
}
