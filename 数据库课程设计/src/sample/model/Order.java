package sample.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 天逸流水
 * @date 2020/6/19 16:18
 */
public class Order {
    private final StringProperty no;
    private final StringProperty people;
    private final StringProperty company;
    private final StringProperty phone;
    private final StringProperty site;
    private final StringProperty time;
    private final StringProperty state;
    private final StringProperty explain;

    public Order(String no, String people, String company, String phone, String site, String time, String state, String explain) {
        this.no = new SimpleStringProperty(no);
        this.people = new SimpleStringProperty(people);
        this.company = new SimpleStringProperty(company);
        this.phone = new SimpleStringProperty(phone);
        this.site = new SimpleStringProperty(site);
        this.time = new SimpleStringProperty(time);
        this.state = new SimpleStringProperty(state);
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

    public String getPeople() {
        return people.get();
    }

    public StringProperty peopleProperty() {
        return people;
    }

    public void setPeople(String people) {
        this.people.set(people);
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public void setSite(String site) {
        this.site.set(site);
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

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
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
