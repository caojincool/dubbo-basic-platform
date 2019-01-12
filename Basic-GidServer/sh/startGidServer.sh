pid=`cat GidServer.pid`
if [ ` ps -ef |grep $pid | grep -v grep | wc -l ` -eq 0 ]
then
        INFLIB=./lib:.:/usr/local/jdk1.8.0_144
                                tag=GidServer;
                                exe_option="-Xms256m -Xmx256m -Xmn64m -XX:PermSize=64m -XX:MaxPermSize=64m -Djava.awt.headless=true -XX:+HeapDumpOnOutOfMemoryError  -Djava.ext.dirs=$INFLIB -Dfile.encoding=GBK  -Duser.language=zh -Duser.region=CN -Dcom.sun.management.jmxremote.port=20188 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dlog4j.configuration=file:./classes/config/properties/log4j.properties"

                     nohup java -cp :$tag:./classes $exe_option com.basic.framework.gid.server.GidServer >/dev/null 2>./logs/GidSerERR.log & echo $! > "$tag".pid
else
        echo "\nWarning: can not startup GidServer program,it is running.........!\n"
fi

