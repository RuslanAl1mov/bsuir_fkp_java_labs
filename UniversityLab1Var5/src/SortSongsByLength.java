import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// СОРТРОВКА ПЕСЕН НА ДИСКЕ ПО ДЛИНЕ КОМПОЗИЦИИ
public class SortSongsByLength {

    public SortSongsByLength(List<Song> songsOnDisk){
        System.out.println("\nСОРТРОВКА ПЕСЕН НА ДИСКЕ ПО ДЛИНЕ КОМПОЗИЦИИ");
        if (songsOnDisk.size() != 0) {

            Collections.sort(songsOnDisk, new Comparator<Song>() {
                @Override
                public int compare(Song s1, Song s2) {
                    return Float.compare(s1.getSongLength(), (s2.getSongLength()));
                }
            });

            for (Song song : songsOnDisk) {
                System.out.println("  - " + song.getSongName() + " (" + Float.toString(song.getSongLength()) + ")  Стиль: " + song.getSongType());
            }
        } else {System.out.println("У тебя нет песен!");}
    }
}
