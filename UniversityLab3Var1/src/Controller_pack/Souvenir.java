package Controller_pack;

public class Souvenir {
    private  String souvenirName;  // Название сувенира
    private int souvenirPrice;  // Цена за сувенир

    public Souvenir() {

    }

    public Souvenir(String souvenirName, int souvenirPrice) {
        this.souvenirName = souvenirName;
        this.souvenirPrice = souvenirPrice;
    }

    public String getSouvenirName() {
        return souvenirName;
    }

    public void setSouvenirName(String newSouvenirName) {
        this.souvenirName = newSouvenirName;
    }

    public int getSouvenirPrice() {
        return souvenirPrice;
    }

    public void setSouvenirPrice(int newSouvenirPrice) {
        this.souvenirPrice = newSouvenirPrice;
    }

}
