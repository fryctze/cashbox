package fryctze.college.cashbox.menu.history;

public class ModelTransaction {
    private String name, date, nominal;

    public ModelTransaction(String name, String date, String nominal) {
        this.name = name;
        this.date = date;
        this.nominal = nominal;
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
}
