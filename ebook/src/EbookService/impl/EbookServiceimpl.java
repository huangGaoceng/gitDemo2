package EbookService.impl;

import EbookDao.EbookDao;
import EbookDao.impl.EbookDaoimpl;
import EbookService.EbookService;
import domain.Ebook;
import domain.Ebook_category;
import domain.PageBean;

import java.util.Date;
import java.util.List;

public class EbookServiceimpl implements EbookService {
    EbookDao dao = new EbookDaoimpl();
    @Override
    public PageBean<Ebook> getEbooks(int categoryId, int pc, int ps) {
        return dao.queryEbooks(categoryId,pc,ps);
    }

    @Override
    public List<Ebook_category> getEbook_categorys() {
        return dao.getEbook_categorys();
    }

    @Override
    public int addEbook(Ebook ebook) {
        return dao.upDate(ebook);
    }

    @Override
    public Ebook getEbookByID(int id) {
        return dao.getEbookByID(id);
    }

    @Override
    public int updateebook(int id, String title, String summary, String uploaduser, Date createdate) {
        return dao.upDate2(id,title,summary,uploaduser,createdate);
    }

    @Override
    public int deleteebook(int id) {
        return dao.deleteebook(id);
    }
}
