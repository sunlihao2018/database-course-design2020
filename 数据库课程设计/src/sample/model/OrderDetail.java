package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 天逸流水
 * @date 2020/6/19 16:23
 */
public class OrderDetail {
    private final StringProperty no;
    private final StringProperty orderno;
    private final StringProperty name;
    private final StringProperty amount;

    public OrderDetail(String no, String orderno, String name, String amount) {
        this.no = new SimpleStringProperty(no);
        this.orderno = new SimpleStringProperty(orderno);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty(amount);
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

    public String getOrderno() {
        return orderno.get();
    }

    public StringProperty ordernoProperty() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno.set(orderno);
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
}
