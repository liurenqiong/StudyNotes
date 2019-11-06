打包 
install compile package

上传到私服
deploy -e

指定配置文件
--spring.profiles.active=local

打包跳过测试且设置打包环境
 clean install -DskipTests -U -Pdev# StudyNotes
学习笔记


linux 安装 Maven  

   第一步 下载
   wget http://mirrors.cnnic.cn/apache/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz
   解压  tar -xvf apache-maven-3.6.2-bin.tar.gz
   增加环境变量

   vi /etc/profile
   export MAVEN_HOME=/usr/java/apache-maven-3.6.2
   export PATH=$PATH:$MAVEN_HOME/bin
 
source /etc/profile

rpm 文件解压

rpm2cpio jdk-8u60-linux-x64.rpm | cpio -div

linux 安装 jdk
第一步 下载
解压  tar -xvf jdk-8u231-linux-x64.tar.gz

vi /etc/profile

export JAVA_HOME=/usr/java/jdk1.8.0_231
export CLASSPATH=$JAVA_HOME/jre/lib:$JAVA_HOME/lib
export PATH=$PATH:$JAVA_HOME/bin

source /etc/profile

   查看环境变量
   echo $PATH 
 
 linux 安装 nexus
  下载 
   http://119.29.241.56:8080/view/1320
  解压  tar -xvf nexus-3.13.0-01-unix.tar.gz
  移动改名
  mv -v nexus-3.13.0-01 /usr/java/nexus
  groupadd nexus
  useradd -r -g nexus nexus
   将安装目录所有者及所属组改为mysql ，这个根据自己的目录来
  chown -R nexus.nexus /usr/java/nexus

  切换用户
  su nexus

  修改自定义配置：
  a、修改配置文件，nexus目录下，cd etc，可以修改端口号和ip地址
  如，修改端口号：vim etc/nexus-default.properties  =>  application-port=8081
b、如果Linux硬件配置比较低的话，建议修改为合适的大小，否则会出现运行崩溃的现象
　 vim nexus/bin/nexus.vmoptions //虚拟机选项配置文件，可以修改数据、日志存储位置

更改nexus 配置文件
run_as_user='nexus'

nexus 常用命令
启动 nexus start //停止 nexus stop //重启 nexus restart //查看状态 nexus status下面我们启动Nexus：

查看nexus服务是否启动成功
　　使用命令：ps -ef|grep nexus     lsof -i:8081    netstat -tunlp
 
关闭防火墙命令  CentOS 7.0            #查看centos 版本 cat /etc/redhat-release
systemctl stop firewalld.service #停止firewall
systemctl disable firewalld.service #禁止firewall开机启动
firewall-cmd --state #查看默认防火墙状态（关闭后显示notrunning，开启后显示running）




