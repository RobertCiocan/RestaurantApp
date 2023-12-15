## To create a service:

```sh
mvn archetype:generate -DgroupId=com.gastrosfera -DartifactId={service}
```

## To build entire project:
```sh
mvn clean install -s settings.xml [-DskipTests]
```

## To build specific service/library
cd {service_directory}
```sh
mvn clean install -s ../settings.xml [-DskipTests]
```
