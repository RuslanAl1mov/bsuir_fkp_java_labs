import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сортировка конфет по кол-ву сахара
public class SortCandiesSugar {

    public SortCandiesSugar(List<Candy> candies){
        Collections.sort(candies, new Comparator<Candy>() {
            @Override
            public int compare(Candy c1, Candy c2) {
                return Integer.compare(c1.getSugarNum(), (c2.getSugarNum()));
            }});

        for (Candy candy : candies) {
            System.out.println(" - "+candy.getCandyName()+" "+candy.getCandyWeight()+" гр.  Сахар: " + candy.getSugarNum());        }
    }
}
