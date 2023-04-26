package Administrator.CreatePresentWindow.entity;

import Administrator.CreateCandyWindow.entity.Candy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Present implements Serializable {
    private String presentName;
    private List<Candy> presentCandies = new ArrayList<>();


    public Present(String presentName, List<Candy> presentCandies) {
        this.presentName = presentName;
        this.presentCandies = presentCandies;
    }

    public String getPresentName() {
        return presentName;
    }

    public List<Candy> getPresentCandies() {
        return presentCandies;
    }

    public void setPresentCandies(List<Candy> presentCandies) {
        this.presentCandies = presentCandies;
    }

    public int getPresentWeight(){
        int weight = 0;
        for(Candy candy: presentCandies){
            weight = weight + candy.getCandyWeight();
        }
        return weight;
    }
}
