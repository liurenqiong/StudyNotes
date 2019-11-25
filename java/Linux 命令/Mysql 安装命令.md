下载包 wget https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.22-linux-glibc2.12-x86_64.tar.gz
   解压  tar -xvf mysql-5.7.22-linux-glibc2.12-x86_64.tar.gz
   然后移动并改名 mv -v mysql-5.7.22-linux-glibc2.12-x86_64 /usr/java/mysql
   再创建用户和用户组
   groupadd mysql
   useradd -r -g mysql mysql
   将安装目录所有者及所属组改为mysql ，这个根据自己的目录来
   chown -R mysql.mysql /usr/java/mysql
   在mysql目录下创建data文件夹
   mkdir data
   初始化数据库
   /usr/java/mysql/bin/mysql_install_db --user=mysql --basedir=/usr/java/mysql/ --datadir=/usr/java/mysql/data --initialize
   如果报错了
   先执行
   yum -y install numactl
   yum search libaio
   yum install libaio
   在执行
   /usr/java/mysql/bin/mysqld --user=mysql --basedir=/usr/java/mysql/ --datadir=/usr/java/mysql/data --initialize
   编辑 /etc/my.cnf
    [mysqld]
    datadir=/var/lib/mysql
    socket=/var/lib/mysql/mysql.sock
    # Disabling symbolic-links is recommended to prevent assorted security risks
    symbolic-links=0
    # Settings user and group are ignored when systemd is used.
    # If you need to run mysqld under a different user or group,
    # customize your systemd unit file for mariadb according to the
    # instructions in http://fedoraproject.org/wiki/Systemd

    [mysqld_safe]
    log-error=/var/log/mariadb/mariadb.log
    pid-file=/var/run/mariadb/mariadb.pid

    #
    # include all files from the config directory
    #
    !includedir /etc/my.cnf.d

    将mysql加入到服务

    cp /usr/java/mysql/support-files/mysql.server /etc/init.d/mysql
    开机启动

    chkconfig mysql on
    启动mysql

    service mysql start

    mysql -u root -p
    或者 /usr/java/mysql/bin/mysql -uroot -p
    使用第二个命令是没有配置环境变量

    export PATH=$PATH:/usr/java/mysql/bin

    登录成功，再操作数据库设置密码

    use mysql;
    update user set authentication_string=password('你的密码') where user='root';
    或者#set password=password("root");
    flush privileges;
    exit
    将 /etc/my.cnf 中skip-grant-tables删除或注释掉

    如果操作不了数据库可以再次修改下密码，具体什么原因我也不清楚

    mysql -u root -p
    alter user 'root'@'localhost' identified by'修改后的密码';
    exit
    设置可以远程连接

    mysql -u root -p
    use mysql;
    update user set host='%' where user = 'root';
    flush privileges;
    exit


 1、使用root用户登录mysql

2、添加具有本地(localhost/127.0.0.1)访问权限的用户

#create user 'newuser'@'localhost' identified by 'password';
1
3、创建具有远程访问权限的用户

#create user 'snail'@'%' identified by 'snail';

创建之后记得执行下面指令更新权限：

#flush privileges; 



3、为新用户分配本地权限，可以指定数据库dbname和表名，可以用*替指所有。

#grant all privileges on `dbname`.* to 'newuser'@'localhost' identified by 'password';  

4、为新用户分配远程权限，可以指定数据库dbname和表名，可以用*替指所有。

MySQL5.x写法：

grant all privileges on `demo`.* to 'sanil'@'localhost' identified by 'snail';

grant all privileges on demo.* to 'snail'@'%' identified by 'sanil';
MySQL8.x写法：
grant all privileges on dbname.* to 'newuser'@'%';
分配好之后之后记得执行下面指令更新权限：

#flush privileges; 

5、如果还有问题，可以使用root账号登陆上去查询一下，再看看有没问题。

#use mysql

#select Host, User, Password from user;

查看权限
show grants for 'snail'@'localhost';


更新mysql.user表
mysql> use mysql;
# mysql5.7之前
mysql> update user set password=password('123456') where user='root';
# mysql5.7之后
mysql> update user set authentication_string=password('123456') where user='root';
mysql> flush privileges;

