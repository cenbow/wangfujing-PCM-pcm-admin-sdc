﻿echo 关闭pcm-admin-sdc系统开始...
kill -9 `cat /opt/app/pcm/pcm-admin-sdc/main.pid`
echo 关闭pcm-admin-sdc系统成功...
echo 拷贝编译jar包到应用目录 ...
rm -f /opt/app/pcm/pcm-admin-sdc/*.jar
rm -f /opt/app/pcm/pcm-admin-sdc/lib/*.jar
cp -f /root/.jenkins/jobs/pcm-admin-sdc/workspace/target/pcm-admin-sdc-0.0.3-SNAPSHOT.jar /opt/app/pcm/pcm-admin-sdc/
cp -f /root/.jenkins/jobs/pcm-admin-sdc/workspace/target/lib/*.jar /opt/app/pcm/pcm-admin-sdc/lib/
echo 拷贝完成....
echo 开始运行服务...
#后台启动pcm-admin-sdc进程
BUILD_ID= java -jar /opt/app/pcm/pcm-admin-sdc/pcm-admin-sdc-0.0.3-SNAPSHOT.jar  start 8043 8053 >/opt/app/pcm/pcm-admin-sdc/pcm-admin-sdc.log 2>&1 &
echo $! > /opt/app/pcm/pcm-admin-sdc/main.pid
echo 启动完毕.....
