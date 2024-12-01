package servlet;

import model.Browsing;
import model.Goods;
import model.User;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "goods_detail",urlPatterns = "/goods_detail")
public class GoodsDetailServlet extends HttpServlet {

    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goods g = gService.getGoodsById(id);
        if(request.getSession().getAttribute("user")!=null){
            Browsing b = new Browsing();
            User u = (User) request.getSession().getAttribute("user");
            b.setUsername(u.getName());
            b.setDatetime(new Timestamp(new Date().getTime()));
            b.setGoods_name(g.getName());
            b.setType(1);
            gService.addBrowsing(b);
        }
        request.setAttribute("g", g);
        request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
    }
}
