salon service
=============

## 使用技術

* Java 12
* Spring Boot 2.1.5
  * Spring Framework 5.1.7
  * Spring Security 5.1.5
  * Thymeleaf 3.0.11
* MySQL


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

