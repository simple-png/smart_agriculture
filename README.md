# smart_agriculture
## 部署方式(CentOS)
### 环境:
maven 3.6.1

jdk 1.8

database mysql-8

redis 3.2.12

### 修改事项
修改/smart_agriculture/src/main/resources/application.yml
内的port和mysql,redis的password和username

打开/smart_agriculture/src/main/resources/static输入pwd获取绝对路径/home/admin/smart_agriculture/src/main/resources/static/getNews.py

修改/smart_agriculture/src/main/java/com/agriculture/task/getNewsTask.java里的内容
String pythonFilePath = "/home/admin/smart_agriculture/src/main/resources/static/getNews.py";
内为之前获取的绝对路径

### 插入数据
打开项目所在目录
mysql -u 用户名 -p
密码
source smart_agriculture.sql

### 修改数据
数据库默认用户名为user密码为123456
管理员为admin密码为123456

