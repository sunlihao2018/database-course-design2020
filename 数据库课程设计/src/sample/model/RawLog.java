package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * @author 天逸流水
 * @date 2020/6/13 16:29
 */
public class RawLog {
    private final StringProperty logno;
    private final StringProperty rawno;
    private final StringProperty where;
    private final StringProperty size;
    private final StringProperty amount;
    private final StringProperty unit;
    private final StringProperty time;
    private final StringProperty explain;

    public RawLog(String logno, String rawno, String where, String size, String amount, String unit, String time, String explain) {
        this.logno = new SimpleStringProperty(logno);
        this.rawno = new SimpleStringProperty(rawno);
        this.where = new SimpleStringProperty(where);
        this.size = new SimpleStringProperty(size);
        this.amount = new SimpleStringProperty(amount);
        this.unit = new SimpleStringProperty(unit);
        this.time = new SimpleStringProperty(time);
        this.explain = new SimpleStringProperty(explain);
    }

    public String getLogno() {
        return logno.get();
    }

    public void setLogno(String logno) {
        this.logno.set(logno);
    }

    public StringProperty lognoProperty() {
        return logno;
    }

    public String getRawno() {
        return rawno.get();
    }

    public void setRawno(String rawno) {
        this.rawno.set(rawno);
    }

    public StringProperty rawnoProperty() {
        return rawno;
    }

    public String getWhere() {
        return where.get();
    }

    public void setWhere(String where) {
        this.where.set(where);
    }

    public StringProperty whereProperty() {
        return where;
    }

    public String getSize() {
        return size.get();
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public StringProperty timeProperty() {
        return time;
    }

    public String getExplain() {
        return explain.get();
    }

    public void setExplain(String explain) {
        this.explain.set(explain);
    }

    public StringProperty explainProperty() {
        return explain;
    }
}
