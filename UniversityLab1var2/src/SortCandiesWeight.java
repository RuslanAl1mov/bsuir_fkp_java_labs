import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сортировка конфет по весу
public class SortCandiesWeight {

    public SortCandiesWeight(List<Candy> candies){
        Collections.sort(candies, new Comparator<Candy>() {
            @Override
            public int compare(Candy c1, Candy c2) {
                return Integer.compare(c1.getCandyWeight(), (c2.getCandyWeight()));
            }});

        for (Candy candy : candies) {
            System.out.println(" - "+candy.getCandyName()+" "+candy.getCandyWeight()+" гр.  Сахар: " + candy.getSugarNum());
        }
    }
}
