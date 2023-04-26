public class Book {
    private String bookName;
    private int pagesNum;


    public Book(String newBookName, int newPagesNum) {
        this.bookName = newBookName;
        this.pagesNum = newPagesNum;

    }

    // Задать новое имя Книге
    public void setBookName(String newBookName){
        this.bookName = newBookName;
    }

    // Получить имеющееся название книги
    public String getBookName() {
        return bookName;
    }

    // Обновить кол-во старниц
    public void setPagesNum(int pagesNum) {
        this.pagesNum = pagesNum;
    }

    // Получить кол-во страниц
    public int getPagesNum() {
        return pagesNum;
    }
}

