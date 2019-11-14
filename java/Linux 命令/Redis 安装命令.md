一、下载redis
redis官网地址：http://www.redis.io/

下载地址：http://download.redis.io/releases/

redis中文文档地址：http://www.redis.cn/documentation.html

wget http://download.redis.io/releases/redis-4.0.11.tar.gz

二、安装

解压  tar -xvf redis-4.0.11.tar.gz

yum安装gcc依赖 yum install gcc

进入安装目录 编译 make MALLOC=libc

将/usr/java/redis-4.0.11/src目录下的文件加到/usr/local/bin目录

cd src && make install



三、启动

先切换到redis src目录下

 cd src
 
1、直接启动redis
./redis-server

2、以后台进程方式启动redis

第一步：修改redis.conf文件

将

daemonize no
 

修改为

daemonize yes

注释 

#bind 127.0.0.1

通过/requirepass foobared搜索到后将其注释打开，同时将foobared修改为你要给redis设置的密码
设置密码 123456



第二步：指定redis.conf文件启动

./redis-server /usr/java/redis-4.0.11/redis.conf

第三步：关闭redis进程

首先使用ps -aux | grep redis查看redis进程

ps -aux | grep redis

使用kill命令杀死进程

 kill 18714

 3、设置redis开机自启动

 1、在/etc目录下新建redis目录

mkdir redis

2、将/usr/java/redis-4.0.11/redis.conf 文件复制一份到/etc/redis目录下，并命名为6379.conf　　

cp /usr/java/redis-4.0.11/redis.conf /etc/redis/6379.conf

3、将redis的启动脚本复制一份放到/etc/init.d目录下

cp /usr/java/redis-4.0.11/utils/redis_init_script /etc/init.d/redisd

4、设置redis开机自启动

4、设置redis开机自启动

先切换到/etc/init.d目录下

然后执行自启命令

[root@iZwz991stxdwj560bfmadtZ init.d]# chkconfig redisd on
service redisd does not support chkconfig　
 

看结果是redisd不支持chkconfig

解决方法：

使用vim编辑redisd文件，在第一行加入如下两行注释，保存退出

# chkconfig:   2345 90 10
# description:  Redis is a persistent key-value database
 

注释的意思是，redis服务必须在运行级2，3，4，5下被启动或关闭，启动的优先级是90，关闭的优先级是10。



 
再次执行开机自启命令，成功

[root@iZwz991stxdwj560bfmadtZ init.d]# chkconfig redisd on
　

现在可以直接已服务的形式启动和关闭redis了

启动：

service redisd start　

[root@izwz991stxdwj560bfmadtz ~]# service redisd start
Starting Redis server...
2288:C 13 Dec 13:51:38.087 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
2288:C 13 Dec 13:51:38.087 # Redis version=4.0.6, bits=64, commit=00000000, modified=0, pid=2288, just started
2288:C 13 Dec 13:51:38.087 # Configuration loaded
 

关闭：

方法1：service redisd stop

[root@izwz991stxdwj560bfmadtz ~]# service redisd stop
Stopping ...
Redis stopped
 

方法2：redis-cli SHUTDOWN



参考 https://www.cnblogs.com/zuidongfeng/p/8032505.html
