package fryctze.college.cashbox.menu.history;

public class ModelTransaction {
    private String name, date, nominal;
    private boolean isGain;

    public ModelTransaction(String name, String date, String nominal, boolean isGain) {
        this.name = name;
        this.date = date;
        this.nominal = nominal;
        this.isGain = isGain;
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
}
