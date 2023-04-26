public class Candy{
    private String candyName;
    private int sugarNum;
    private int candyWeight;


    public Candy(String newCandyName, int newSugarNum, int newCandyWeight) {
        this.candyName = newCandyName;
        this.sugarNum = newSugarNum;
        this.candyWeight = newCandyWeight;
    }

    // Задать новое имя Конфете
    public void setCandyName(String newCandyName){
        this.candyName = newCandyName;
    }

    // Получить имеющееся имя Конфеты
    public String getCandyName(){
        return candyName;
    }

    // Получить кол-во сахара в Конфете
    public int getSugarNum(){
        return sugarNum;
    }


    // Получить вес Конфеты
    public int getCandyWeight(){
        return candyWeight;
    }

    // Изменить вес Конфеты
    public void setCandyWeight(int newCandyWeight){
        this.candyWeight = newCandyWeight;
    }

}

