打包 
install compile package

上传到私服
deploy -e

指定配置文件
--spring.profiles.active=local

打包跳过测试且设置打包环境
 clean install -DskipTests -U -Pdev# StudyNotes
学习笔记


https://www.cnblogs.com/fan-gx/p/11371984.html


1、要使用nexus服务需要安装jdk和maven
1.1、jdk下载地址：https://www.oracle.com/technetwork/java/javase/downloads/index.html

rpm -ivh jdk-8u221-linux-x64.rpm

vim /etc/profile
export JAVA_HOME=/usr/java/jdk1.8.0_221-amd64
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar 

source /etc/profile

java -version
1.2、maven下载地址：https://maven.apache.org/download.cgi

tar -zxvf apache-maven-3.6.1-bin.tar.gz

vim /etc/profile
export PATH=$PATH:/usr/local/maven/bin

source /etc/profile

mvn -v
2、安装nexus
下载地址：https://www.sonatype.com/nexus-repository-oss，点击首页大广告图，跳转后填写邮箱，点击，download，网页跳转后，选择 nexus repository manager oss 3.x - unix，下载最新安装包。

或者打开网址：https://my.sonatype.com/ 在 Latest Releases 标签下， 下载最新nexus repository安装包

wget https://download.sonatype.com/nexus/3/latest-unix.tar.gz

tar -zxvf nexus-3.18.1-01-unix.tar.gz
#解压后又2个目录
    #nexus-3.18.1-01：包含了 Nexus 运行所需要的文件。是 Nexus 运行必须的
    #sonatype-work：包含了 Nexus 生成的配置文件、日志文件、仓库文件等。当我们需要备份 Nexus 的时候默认备份此目录即可

#修改环境变量
vim /etc/profile
export NEXUS_HOME=/usr/local/nexus/nexus-3.18.1
export PATH=$PATH:$NEXUS_HOME/bin

source /etc/profile

#修改启动用户
vim /usr/local/nexus/nexus-3.18.1/bin/nexus.rc
#run_as_user=""         #内容就这一行，放开注释，填写用户即可

#修改端口
vim /usr/local/nexus/nexus-3.18.1/etc/nexus-default.properties  #默认是8081

#最后启动nexus
cd /usr/local/nexus/nexus-3.18.1/bin
./nexus start
./nexus status

#访问http://ip:8081，登陆用户admin 密码存放在：/usr/local/nexus/sonatype-work/nexus3/admin.password 目录

#开机自启动
vim /etc/rc.d/rc.local

/usr/local/nexus/nexus-3.18.1/bin/nexus start   #添加这一行内容

chmod 755 /etc/rc.d/rc.local
 









