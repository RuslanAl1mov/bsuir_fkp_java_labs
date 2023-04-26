package Controller_pack;

import java.io.*;
import java.util.*;

public class Controller {
    private List<Flower> flowers = new ArrayList<>();
    private List<Bucket> buckets = new ArrayList<>();
    private String Buckets_FILE_PATH = "src\\Controller_pack\\Memory\\Buckets.txt";
    private String Flowers_FILE_PATH = "src\\Controller_pack\\Memory\\Flowers.txt";

    public Controller() {
        loadFlowersFile();
        loadBucketsFile();
    }

    public void createFlower(String newFlowerName, int newFlowerPrice, int newFlowerFresh, int newFlowerStemLength) {
        Flower newFlower = new Flower(newFlowerName, newFlowerPrice, newFlowerFresh, newFlowerStemLength);
        flowers.add(newFlower);
        saveFlowersInFile();
    }

    public void createBucket(String bucketName, List<Flower> bucketFlowers){
        Bucket newBucket = new Bucket(bucketName);
        newBucket.setFlowers(bucketFlowers);
        buckets.add(newBucket);
        saveBucketsInFile();
    }

    public List<Bucket> getBuckets() {
        return buckets;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void sortBucketsByPrice(){
        Collections.sort(buckets, new Comparator<Bucket>() {
            @Override
            public int compare(Bucket b1, Bucket b2) {
                return Integer.compare(b1.getBucketPrice(), (b2.getBucketPrice()));
            }
        });
    }

    public void saveFlowersInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Flowers_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(flowers);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBucketsInFile(){
        try {
            FileOutputStream fos= new FileOutputStream(Buckets_FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(buckets);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFlowersFile(){
        try {
            FileInputStream fis = new FileInputStream(Flowers_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.flowers = (List<Flower>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadBucketsFile(){
        try {
            FileInputStream fis = new FileInputStream(Buckets_FILE_PATH);

            ObjectInputStream ois = new ObjectInputStream(fis);
            this.buckets = (List<Bucket>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
