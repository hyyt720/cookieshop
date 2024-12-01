package dao;


import model.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public void insertOrder(Connection con, Order order) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) values(?,?,?,?,?,?,?,?,?)";
        r.update(con,sql,
                order.getTotal(),order.getAmount(),order.getStatus(),
                order.getPaytype(),order.getName(),order.getPhone(),
                order.getAddress(),order.getDatetime(),order.getUser().getId() );
    }
    public int getLastInsertId(Connection con) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql = "select last_insert_id()";
        BigInteger bi = r.query(con, sql,new ScalarHandler<BigInteger>());
        return Integer.parseInt(bi.toString());
    }
    public void insertOrderItem(Connection con, OrderItem item) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql ="insert into orderitem(price,amount,goods_id,order_id) values(?,?,?,?)";
        r.update(con,sql,item.getPrice(),item.getAmount(),item.getGoods().getId(),item.getOrder().getId());
    }
    public List<Order> selectAll(int userid) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from `order` where user_id=? order by datetime desc";
        return r.query(sql, new BeanListHandler<Order>(Order.class),userid);
    }
    public List<OrderItem> selectAllItem(int orderid) throws SQLException{
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id=? and i.goods_id=g.id";
        return r.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),orderid);
    }
    public int getOrderCount(int status) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "";
        if(status==0) {
            sql = "select count(*) from `order`";
            return r.query(sql, new ScalarHandler<Long>()).intValue();
        }else {
            sql = "select count(*) from `order` where status=?";
            return r.query(sql, new ScalarHandler<Long>(),status).intValue();
        }
    }
    public List<Order> selectOrderList(int status, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        if(status==0) {
            String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<Order>(Order.class), (pageNumber-1)*pageSize,pageSize );
        }else {
            String sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and o.status=? order by o.datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<Order>(Order.class),status, (pageNumber-1)*pageSize,pageSize );
        }
    }
    public void updateStatus(int id,int status) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="update `order` set status=? where id = ?";
        r.update(sql,status,id);
    }
    public void deleteOrder(Connection con ,int id) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql ="delete from `order` where id = ?";
        r.update(con,sql,id);
    }
    public void deleteOrderItem(Connection con ,int id) throws SQLException {
        QueryRunner r = new QueryRunner();
        String sql ="delete from orderitem where order_id=?";
        r.update(con,sql,id);
    }

    public String getEmail(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select u.email from `order` o, user u where o.id = ? and u.id=o.user_id";
        String email = (String) r.query(sql, new ScalarHandler<>(), id);
        return email;
    }

    public int getAmount(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select amount from `order` where id = ?";
        Integer amount = r.query(sql, new ScalarHandler<Integer>(), id);
        return amount;
    }

    public Integer getGoodsId(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select goods_id from orderitem where id = ?";
        return r.query(sql,new ScalarHandler<>(),id);
    }

    public Integer getGoodsIdByOrderId(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select goods_id from orderitem where order_id = ?";
        return r.query(sql,new ScalarHandler<>(),id);
    }

    public boolean isNameExist(String name) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from ordercount where name = ?";
        OrderCount oc = r.query(sql,new BeanHandler<OrderCount>(OrderCount.class),name);
        if(oc == null) return false;
        else return true;
    }

    public OrderCount getOrderCount(String name) throws  SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from ordercount where name = ?";
        return r.query(sql,new BeanHandler<OrderCount>(OrderCount.class),name);
    }

    public void insertOrderCount(OrderCount oc) throws SQLException{
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="insert into ordercount(name,amount,total) values (?,?,?)";
        r.update(sql,oc.getName(),oc.getAmount(),oc.getTotal());
    }

    public void addOrderCount(int id,float total,int amount) throws SQLException{
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="update ordercount set total = ?, amount = ? where id =?";
        r.update(sql,total,amount,id);
    }

    public float getTotal(int id) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="select total from `order` where id = ?";
        return r.query(sql,new ScalarHandler<>(),id);
    }

    public List selectOrderCountList(int pageNo, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from ordercount limit ?,?";
        return r.query(sql, new BeanListHandler<OrderCount>(OrderCount.class), (pageNo-1)*pageSize,pageSize );
    }

    public int getOrderCountNum() throws SQLException{
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from ordercount";
        return r.query(sql, new ScalarHandler<Long>()).intValue();
    }

    public int getBrowsingNum(int type) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "";
        if(type==0) {
            sql = "select count(*) from browsing";
            return r.query(sql, new ScalarHandler<Long>()).intValue();
        }else {
            sql = "select count(*) from browsing where type=?";
            return r.query(sql, new ScalarHandler<Long>(),type).intValue();
        }
    }
    public List selectBrowsingList(int type, int pageNumber, int pageSize) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        if(type==0) {
            String sql = "select * from browsing order by datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<Browsing>(Browsing.class), (pageNumber-1)*pageSize,pageSize );
        }else {
            String sql = "select * from browsing where type = ? order by datetime desc limit ?,?";
            return r.query(sql, new BeanListHandler<Browsing>(Browsing.class),type, (pageNumber-1)*pageSize,pageSize );
        }
    }
    public void deleteBrowsing(int id) throws SQLException{
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from browsing where id = ?";
        r.update(sql,id);
    }
}
