package servlet;

import model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "goods_delete", value = "/goods_delete")
public class GoodsDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order o = (Order) request.getSession().getAttribute(("order"));
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        o.delete(goodsid);
        response.getWriter().print("ok");
    }
}
