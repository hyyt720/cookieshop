package servlet;

import model.Page;
import model.Type;
import service.GoodsService;
import service.TypeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "goods_list", value = "/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService=new GoodsService();
    private TypeService tService=new TypeService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        if(request.getParameter("typeid")!=null)
        {
            id=Integer.parseInt(request.getParameter("typeid"));
        }
        int pageNumber=1;
        if(request.getParameter("pageNumber")!=null) {
            try {
                pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch (Exception e)
            {

            }

        }
        Type t=null;
        if(id!=0)
        {
            t=tService.selectTypeNameByID(id);
        }
        request.setAttribute("t",t);
        //List<Goods> list=gService.selectGoodsByTypeID(id,1,8);
        //request.setAttribute("goodsList",list);
        if(pageNumber<=0)
            pageNumber=1;
        Page p=gService.selectPageByTypeID(id,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p=gService.selectPageByTypeID(id,p.getTotalPage());
            }
        }

        request.setAttribute("p",p);
        request.setAttribute("id",String.valueOf(id));
        request.getRequestDispatcher("/goods_list.jsp").forward(request,response);
    }
}
