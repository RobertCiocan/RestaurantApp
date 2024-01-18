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
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart=unless-stopped mysql:latest
```
### Run mongodb in docker
```sh
docker run --name mongodb -d -p 27017:27017 --restart=unless-stopped -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root mongo:latest
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

