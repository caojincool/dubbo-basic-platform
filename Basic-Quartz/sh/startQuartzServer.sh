if [ ` ps -ef |grep QuartzServer | grep -v grep | wc -l ` -eq 0 ]
then
        INFLIB=./lib:.:/wy/jdk/jdk1.7.0_55
				tag=QuartzServer;
				exe_option="-Xms256m -Xmx256m -Xmn64m -XX:PermSize=64m -XX:MaxPermSize=64m -Djava.awt.headless=true -XX:+HeapDumpOnOutOfMemoryError  -Djava.ext.dirs=$INFLIB -Dfile.encoding=GBK  -Duser.language=zh -Duser.region=CN -Dcom.sun.management.jmxremote.port=20390 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dlog4j.configuration=file:./classes/config/properties/log4j.properties"
				
				nohup java -cp :$tag:./classes $exe_option com.basic.framework.quartz.main.Main >./logs/QuartzSerSTD.log 2>./logs/QuartzSerERR.log & echo $! > "$tag".pid

else    
	echo "\nWarning: can not startup QuartzServer program,it is running.........!\n" 
fi
