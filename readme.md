#跳过测试并打包
mvn clean package -Dmaven.test.skip=true

#指定端口运行
java -jar .\monitorManage-1.0-SNAPSHOT.jar --server.port=8081

##swagger地址
http://127.0.0.1:8080/swagger-ui.html

#连续查询
``连续查询主要用在将数据归档，以降低系统空间的占用率，主要是以降低精度为代价``
show continuous queries

DROP CONTINUOUS QUERY <cq_name> ON <database_name>

## 创建30s取样平均值
create continuous query cq_mean_1m on monitorMS BEGIN select mean(height_ld) as height_ld,mean(pressure) as pressure,mean(temperature) as temperature into forever.factory1_1m from factory1 group by time(30s),a1_tank,a2_oil END
数据结构如下
name: factory1_30s
time                a1_tank a2_oil height_ld          pressure            temperature
----                ------- ------ ---------          --------            -----------
1575373260000000000 tank1   丙烷     11.979658126831055 0.0643330452342828  14.5332932472229
1575373260000000000 tank2   丙烷     11.979991594950357 0.06333304631213348 14.516626516977945
1575373260000000000 tank3   丙烷     11.97932481765747  0.06399971184631188 14.566626389821371
1575373260000000000 tank4   丁烷     11.979825019836426 0.06366637907922268 14.483293215433756
1575373260000000000 tank5   丁烷     11.980324745178223 0.06383304546276729 14.516626516977945
1575373260000000000 tank6   丁烷     11.980324745178223 0.06399971184631188 14.566626389821371

SELECT * FROM "forever"."factory1_1m" WHERE time > now() - 5m