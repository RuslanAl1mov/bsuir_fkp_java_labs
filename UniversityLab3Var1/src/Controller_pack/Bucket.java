package Controller_pack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Bucket implements Serializable {
    private String bucketName;
    private List<Flower> bucketFlowers = new ArrayList<>();


    public Bucket(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }


    public void setFlowers(List<Flower> flowers) {
        this.bucketFlowers = flowers;
    }

    public List<Flower> getFlowers() {
        return bucketFlowers;
    }

    public int getBucketPrice() {
        int bucketCost = 0;
        for (Flower flower: bucketFlowers){
            bucketCost = bucketCost + flower.getFlowerPrice();
        }
        return bucketCost;
    }


}

