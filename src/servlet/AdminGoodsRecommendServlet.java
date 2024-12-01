package servlet;

import service.GoodsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "admin_goods_recommend", value = "/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget=Integer.parseInt(request.getParameter("typeTarget"));
        if(method.equals("add")) {
            gService.addRecommend(id, typeTarget);
        }else {
            gService.removeRecommend(id, typeTarget);
        }
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
