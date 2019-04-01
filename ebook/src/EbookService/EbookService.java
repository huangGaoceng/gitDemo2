package EbookService;

import domain.Ebook;
import domain.Ebook_category;
import domain.PageBean;

import java.util.Date;
import java.util.List;

public interface EbookService {
    PageBean<Ebook> getEbooks(int categoryId, int pc, int ps);

    List<Ebook_category> getEbook_categorys();

    int addEbook(Ebook ebook);

    Ebook getEbookByID(int id);

    int updateebook(int id, String title, String summary, String uploaduser, Date createdate);

    int deleteebook(int id);
}
