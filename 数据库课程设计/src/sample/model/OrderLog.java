package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 天逸流水
 * @date 2020/6/19 16:34
 */
public class OrderLog {
    private final StringProperty no;
    private final StringProperty name;
    private final StringProperty amount;
    private final StringProperty time;
    private final StringProperty explain;

    public OrderLog(String no, String name, String amount, String time, String explain){
        this.no = new SimpleStringProperty(no);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleStringProperty(amount);
        this.time = new SimpleStringProperty(time);
        this.explain = new SimpleStringProperty(explain);
    }

}
