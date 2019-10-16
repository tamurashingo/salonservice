salon service
=============

## 使用技術

* Java 12
* Spring Boot 2.1.5
  * Spring Framework 5.1.7
  * Spring Security 5.1.5
  * Thymeleaf 3.0.11
* MySQL

## 実行方法

```bash
$ docker-compose up -d
$ ./gradlew clean build
$ SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver SPRING_DATASOURCE_URL='jdbc:mysql://localhost:43306/salonservice' SPRING_DATASOURCE_USERNAME=root SPRING_DATASOURCE_PASSWORD=password  java -jar build/libs/salonservice-0.0.1-SNAPSHOT.jar
```


## テスト

### MySQL on Docker

```bash
$ docker-compose up -d
```

port 43306 で MySQL が立ち上がります。
その際に `docker/mysql/init` 内のスクリプトを実行し、データベースを作成しています。

### Database Migration for MySQL on Docker

```bash
$ ./gradlew migrateTest
```

docker で起動している MySQL にマイグレーションを行います。

## 構成

### app

Controller、Helper、Formを配置。
各画面ごとに作成する。


### domain

#### service

処理の塊。

#### model

serviceで使用するオブジェクト。

### repository

modelのCRUD。





