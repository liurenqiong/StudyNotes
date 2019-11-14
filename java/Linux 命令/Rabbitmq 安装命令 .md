参考地址

https://www.cnblogs.com/wintercloud/p/10877234.html

安装依赖文件

yum -y install gcc glibc-devel make ncurses-devel openssl-devel xmlto perl wge


安装erlang语言环境：

wget http://www.erlang.org/download/otp_src_20.3.tar.gz #下载erlang包
tar -xvf otp_src_20.3.tar.gz  #解压
cd otp_src_20.3/ #切换到安装路径
./configure --prefix=/usr/local/erlang  #生产安装配置
make && make install  #编译安装
配置erlang环境变量

vi /etc/profile  #在底部添加以下内容
#set erlang environment
ERL_HOME=/usr/local/erlang
PATH=$ERL_HOME/bin:$PATH
export ERL_HOME PATH
source /etc/profile #使以上配置生效
测试一下是否安装成功,在控制台输入命令erl

erl  #如果进入erlang的shell则证明安装成功，退出即可


安装RabbitMQ

cd /usr/local  #切换到计划安装RabbitMQ的目录，我这里放在/usr/local
wget https://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.4/rabbitmq-server-generic-unix-3.7.4.tar.xz
# wget http://www.rabbitmq.com/releases/rabbitmq-server/v3.7.4/rabbitmq-server-generic-unix-3.7.14.tar.xz （此下载源失效）
xz -d rabbitmq-server-generic-unix-3.7.4.tar.xz
tar -xvf rabbitmq-server-generic-unix-3.7.4.tar
mv rabbitmq_server-3.7.4/ rabbitmq
配置rabbitmq环境变量：

vi /etc/profile
#set rabbitmq environment
export PATH=$PATH:/usr/local/rabbitmq/sbin
保存退出

source /etc/profile
启动服务

rabbitmq-server -detached //启动rabbitmq，-detached代表后台守护进程方式启动。
如果启动失败，可尝试

nohup rabbitmq-server restart >rabbitmqLOG.log 2>&1 &
（停止rabbitmq：rabbitmqctl stop）
查看状态，如果显示如下截图说明安装成功：

rabbitmqctl status


mkdir /etc/rabbitmq
然后启用插件：

rabbitmq-plugins enable rabbitmq_management
配置防火墙：

配置linux 端口 15672 网页管理 5672 AMQP端口：（阿里云服务器只需要将15672、5672端口加入安全组即可，无需以下3步操作）

firewall-cmd --permanent --add-port=15672/tcp
firewall-cmd --permanent --add-port=5672/tcp
systemctl restart firewalld.service

现在你在浏览器中输入服务器IP:15672 就可以看到RabbitMQ的WEB管理页面了，是不是很兴奋，可是你没有账号密码，别急

配置访问账号密码的和权限
默认网页是不允许访问的，需要增加一个用户修改一下权限，代码如下：

rabbitmqctl add_user admin admin  #添加用户，后面两个参数分别是用户名和密码，我这都用super了。
rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"  #添加权限
rabbitmqctl set_user_tags admin administrator #修改用户角色


安装延时任务插件（非必须）

linux安装rabbitmq_delayed_message_exchange插件（可通过该插件的x-delay-message实现延时消息队列）

检查本地是否安装rabbitmq_delayed_message_exchange

rabbitmq-plugins list
wget wget https://dl.bintray.com/rabbitmq/community-plugins/3.7.x/rabbitmq_delayed_message_exchange/rabbitmq_delayed_message_exchange-20171201-3.7.x.zip
unzip，解压到/usr/local/rabbitmq/plugins/rabbitmq_delayed_message_exchange-20171201-3.7.x.ez

如果提示找不到命令，也可通过https://dl.bintray.com/rabbitmq/community-plugins/3.7.x/rabbitmq_delayed_message_exchange/下载压缩文件到本地，解压后在将rabbitmq_delayed_message_exchange-20171201-3.7.x.ez上传到/usr/local/rabbitmq/plugins/目录下

安装插件

rabbitmq-plugins enable rabbitmq_delayed_message_exchange
出现started 1 plugins.代表成功