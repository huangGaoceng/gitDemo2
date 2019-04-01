package EbookDao;

import domain.Ebook;
import domain.Ebook_category;
import domain.PageBean;

import java.util.Date;
import java.util.List;

public interface EbookDao {
    PageBean<Ebook> queryEbooks(int categoryId, int pc, int ps);

    List<Ebook_category> getEbook_categorys();


    int upDate(Ebook ebook);

    Ebook getEbookByID(int id);

    int upDate2(int id, String title, String summary, String uploaduser, Date createdate);

    int deleteebook(int id);
}
