import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Сортировка песен НА ДИСКЕ по жанру
public class SortSongsByType {

    public SortSongsByType(List<Song> songsOnDisk) {
        System.out.println("\nСОРТРОВКА ПЕСЕН НА ДИСКЕ ПО ЖАНРУ");
        if (songsOnDisk.size() != 0) {

            Collections.sort(songsOnDisk, new Comparator<Song>() {
                @Override
                public int compare(Song s1, Song s2) {
                    return CharSequence.compare(s1.getSongType(), (s2.getSongType()));
                }
            });

            for (Song song : songsOnDisk) {
                System.out.println("  - " + song.getSongName() + " (" + Float.toString(song.getSongLength()) + ")  Стиль: " + song.getSongType());
            }
        } else {try{
            throw new NoMusicException("Здесь нет музыки");
        }catch (NoMusicException e){
            System.err.println(e.getMessage());
        }}
    }
}
