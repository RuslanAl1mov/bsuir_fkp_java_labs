import java.util.*;


public class Ekipir{
    private String ekipirName;
    private int ekipirWeight;
    private int ekipirCost;


    public Ekipir (String newEkipirName, int newEkipirCost, int newEkipirWeight) {
        this.ekipirName = newEkipirName;
        this.ekipirWeight = newEkipirWeight;
        this.ekipirCost = newEkipirCost;
    }

    // Задать новое название Экипировке
    public void setEkipirName(String ekipirName) {
        this.ekipirName = ekipirName;
    }

    // Получить имеющееся название Экипировки
    public String getEkipirName() {
        return ekipirName;
    }

    // Изменить цену Экипировки
    public void setEkipirCost(int ekipirCost) {
        this.ekipirCost = ekipirCost;
    }

    // Получить цену Экипировки
    public int getEkipirCost() {
        return ekipirCost;
    }

    public void setEkipirWeight(int ekipirWeight) {
        this.ekipirWeight = ekipirWeight;
    }

    public int getEkipirWeight() {
        return ekipirWeight;
    }
}
