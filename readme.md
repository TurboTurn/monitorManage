#跳过测试并打包
mvn clean package -Dmaven.test.skip=true

#指定端口运行
java -jar .\monitorManage-1.0-SNAPSHOT.jar --server.port=8081