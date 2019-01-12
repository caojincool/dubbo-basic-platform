# __author__ = "Amos"
# Email: 379833553@qq.com

import os
import re

server_name = os.getenv('server_name')
mysql_addr = os.getenv('mysql_addr')
mysql_user_passwd = os.getenv('mysql_user_passwd')
activemq_addr = os.getenv('activemq_addr')
gidserver_url = os.getenv('gidserver_url')
redis_addr = os.getenv('redis_addr')

serverdir = '/opt/workflowservice'
if not os.path.isdir(serverdir):
	os.system('mkdir -p {0}/classes & mkdir -p {0}/lib & mkdir -p {0}/logs'.format(serverdir))
projectdir_name = os.path.basename(serverdir)


def alter(filepath,old,new,linenum=None):
    with open(filepath, 'r+', encoding='utf-8') as f:
        ff = f.readlines()
        if linenum:
            ff[linenum] = re.sub(old,new,ff[linenum])
        else:
            for line in range(len(ff)):
                ff[line] = re.sub(old,new,ff[line])

    with open(filepath, 'w+', encoding='utf-8') as f2:
        f2.writelines(ff)


def update_files():
    os.system("rm -rf %s/classes/*" % serverdir)
    os.system("mv /home/jenkins/%s/classes/* %s/classes/" %(projectdir_name,serverdir))
    os.system("rm -rf %s/lib/*" % serverdir)
    os.system("mv /home/jenkins/%s/lib/* %s/lib/" %(projectdir_name,serverdir))
    os.system("rm -rf %s/*.sh" % serverdir)
    os.system("mv /home/jenkins/%s/*.sh %s/" %(projectdir_name,serverdir))
    os.system("chmod +x %s/*.sh" % serverdir)


def config():
    dbconfig = "%s/classes/config/properties/datasource.properties" % serverdir
    alter(dbconfig,'(?<=jdbc:mysql://).*?(?=\?useUnicode=true)',mysql_addr)
    alter(dbconfig,'(?<=datasource.username=).*',mysql_user_passwd.split('/')[0])
    alter(dbconfig,'(?<=datasource.password=).*',mysql_user_passwd.split('/')[1])
    
    mqcon = "%s/classes/config/properties/activemq-consumer.properties" % serverdir
    alter(mqcon,'(?<=failover:\().*(?=\))',activemq_addr)
    mqpro = "%s/classes/config/properties/activemq-producer.properties" % serverdir
    alter(mqpro,'(?<=failover:\().*(?=\))',activemq_addr)
   
    gidconfig = "%s/classes/config/properties/gidclient.properties" % serverdir
    alter(gidconfig,'(?<=gid.server.url = ).*',gidserver_url)
    redisconfig = "%s/classes/config/properties/redis.properties" % serverdir
    alter(redisconfig,'(?<=group..redis.node1=).*',redis_addr.split(',')[0].split(':')[0])
    alter(redisconfig,'(?<=group..redis.node2=).*',redis_addr.split(',')[1].split(':')[0])
    alter(redisconfig,'(?<=group..redis.node3=).*',redis_addr.split(',')[2].split(':')[0])
    alter(redisconfig,'(?<=group..redis.port1=).*',redis_addr.split(',')[0].split(':')[1])
    alter(redisconfig,'(?<=group..redis.port2=).*',redis_addr.split(',')[1].split(':')[1])
    alter(redisconfig,'(?<=group..redis.port3=).*',redis_addr.split(',')[2].split(':')[1])
    alter(redisconfig,'(?<=redis.sentine.database=).*',redis_addr.split(',')[3])



def stop():
    try:
        with open('%s/%s.pid' %(serverdir,server_name),'r') as f:
            pid = f.read().strip()
        os.system("rm -rf %s/%s.pid" % (serverdir, server_name))
        os.system('kill -9 %s' %pid)
    except:
        pass


def start():
    os.chdir(serverdir)
    os.system("./start%s.sh" % server_name)


if __name__ == '__main__':
    # 关闭服务
    stop()
    # 更新服务
    update_files()
    config()
    # 启动服务
    start()
