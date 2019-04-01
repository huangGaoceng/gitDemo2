package domain;

public class Ebook_category {
    private int id;
    private String name;

    public Ebook_category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ebook_category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
