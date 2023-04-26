public class Drugs {
    private String drugName;
    private Vipolnayushiy drugMaster;


    public Drugs(String drugName) {
        this.drugName = drugName;
    }

    // Задать название лекарству
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    // Получить название лекарства
    public String getDrugName() {
        return drugName;
    }

    public void setDrugMaster(Vipolnayushiy drugMaster) {
        this.drugMaster = drugMaster;
    }

    public Vipolnayushiy getDrugMaster() {
        return drugMaster;
    }
}

