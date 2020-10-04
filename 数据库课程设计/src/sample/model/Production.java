package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 天逸流水
 * @date 2020/6/14 2:17
 */
public class Production {
    private final StringProperty no;
    private final StringProperty name;
    private final StringProperty amount;
    private final StringProperty unit;

    public Production(String no, String name, String amount, String unit) {
        this.no = new SimpleStringProperty(no);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty(amount);
        this.unit = new SimpleStringProperty(unit);
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
}
