## To create a service:

```sh
mvn archetype:generate -DgroupId=com.gastrosfera -DartifactId={service}
```

## To build entire project:
```sh
mvn clean install -s settings.xml [-DskipTests]
```

## To build specific service/library
```sh
cd {service_directory}
```
```sh
mvn clean install -s ../settings.xml [-DskipTests]
```

## To create DB
### Run mysql in docker
```sh
<<<<<<< HEAD
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart=unless-stopped mysql:latest
```

docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql:latest

```

### Create database
```sh
docker exec -it mysql /bin/bash
mysql -u root -p
# input password (default root)
```
```sh
create database {database_name};
```

