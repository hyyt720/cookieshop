# 蛋糕商城
## 个人信息
黄昊宇 学号：202230441190
## 项目介绍
蛋糕商城是一个基于Java和JavaScript开发的在线购物平台。本项目旨在提供一个完整的电商平台解决方案，包括用户管理、商品管理、购物车管理、订单管理以及邮件通知等功能。通过前后端的紧密协作，为用户提供了流畅的购物体验。

## 功能概述
### 前台功能
#### 用户注册与登录：
用户可以通过注册页面填写基本信息进行注册。
注册成功后，用户可以使用用户名和密码进行登录。
当名字，邮箱，手机号被使用过时，将无法注册。
##### 前端代码
位于user_login.jsp，user_register.jsp中，将接收到的数据传入servlet包中的UserLoginServlet.java和UserRegisterServlet.java中。
##### 后端代码
位于UserLoginServlet.java和UserRegisterServlet.java中，调用UserService类中的函数，UserService类调用UserDao对数据库中的数据进行查找，修改，当注册成功转到登录界面，登陆成功转到个人中心页面。
#### 用户属性修改与登出：
登录后的用户可以在个人中心修改自己的个人信息，如姓名、邮箱、密码等。
##### 前端代码
位于user_center.jsp中，如果要进行修改功能，则根据修改的是地址还是密码，将接收到的数据分别传入servlet包中的UserChangeAddressServlet.java和UserChangePwdServlet.java中；如果要进行登出功能，则转入UserLogoutServlet中。
##### 后端代码
分别位于UserChangeAddressServlet.java，UserChangePwdServlet.java和UserLogoutServlet.java中，调用UserService类中的函数，UserService类调用UserDao对数据库中的数据进行查找，修改。
#### 商品浏览：
用户可以在首页或商品分类页面浏览各类蛋糕商品。
商品分为热销，条幅，新品等状态，客户可以在对应页面下查看
每个商品页面都提供了详细的商品信息和图片。
##### 前端代码
位于goods_list.jsp和goods_detail.jsp中，在goods_list.jsp中，页面根据当前的类别与页数输出对应的商品，当点击商品详情时，跳转到GoodsDetailServlet.java中，该servlet类会根据传递的goods_id从数据库中取出id=goods_id的商品，然后转入goods_detail.jsp，将数据输出。
##### 后端代码
分别位于GoodsDetailServlet.java，GoodsListServlet.java中，调用GoodsService类中的函数，GoodsService类调用GoodsDao对数据库中的数据进行查找。GoodsListServlet.java根据转入的页信息和类别信息筛选展示的商品，当点击对应商品的商品详情时，转入GoodsDetailServlet.java,在其中根据商品id查找对应数据，将查找后的数据转入goods_detail.jsp中。
#### 购物车管理：
用户可以将心仪的商品加入购物车，并在购物车页面查看和管理商品。
购物车支持修改商品数量、删除商品等操作。
##### 前端代码
位于goods_cart.jsp中，在goods_cart.jsp中，页面根据传入的orderItem类数据，进行输出
##### 后端代码
分别位于GoodsBuyServlet.java中，GoodsDeleteServlet中，调用GoodsService类中的函数，GoodsService类调用GoodsDao对数据库中的数据进行查找与修改。GoodsBuyServlet.java将对应的商品数据输入其中，GoodsDeleteServlet.java则是根据商品id订单中的商品数量减少或直接将其删除，在进行修改后，返回goods_cart.jsp中。
#### 支付功能：
用户可以在购物车页面选择支付方式进行支付。
支付成功后，订单状态会更新为已支付。
##### 前端代码
位于order_submit.jsp中，在order_submit.jsp中，可以选择支付方式
##### 后端代码
分别位于OrderSubmitServlet.java和OrderConfirmServlet.java中，调用OrderService类中的函数，OrderService类调用OrderDao对数据库中的数据进行查找与修改。OrderSubmitServlet.java对会话进行查询，确认用户是否登录，如未登录则转入登录环节，若登录则转入OrderConfirmServlet.java。
### 后台功能
#### 客户数据管理：
后台管理员可以修改客户的基本信息，如联系方式、地址等。
##### 前端代码
分别位于/admin/user_edit.jsp，/admin/user_add.jsp，/admin/user_list.jsp，/admin/user_delete.jsp中，将接收到的数据传入servlet包中的AdminUserEditServlet.java，AdminUserAddServlet.java，AdminUserListServlet.java，AdminUserDeleteServlet.java中。
##### 后端代码
分别位于AdminUserEditServlet.java，AdminUserAddServlet.java，AdminUserListServlet.java，AdminUserDeleteServlet.java中，调用UserService类中的函数，UserService类调用UserDao对数据库中的数据进行查找，修改。
#### 商品与商品类别管理：
后台管理员可以增加、修改和删除商品及商品类别。
商品信息包括名称、价格、库存、描述、图片等。
##### 前端代码
分别位于/admin/goods_edit.jsp，/admin/goods_add.jsp，/admin/user_list.jsp中，根据点击的按键将接收到的数据传入servlet包中的AdminGoodsEditServlet.java，AdminGoodsAddServlet.java，AdminGoodsListServlet.java，AdminGoodsDeleteServlet.java和AdminGoodsEditShowServlet.java中。
##### 后端代码
分别位于AdminGoodsEditServlet.java，AdminGoodsAddServlet.java，AdminGoodsListServlet.java，AdminGoodsDeleteServlet.java和AdminGoodsEditShowServlet.java中，调用GoodsService类中的函数，GoodsService类调用GoodsDao对数据库中的数据进行查找，修改。
#### 订单管理：
后台管理员可以查看、修改和删除订单。
订单状态包括待支付、已支付、已发货、已完成等。
##### 前端代码
分别位于/admin/order_list.jsp中，将订单分为三类，即已付款，已发货和已完成，根据点击的按键将接收到的数据传入servlet包中的AdminOrderDeleteServlet.java，AdminOrderStatusServlet.java，AdminOrderListServlet.java中。
##### 后端代码
分别位于AdminOrderDeleteServlet.java，AdminOrderStatusServlet.java，AdminOrderListServlet.java中，AdminOrderListServlet.java用于展现当前状态（即全部或付款，发货，完成三种状态）下当前页码的订单，AdminOrderDeleteServlet.java用于删除订单，AdminOrderStatusServlet.java用于修改订单状态
#### 邮件通知功能：
当订单状态更新为已发货时，系统会自动发送邮件通知客户。
##### 后端代码
分别位于AminOrderStatusServlet.java中，运用SMTP邮件传输技术实现，当状态转换为发货时，通过UserService取出对应客户的邮箱地址，将邮件发送出去。
#### 数据分析功能：
后台管理员可以查看总营业额。
管理员还可以查看用户浏览和购买记录，以便进行数据分析。
#### 杂项
##### 前端代码
header.jsp主要存放对应页面的头部，在其中可以实现页面的切换，index.jsp则是实现了页面的主体部分，footer.jsp则是实现了页面的底部，page.jsp则是实现了列表类型的页面管理。
##### 后端代码
Dao包主要用于与数据库连接，查找或更新数据库的数据；Service包则是作为Dao包与Servlet包的桥梁，调用Dao层为Servlet提供服务；Servlet包用于存放实现某种业务逻辑的Servlet类；Listener包用于侦听所有商品分类；Model包用于存放实体类；filter包用于确认用户权限，同时统一全站编码，utils包存放工具类。
## 技术栈
前端：JavaScript
后端：Java
数据库：MySQL
邮件服务：SMTP
## 使用说明
### 环境准备：
安装Java和JavaScript的开发环境。
配置数据库并导入项目所需的数据库表结构。
数据库对应八个表，分别为user表（用户表），goods表（商品表），order表（订单表），orderitem表（订单项表），ordercount表（订单总额表），browsing表（浏览记录表），recommend表（推荐表），type表（商品标签表）。
#### user表
user表中有id(int AI PK)，username(varchar(45))，password(varchar(45))，name(varchar(45))，email(varchar(45))，phone(varchar(45))，address(varchar(45))，isadmin(bit(1))，isvalidate(bit(1))。
#### goods表
goods表中有id(int AI PK)，name(varchar(45))，cover(varchar(45))，image1(varchar(45))，image2(varchar(45))，price(float)，intro(varchar(300))，stock(int)，type_id(id)。
#### order表
order表中有id(int AI PK)，total(float)，amount(int)，user_id(int)，status(tinyint(1))，paytype(tinyint(1))，name(varchar(45))，phone(varchar(45))，address(varchar(45))，datetime(datetime)。
#### orderitem表
orderitem表中有id(int AI PK)，price(float)，amount(int)，goods_id(int),order_id(int)。
#### ordercount表
ordercount表中有id(int AI PK)，name(varchar(45))，amount(int)，total(float)。
#### browsing表
browsing表中有id(int AI PK)，username(varchar(45))，goods_name(varchar(45))，datetime(datetime)，type(int)。
#### recommend表
recommend表中有id(int AI PK)，type(tinyint(1))，goods_id(int)。
#### type表
type表中有id(int AI PK)，name(varchar(45))。
### 项目启动：
在后端项目中配置数据库连接信息和其他必要的配置。
启动后端项目，确保后台服务正常运行。
在前端项目中配置后端服务的API地址。
启动前端项目，进行开发和测试。
### 功能测试：
使用不同的用户角色（如普通用户、后台管理员）进行功能测试。
确保所有功能都按预期工作，并处理可能出现的错误和异常情况。
## 注意事项
在开发和测试过程中，请确保数据库中的数据安全和备份。
如果遇到任何问题或bug，请查阅项目的日志文件或联系项目负责人。
未经授权，请勿将本项目用于商业用途或进行二次开发。