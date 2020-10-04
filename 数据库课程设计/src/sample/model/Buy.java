package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 天逸流水
 * @date 2020/6/19 10:06
 */
public class Buy {
    private final StringProperty no;
    private final StringProperty factory;
    private final StringProperty raw;
    private final StringProperty size;
    private final StringProperty amount;
    private final StringProperty unit;
    private final StringProperty price;
    private final StringProperty allprice;
    private final StringProperty time;

    public Buy(String no, String factory, String raw, String size, String amount, String unit, String price, String allprice, String time) {
        this.no = new SimpleStringProperty(no);
        this.factory = new SimpleStringProperty(factory);
        this.raw = new SimpleStringProperty(raw);
        this.size = new SimpleStringProperty(size);
        this.amount = new SimpleStringProperty(amount);
        this.unit = new SimpleStringProperty(unit);
        this.price = new SimpleStringProperty(price);
        this.allprice = new SimpleStringProperty(allprice);
        this.time = new SimpleStringProperty(time);
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

    public String getFactory() {
        return factory.get();
    }

    public StringProperty factoryProperty() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory.set(factory);
    }

    public String getRaw() {
        return raw.get();
    }

    public StringProperty rawProperty() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw.set(raw);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
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

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getAllprice() {
        return allprice.get();
    }

    public StringProperty allpriceProperty() {
        return allprice;
    }

    public void setAllprice(String allprice) {
        this.allprice.set(allprice);
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
}
