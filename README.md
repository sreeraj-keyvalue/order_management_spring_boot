# order_management_spring_boot

To run migrations, run the following command
```
docker-compose --profile migration run --rm migrate-up
```

To rollback a migration, run the following command
```
docker-compose --profile migration run --rm migrate-down
```

To check code coverage, run
```
mvn clean jacoco:prepare-agent install jacoco:report
```