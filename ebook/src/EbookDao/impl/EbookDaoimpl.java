package EbookDao.impl;

import EbookDao.EbookDao;
import Utils.JDBCUtils;
import domain.Ebook;
import domain.Ebook_category;
import domain.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EbookDaoimpl implements EbookDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDateSource());
    @Override
    public PageBean<Ebook> queryEbooks(int categoryId,int pc,int ps) {
        StringBuilder sql = new StringBuilder("SELECT ebook_entry.id, categoryId,title,summary,uploaduser,createdate ,`name` FROM ebook_entry LEFT OUTER JOIN ebook_category ON ebook_entry.categoryId=ebook_category.id WHERE 1=1 ");
        StringBuilder sql2 = new StringBuilder("SELECT COUNT(*) FROM ebook_entry LEFT OUTER JOIN ebook_category ON ebook_entry.categoryId=ebook_category.id WHERE 1=1 ");
        List parms = new ArrayList();
        if (categoryId!=0){
            sql.append("and categoryId=? ");
            sql2.append("and categoryId=? ");
            parms.add(categoryId);
        }
        int tr = 0;
        try {
          Number  tr1 = (Number)qr.query(sql2.toString(),new ScalarHandler(),parms.toArray());
          tr = tr1.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql.append("ORDER BY categoryId DESC,ebook_entry.id ASC limit ?,? ");
        parms.add((pc-1)*ps);
        parms.add(ps);
        List<Ebook> list = null;
        try {
           list = qr.query(sql.toString(), new BeanListHandler<Ebook>(Ebook.class), parms.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PageBean PB = new PageBean();
        PB.setTr(tr);
        PB.setPs(ps);
        PB.setPc(pc);
        PB.setList(list);
        return PB;

    }

    @Override
    public List<Ebook_category> getEbook_categorys() {
        String sql = "SELECT id,NAME FROM ebook_category ";
        try {
            return qr.query(sql, new BeanListHandler<Ebook_category>(Ebook_category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int upDate(Ebook ebook) {
        String sql = "INSERT INTO ebook_entry (categoryId, title, summary, uploaduser, createdate) VALUES (?,?,?,?,?)";
        try {
            return qr.update(sql,ebook.getCategoryId(),ebook.getTitle(),ebook.getSummary(),ebook.getUploaduser(),ebook.getCreatedate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ebook getEbookByID(int id) {
        String sql = "SELECT  categoryId, title, summary, uploaduser, createdate FROM ebook_entry WHERE id=? ";
        try {
            return  qr.query(sql,new BeanHandler<Ebook>(Ebook.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int upDate2(int id, String title, String summary, String uploaduser, Date createdate) {
        String sql ="UPDATE ebook_entry SET title = ? , summary = ? , uploaduser = ? ,createdate =? WHERE id = ? ";
        try {
            return qr.update(sql,title,summary,uploaduser,createdate,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteebook(int id) {
        String sql = "DELETE FROM ebook_entry WHERE id = ? ";
        try {
            return qr.update(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
