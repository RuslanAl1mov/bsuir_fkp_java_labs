import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Person> clients = new ArrayList<>();
    private static ArrayList<Person> blackList = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("\nМеню:");
        System.out.println("1) Создать книгу");
        System.out.println("2) Зарегестрировать пользователя");
        System.out.println("3) Добавить книгу клиенту");
        System.out.println("4) Добавить в черный список");
        System.out.println("5) Черный список");


        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Выбери: ");
        String choose = consoleChoose.nextLine();

        if (Objects.equals(choose, "1")) {
            createBook();
        } else if (Objects.equals(choose, "2")) {
            newPersonRegistration();
        } else if (Objects.equals(choose, "3")) {
            addBookToClient();
        } else if (Objects.equals(choose, "4")) {
            addPersonToBlackList();
        } else if (Objects.equals(choose, "5")) {
            showBlackList();
        }

    }

    public static void createBook() {
        int intPagesNum = 0;

        System.out.println("\nДобавление книги в каталог:");
        Scanner consoleChoose = new Scanner(System.in);

        System.out.print("Название Книги: ");
        String newBookName = consoleChoose.nextLine();  // Вводим название книги

        System.out.print("Кол-во страниц в книге: ");
        String pagesNum = consoleChoose.nextLine();
        try {
            intPagesNum = Integer.parseInt(pagesNum);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }


        Book newBook = new Book(newBookName, intPagesNum);
        books.add(newBook);
        System.out.println("Добавлна новая книга: " + newBookName + " (" + intPagesNum + " стр.)\n");
        mainMenu();

    }


    public static void newPersonRegistration() {
        String readingPlace = "С собой";

        System.out.println("\nВыбери прибор для отключения:");

        Scanner consoleChoose = new Scanner(System.in);
        System.out.print("Имя пользователя: ");
        String newPersonName = consoleChoose.nextLine();
        System.out.print("Фамилия пользователя: ");
        String newPersonSecName = consoleChoose.nextLine();
        System.out.print("Адрес пользователя: ");
        String newPersonAddress = consoleChoose.nextLine();

        System.out.println("Выберите место чтения:\n1) С собой\n2) В читальном зале");
        String choose = consoleChoose.nextLine();
        if (Objects.equals(choose, "1")){
            readingPlace = "С собой";
        }

        else if (Objects.equals(choose, "2")){
            readingPlace = "В читально зале";
        }

        Person newPerson = new Person(newPersonName, newPersonSecName, newPersonAddress, readingPlace);
        clients.add(newPerson);
        mainMenu();

    }

    private static Person findPerson(){
        int numeric = 0;
        int intChoose = 0;

        System.out.println("\nСписок клиентов:");

        for (Person person: clients){
            numeric ++;
            System.out.println(numeric + ") " + person.getFirstName() + " " + person.getSecondName() );
        }

        System.out.print("\nВыберите:");
        Scanner choose = new Scanner(System.in);
        String personNum = choose.nextLine();  // Выбор человека
        try {
            intChoose = Integer.parseInt(personNum);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        return clients.get(intChoose-1);
    }


    private static Book findBook(){
        int numeric = 0;
        int intChoose = 0;

        System.out.println("\nСписок книг:");

        for (Book book: books){
            numeric ++;
            System.out.println(numeric + ") " + book.getBookName() + " - " + book.getPagesNum() + " стр.");
        }

        System.out.print("\nВыберите:");
        Scanner choose = new Scanner(System.in);
        String personNum = choose.nextLine();  // Выбор человека
        try {
            intChoose = Integer.parseInt(personNum);  // Преобразование в целое число
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        return books.get(intChoose-1);
    }


    private static void addBookToClient() {
        System.out.println("\nДобавить книгу Посетителю:");
        Person client = findPerson();
        Book book = findBook();

        client.addBookToPerson(book);
        System.out.println("Книга Добавлена!");
        mainMenu();
    }


    public static void addPersonToBlackList() {
        Person selected_person = findPerson();
        clients.removeIf(person -> person == selected_person);
        blackList.add(selected_person);
        System.out.println("Клиент добавлен в черный список");
        mainMenu();
    }

    public static void showBlackList(){
        int numeric = 0;
        for (Person person: blackList){
            numeric++;
            System.out.println(numeric + ") " + person.getFirstName() + " " + person.getSecondName() );

        }
        mainMenu();
    }
}
