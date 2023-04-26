import java.util.ArrayList;
import java.util.List;


public class Bucket {
    public String bucketName;
    public List<Flower> flowers = new ArrayList<>();
    public List<Souvenir> souvenirs = new ArrayList<>();


    public Bucket() {

    }

    public Bucket(String bucketName) {
        this.bucketName = bucketName;
        this.flowers = new ArrayList<Flower>();
    }

    public String getBucketName(){
        return bucketName;
    }

    public void setBucketName(String newBucketName){
        this.bucketName = newBucketName;
    }

    public void addFlower(Flower newFlower){
        flowers.add(newFlower);
    }

    public void addSouvenirs(Souvenir newSouvenir){
        souvenirs.add(newSouvenir);
    }


}

