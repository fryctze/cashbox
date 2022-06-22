package fryctze.college.cashbox.menu.history;

public class ModelTransaction {
    private int id;
    private String name, date, nominal, desc;
    private boolean isGain;

    public ModelTransaction(int id) {
        this.id = id;
    }

    public ModelTransaction(String name, String date, String nominal, boolean isGain, String desc) {
        this.name = name;
        this.date = date;
        this.nominal = nominal;
        this.isGain = isGain;
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

    public boolean isGain() {
        return isGain;
    }

    public void setGain(boolean gain) {
        isGain = gain;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
