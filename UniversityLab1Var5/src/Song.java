import java.io.Serializable;

public class Song implements Serializable {
    private String songName;
    private String songType;
    private float songLength;


    public Song(String songName, String songType, float songLength) {
        this.songName = songName;
        this.songType = songType;
        this.songLength = songLength;
    }

    // Получить название композиципи
    public String getSongName() {
        return songName;
    }

    // Изменить название композиципи
    public void setSongName(String songName) {
        this.songName = songName;
    }

    // Получить Жанр композиципи
    public String getSongType() {
        return songType;
    }

    // Изменить жанр композиции
    public void setSongType(String songType) {
        this.songType = songType;
    }

    // Получит длинну композиции (в минутах)
    public float getSongLength() {
        return songLength;
    }

    // Изменить значение длинны композиции (в минутах)
    public void setSongLength(float songLength) {
        this.songLength = songLength;
    }
}

