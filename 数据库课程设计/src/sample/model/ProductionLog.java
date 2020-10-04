package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 天逸流水
 * @date 2020/6/14 2:20
 */
public class ProductionLog {
    private final StringProperty no;
    private final StringProperty name;
    private final StringProperty where;
    private final StringProperty amount;
    private final StringProperty unit;
    private final StringProperty time;
    private final StringProperty explain;

    public ProductionLog(String no, String name, String where, String amount, String unit, String time, String explain) {
        this.no = new SimpleStringProperty(no);
        this.name = new SimpleStringProperty(name);
        this.where = new SimpleStringProperty(where);
        this.amount = new SimpleStringProperty(amount);
        this.unit = new SimpleStringProperty(unit);
        this.time = new SimpleStringProperty(time);
        this.explain = new SimpleStringProperty(explain);
    }

    public String getNo() {
        return no.get();
    }

    public StringProperty noProperty() {
        return no;
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getWhere() {
        return where.get();
    }

    public StringProperty whereProperty() {
        return where;
    }

    public void setWhere(String where) {
        this.where.set(where);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getUnit() {
        return unit.get();
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getExplain() {
        return explain.get();
    }

    public StringProperty explainProperty() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain.set(explain);
    }
}
