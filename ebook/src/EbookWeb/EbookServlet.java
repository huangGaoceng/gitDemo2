package EbookWeb;

import EbookService.EbookService;
import EbookService.impl.EbookServiceimpl;
import domain.Ebook;
import domain.Ebook_category;
import domain.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EbookServlet extends HttpServlet {
    EbookService service = new EbookServiceimpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        EbookServlet servlet = new EbookServlet();
        servlet.doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getParameter("method");
        EbookServlet servlet = new EbookServlet();
        switch (method) {
            case "showlist":
                servlet.showlist(request, response);
                break;
            case "save":
                servlet.save(request, response);
                break;
            case "amend":
                servlet.amend(request, response);
                break;
            case "delete":
                servlet.delete(request,response);
                request.getSession().removeAttribute("I");
                break;
            case "saveamend":
                servlet.saveamend(request,response);
                break;
        }

    }

    private void saveamend(HttpServletRequest request, HttpServletResponse response) {
        String categoryIdstr = request.getParameter("Typeid");
        int categoryId = Integer.parseInt(categoryIdstr);
        String idstr = request.getParameter("BookId");
        int id = Integer.parseInt(idstr);
        String title = request.getParameter("bookName");
        String summary = request.getParameter("bookZY");
        String uploaduser = request.getParameter("onPepole");
        String onTime = request.getParameter("onTime");
        Date createdate = null;
        try {
            createdate = new SimpleDateFormat("yyyy-MM-dd").parse(onTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
      int i = service.updateebook(id,title,summary,uploaduser,createdate);
        request.getSession().setAttribute("cid", categoryId);
        int pc = 1;
        String pcstr = request.getParameter("PC");
        if (pcstr != null && !pcstr.trim().isEmpty()) {
            pc = Integer.parseInt(pcstr);
        }
        int ps = 5;
        PageBean<Ebook> PB = service.getEbooks(categoryId, pc, ps);
        List<Ebook_category> list = service.getEbook_categorys();
        request.getSession().setAttribute("CATES", list);
        request.getSession().setAttribute("pb", PB);
        try {
            request.getRequestDispatcher("/jsp/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        String idstr = request.getParameter("ID");
        int id = Integer.parseInt(idstr);
        int i = service.deleteebook(id);
        request.getSession().setAttribute("I",i==1? "删除成功！":"删除失败");
        try {
            request.getRequestDispatcher("/").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void amend(HttpServletRequest request, HttpServletResponse response) {
        String idstr = request.getParameter("ID");
        int id = Integer.parseInt(idstr);
        Ebook ebook = service.getEbookByID(id);
        request.getSession().setAttribute("EB",ebook);
        try {
            request.getRequestDispatcher("/jsp/amend.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        String categoryIdstr = request.getParameter("Typeid");
        int categoryId = Integer.parseInt(categoryIdstr);
        String title = request.getParameter("bookName");
        String summary = request.getParameter("bookZY");
        String uploaduser = request.getParameter("onPepole");
        String onTime = request.getParameter("onTime");
        Date createdate = null;
        try {
            createdate = new SimpleDateFormat("yyyy-MM-dd").parse(onTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Ebook ebook = new Ebook(categoryId, title, summary, uploaduser, createdate);
        int i = service.addEbook(ebook);
        request.getSession().setAttribute("cid", categoryId);
        int pc = 1;
        String pcstr = request.getParameter("PC");
        if (pcstr != null && !pcstr.trim().isEmpty()) {
            pc = Integer.parseInt(pcstr);
        }
        int ps = 5;
        PageBean<Ebook> PB = service.getEbooks(categoryId, pc, ps);
        List<Ebook_category> list = service.getEbook_categorys();
        request.getSession().setAttribute("CATES", list);
        request.getSession().setAttribute("pb", PB);
        try {
            request.getRequestDispatcher("/jsp/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showlist(HttpServletRequest request, HttpServletResponse response) {
        int CategoryId = 0;
        String CategoryIdstr = request.getParameter("CategoryId");
        if (CategoryIdstr != null && !CategoryIdstr.trim().isEmpty()) {
            CategoryId = Integer.parseInt(CategoryIdstr);
        }
        request.getSession().setAttribute("cid", CategoryId);
        int pc = 1;
        String pcstr = request.getParameter("PC");
        if (pcstr != null && !pcstr.trim().isEmpty()) {
            pc = Integer.parseInt(pcstr);
        }
        int ps = 5;
        PageBean<Ebook> PB = service.getEbooks(CategoryId, pc, ps);
        List<Ebook_category> list = service.getEbook_categorys();
        request.getSession().setAttribute("CATES", list);
        request.getSession().setAttribute("pb", PB);
        try {
            request.getRequestDispatcher("/jsp/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
