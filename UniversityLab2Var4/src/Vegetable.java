import java.io.Serializable;

public class Vegetable implements Serializable {
    private String vegetableName;
    private int kalNum;


    public Vegetable(String newVegetableName, int newKalNum) {
        this.vegetableName = newVegetableName;
        this.kalNum = newKalNum;
    }

    // Задать новое имя Овощю(тебе)
    public void setCandyName(String newVegetableName) {
        this.vegetableName = newVegetableName;
    }

    // Получить имеющееся имя Овоща(твое)
    public String getVegetableName() {
        return vegetableName;
    }

    // Получить кол-во калорий (твое, в тоннах)
    public int getKalNum() {
        return kalNum;
    }

}