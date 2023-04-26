public class Flower {
    private  String flowerName;  // Название цветка
    private int flowerPrice;  // Цена за цветок
    private int flowerFresh;  // свежесть цветка(в днях)
    private int stemLength;  // длинна стебля

    public Flower() {

    }

    public Flower(String flowerName, int flowerPrice, int flowerFresh, int stemLength) {
        this.flowerName = flowerName;
        this.flowerPrice = flowerPrice;
        this.flowerFresh = flowerFresh;
        this.stemLength = stemLength;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String newFlowerName) {
        this.flowerName = newFlowerName;
    }

    public int getFlowerPrice() {
        return flowerPrice;
    }

    public void setFlowerPrice(int newFlowerPrice) {
        this.flowerPrice = newFlowerPrice;
    }

    public int getFlowerFresh() {
        return flowerFresh;
    }

    public int getStemLength() {
        return stemLength;
    }

}

