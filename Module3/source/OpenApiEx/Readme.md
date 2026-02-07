# Example 

Sample OpenAPI Specification for a Student Management API.

You can use the swagger editor to visualize and test this API: https://editor.swagger.io/

Generate client code using OpenAPI Generator: https://openapi-generator.tech/

Refer for openapi generator installation : https://openapi-generator.tech/docs/installation

## Server Stubs
```bash
# OpenAPI Generator
openapi-generator-cli generate \
  -i studentApi.yaml \
  -g spring \
  -o ./server
```

## Client SDKs
```bash
# Generate Java client
openapi-generator-cli generate \
  -i studentApi.yaml \
  -g java \
  -o ./client-java

# Generate JavaScript client
openapi-generator-cli generate \
  -i studentApi.yaml \
  -g javascript \
  -o ./client-js
```

## Documentation Generation

```bash

# Redoc
npx @redocly/cli build-docs studentApi.yaml -o redoc-generated/index.html

```