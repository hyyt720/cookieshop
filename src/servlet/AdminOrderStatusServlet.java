package servlet;

import model.OrderCount;
import service.GoodsService;
import service.OrderService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "admin_order_status", value = "/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    private GoodsService gService = new GoodsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        if(status == 3) {

            int goods_id = oService.getGoodsId(id);
            int amount = oService.getAmount(id);
            int stock = gService.getStock(goods_id);
            if(stock<amount) {
                status = 2;
                oService.updateStatus(id,status);
                response.sendRedirect("/admin/order_list?status="+status);
            }
            else {
                stock -= amount;
                gService.setStock(goods_id, stock);

                String email =  oService.getEmail(id);;
                String subject = "订单发货！";
                String message = "您的订单"+id+"已发货";
                if(email != null) {
                    sendEmail1(email, subject, message);
                }
            }
        }
        if(status == 4) {
            OrderCount oc = new OrderCount();
            int goods_id = oService.getGoodsId(id);
            oc.setAmount(oService.getAmount(id));
            oc.setTotal(oService.getTotal(id));
            oc.setName(gService.getName(goods_id));
            System.out.println(oc.toString());
            oService.addOrderCount(oc);
        }
        oService.updateStatus(id,status);
        response.sendRedirect("/admin/order_list?status="+status);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void sendEmail1(String to, String subject, String messageText) {
        // SMTP 配置
        String host = "smtp.qq.com"; // SMTP 服务器地址
        final String username = "3295675539@qq.com"; // 发送者邮箱
        final String password = "qunzaglvdjvdciif"; // 发送者邮箱密码

        // 配置邮件会话属性
        Properties properties = new Properties();


        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        // 创建邮件会话
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建邮件对象
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // 发送者邮箱
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // 收件人邮箱
            message.setSubject(subject); // 邮件主题
            message.setText(messageText); // 邮件内容

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功");

        } catch (MessagingException e) {
            e.printStackTrace(); // 打印错误信息
        }
    }

}


