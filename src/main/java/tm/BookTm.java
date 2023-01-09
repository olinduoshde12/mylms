package tm;

public class BookTm {
    private String id;
    private String tittle;
    private String author;
    private String isbn;

    public BookTm() {
    }

    public BookTm(String id, String tittle, String author, String isbn) {
        this.id = id;
        this.tittle = tittle;
        this.author = author;
        this.isbn = isbn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
