## Startup

```
# Stopping localstack
helm uninstall localstack

# Starting localstack
helm upgrade --install localstack localstack-repo/localstack

# Spin up the project's stack
docker-compose up
```

## If the stack has not been created
```
cd terraform

terrafirn init

terraform apply
```

## Handy commands

```
# List all tables
aws --endpoint-url=http://localhost:4566 dynamodb list-tables

# Scan content of a given table
aws --endpoint-url=http://localhost:4566 dynamodb scan --table-name $TABLE_NAME

```