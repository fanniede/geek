2.按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
(1).关闭自动提交,批量插入
DROP PROCEDURE IF EXISTS orders_initData;
DELIMITER $
CREATE PROCEDURE orders_initData()
BEGIN
    DECLARE i INT DEFAULT 1;
    set autocommit=0;
    WHILE i<=1000000 DO
            insert into geek.order_info(order_sn,customer_id,order_money,create_time,order_status,modified_time)VALUES(
            CEILING(rand()*100),100.21,1,now(),0,now());
        SET i = i+1;
    END WHILE;
    commit;
END $
CALL orders_initData();
测试插入100万条数据,用时65.857s
(1).自动提交，一条一条的插入
DROP PROCEDURE IF EXISTS orders_initData_one;
DELIMITER $
CREATE PROCEDURE orders_initData_one()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=100000 DO
            insert into geek.order_info(order_sn,customer_id,order_money,create_time,order_status,modified_time)VALUES(
            CEILING(rand()*100),100.21,1,now(),0,now());
        SET i = i+1;
    END WHILE;
END $
CALL orders_initData_one();
测试插入10万条数据,用时121.486s

9.读写分离 - 动态切换数据源版本 1.0
code/src/main/java/com/example/demo/database
10.读写分离 - 数据库框架版本 2.0
code/src/main/java/com/example/demo/shardingsphere/raw/jdbc/ShardingMasterSlaveDataSource.java