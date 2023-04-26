import java.util.ArrayList;

public class Person {

    private String firstName;  // Имя посетителя
    private String secondName;  // Фамилия посетителя
    private String address;  // Адрес посетителя
    private String readingPlace; // Место чтения книги (с собой или в зале)
    private static ArrayList<Book> personBooks = new ArrayList<>();


    public Person(String firstName, String secondName, String address, String readingPlace) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.readingPlace = readingPlace;
    }

    // Обновить имя Посетителя
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Получить имя Посетителя
    public String getFirstName() {
        return firstName;
    }

    // Обновить фамилию Посетителя
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    // Получить фамилию Посетителя
    public String getSecondName() {
        return secondName;
    }

    // Обновить адрес Посетителя
    public void setAddress(String address) {
        this.address = address;
    }

    // Получить имя Посетителя
    public String getAddress() {
        return address;
    }

    // Обновить читальное место
    public void setReadingPlace(String readingPlace) {
        this.readingPlace = readingPlace;
    }

    // Получить читальное место
    public String getReadingPlace() {
        return readingPlace;
    }

    // Добавляем книгу в список посетителя
    public void addBookToPerson(Book newBook){
        personBooks.add(newBook);
    }

    // Возвращае список книг данного Посетителя
    public static ArrayList<Book> getPersonBooks() {
        return personBooks;
    }
}

