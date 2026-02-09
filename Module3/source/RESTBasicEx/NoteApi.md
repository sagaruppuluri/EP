## 1. POST - Create New Note

### Create Book #1
```bash
curl -X POST http://localhost:8080/api/notes \
  -H "Content-Type: application/json" \
  -d '{
    "text": "Sample Note"
  }'
```

### GET Note #2 

```bash
curl http://localhost:8080/api/notes/1
```

### GET Note #3

```bash
curl http://localhost:8080/api/notes/2
```
