import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static List<Song> existedSongs = new ArrayList<>();
    private static List<Song> songsOnDisk = new ArrayList<>();
    private static final String FILE_PATH1 = "ExistedMusic.data";
    private static final String FILE_PATH2 = "dickMusic.data";

    public static void main(String[] args) {
        while(true){
            System.out.println("\nМеню:");
            System.out.println("  1) Создать композицию");
            System.out.println("  2) Записать композицию на диск");
            System.out.println("  3) Список существующих композиций");
            System.out.println("  4) Список композиций на диске");
            System.out.println("  5) Найти композицию по длинне тайм-кода");
            System.out.println("  6) Сортировать композиции по жанру");
            System.out.println("  7) Сортировать композиции по длине тайм-кода");
            System.out.println("  8) Сохранить музыку");
            System.out.println("  9) Загрузить музыку");


            Scanner consoleChoose = new Scanner(System.in);
            System.out.print("Выбери: ");
            String choose = consoleChoose.nextLine();

            switch (choose){
                case "1": {createSong(); break;}
                case "2": {addSongToDisc(); break;}
                case "3": {showExistedSongs(); break;}
                case "4": {showSongsOnDisk(); break;}
                case "5": {findSongsByLength(); break;}
                case "6": {new SortSongsByType(songsOnDisk); break;}
                case "7": {new SortSongsByLength(songsOnDisk); break;}
                case "8": {saveMusicInFile(); break;}
                case "9": {loadFile(); break;}
            }
        }
    }


    public static void createSong() {  // функция создания новой композиции
        System.out.println("\nСоздать новую композицию:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Композиции: ");
        String newSongName = consoleChoose.nextLine();  // Вводим название Композиции

        System.out.print("Стиль композиции: ");
        String newSongType = consoleChoose.nextLine();  // Вводим стиль


        System.out.print("Длина композиции(в мин.): ");
        String newSongLength = consoleChoose.nextLine();  // Вводим длинну композиции (в минутах: 6.00, 3.23)
        try {
            float floatLength = Float.parseFloat(newSongLength);  // Преобразование в число с запятой
            Song newSong = new Song(newSongName, newSongType, floatLength);
            existedSongs.add(newSong);
            System.out.println(newSongName + " теперь в списке!\n");
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }
    }


    public static void addSongToDisc() {  // Выбираем и записываем композицию на диск
        int numeric = 1;
        float allSongsLength = 0;

        System.out.println("\nВЫБЕРИ КОМПОЗИЦИЮ, ЧТОБЫ ЗАПИСАТЬ ЕЕ НА ДИСК:");
        if (existedSongs.size() != 0) {
            for (Song song : existedSongs) {
                System.out.println(numeric + ") " + song.getSongName() + " (" + Float.toString(song.getSongLength()) + ")  Стиль: " + song.getSongType());
                numeric++;
            }
            try {
                Scanner consoleChoose = new Scanner(System.in);
                System.out.print("Выбери: ");
                String choose = consoleChoose.nextLine();
                int intChoose = Integer.parseInt(choose);  // Преобразование в целое число

                // Выбираем и записываем композицию на диск
                if (intChoose <= existedSongs.size()) {
                    songsOnDisk.add(existedSongs.get(intChoose - 1));
                }

                numeric = 1;
                System.out.println("\nОбновленный список композиций на диске:");
                for (Song diskSong : songsOnDisk) {
                    System.out.println(numeric + ") " + diskSong.getSongName() + " (" + Float.toString(diskSong.getSongLength()) + ")  Стиль: " + diskSong.getSongType());
                    numeric++;
                    allSongsLength += diskSong.getSongLength();
                }

                System.out.println("Общая длинна композиций записанных на диск: " + Float.toString(allSongsLength) + "\n");
            } catch (Exception e) {
                System.out.println("Неправильный формат строки!");
            }
        } else {
            try{
                throw new NoMusicException("Здесь нет музыки");
            }catch (NoMusicException e){
                System.err.println(e.getMessage());
            }
        }
    }

    private static void showExistedSongs() {  // Функция выводит весь список созданных композиций
        System.out.println("\nВСЕ СОЗДАННЫЕ ПЕСНИ:");
        if (existedSongs.size() != 0) {
            int numeric = 1;
            for (Song song : existedSongs) {
                System.out.println(numeric + ") " + song.getSongName() + " (" + Float.toString(song.getSongLength()) + ")  Стиль: " + song.getSongType());
                numeric++;
            }
        } else {
            try {
                throw new NoMusicException("Здесь нет музыки");
            } catch (NoMusicException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void showSongsOnDisk() {  // Функция выводит список композиций, записанных на диск
        float allSongsLength = 0;
        int numeric = 1;

        System.out.println("\nКОМПОЗИЦИИ ЗАПИСАННЫЕ НА ДИСК:");
        if (songsOnDisk.size() != 0) {
            for (Song diskSong : songsOnDisk) {
                System.out.println(numeric + ") " + diskSong.getSongName() + " (" + Float.toString(diskSong.getSongLength()) + ")  Стиль: " + diskSong.getSongType());
                numeric++;
                allSongsLength += diskSong.getSongLength();
            }
            System.out.println("Общая длина композиций записанных на диск: " + Float.toString(allSongsLength) + "\n");

        } else {
            try{
                throw new NoMusicException("Здесь нет музыки");
            }catch (NoMusicException e){
                System.err.println(e.getMessage());
            };
        }
    }

    public static void findSongsByLength() {  // функция поиска композиций НА ДИСКЕ по вписанному диапазону длинны композиции
        System.out.println("\nДИАПАЗОН ТАЙМ-КОДА:");
        try {
            if (songsOnDisk.size() != 0) {

                Scanner consoleChoose = new Scanner(System.in);

                System.out.print("От: ");
                String Ot = consoleChoose.nextLine();
                float OtInt = Float.parseFloat(Ot);  // Преобразование в число с запятой

                System.out.print("До: ");
                String Do = consoleChoose.nextLine();
                float DoInt = Float.parseFloat(Do);  // Преобразование в число с запятой

                for (Song song : songsOnDisk) {
                    if (OtInt <= song.getSongLength() & song.getSongLength() <= DoInt) {
                        System.out.println(" -" + song.getSongName() + " (" + Float.toString(song.getSongLength()) + ")  Стиль: " + song.getSongType());
                    }
                }
            } else {
                try{
                    throw new NoMusicException("Здесь нет музыки");
                }catch (NoMusicException e){
                    System.err.println(e.getMessage());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Неправильный формат строки!");
        }
    }

    // Сериализация класса
    public static void saveMusicInFile(){  // Сохранение музыки в файл в файл
        try {
            FileOutputStream fos= new FileOutputStream(FILE_PATH1);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(existedSongs);
            oos.close();

            FileOutputStream fos1= new FileOutputStream(FILE_PATH2);
            ObjectOutputStream oos1=new ObjectOutputStream(fos1);
            oos1.writeObject(songsOnDisk);
            oos1.close();
            System.out.println("\nМузыка сохранена!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Десерилизация класса
    public static void loadFile(){  // Загрузка сохраненных песен из файла
        System.out.println("\nЗАГРУЗКА ФАЙЛА:");
        try {
            FileInputStream fis1 = new FileInputStream(FILE_PATH1);
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            existedSongs = (List<Song>) ois1.readObject();

            FileInputStream fis2 = new FileInputStream(FILE_PATH2);
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            songsOnDisk = (List<Song>) ois2.readObject();

            System.out.println("\nМузыка загружена!\n");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
