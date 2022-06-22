package fryctze.college.cashbox.menu.home;

public class ModelGoal {
    private int id;
    private String name, date, nominal, desc;

    public ModelGoal(int id) {
        this.id = id;
    }

    public ModelGoal(String name, String date, String nominal, String desc) {
        this.name = name;
        this.date = date;
        this.nominal = nominal;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
