# cURL Commands for BookController Testing

## Base URL
```bash
BASE_URL="http://localhost:8080"
```

---

## 1. GET All Books

```bash
curl http://localhost:8080/api/books
```

**Expected Response:** `[]` (empty list initially)

---

## 2. POST - Create New Book

### Create Book #1
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot in Action",
    "author": "Craig Walls"
  }'
```

### Create Book #2
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java",
    "author": "Joshua Bloch"
  }'
```

### Create Book #3
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Clean Code",
    "author": "Robert Martin"
  }'
```

**Expected Response:**
```json
{
  "id": 1,
  "title": "Spring Boot in Action",
  "author": "Craig Walls"
}
```

---

## 3. GET All Books (After Creating)

```bash
curl http://localhost:8080/api/books
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot in Action",
    "author": "Craig Walls"
  },
  {
    "id": 2,
    "title": "Effective Java",
    "author": "Joshua Bloch"
  },
  {
    "id": 3,
    "title": "Clean Code",
    "author": "Robert Martin"
  }
]
```

---

## 4. GET Book by ID

### Get Book with ID 1
```bash
curl http://localhost:8080/api/books/1
```

### Get Book with ID 2
```bash
curl http://localhost:8080/api/books/2
```

### Get Non-existent Book (ID 999)
```bash
curl http://localhost:8080/api/books/999
```

**Expected Response:** `null` or empty

---

## 5. PUT - Update Book

### Update Book with ID 1
```bash
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot in Action - 2nd Edition",
    "author": "Craig Walls"
  }'
```

### Update Book with ID 2
```bash
curl -X PUT http://localhost:8080/api/books/2 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Effective Java - 3rd Edition",
    "author": "Joshua Bloch"
  }'
```

**Expected Response:**
```json
{
  "id":2,
  "title":"Effective Java - 3rd Edition",
  "author":"Joshua Bloch"
}
```

---

## 6. GET Search - Query Parameter

### Search for "Spring"
```bash
curl "http://localhost:8080/api/books/search?title=Spring"
```

### Search for "Java"
```bash
curl "http://localhost:8080/api/books/search?title=Java"
```

### Search for "Code"
```bash
curl "http://localhost:8080/api/books/search?title=Code"
```

### Search with URL encoding (for spaces)
```bash
curl "http://localhost:8080/api/books/search?title=Spring%20Boot"
```

**Expected Response:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot in Action - 2nd Edition",
    "author": "Craig Walls"
  }
]
```

---

## 7. DELETE Book

### Delete Book with ID 1
```bash
curl -X DELETE http://localhost:8080/api/books/1
```

### Delete Book with ID 2
```bash
curl -X DELETE http://localhost:8080/api/books/2
```

**Expected Response:** `"Book deleted"`

---

## 8. Verify Deletion

```bash
curl http://localhost:8080/api/books
```

**Expected Response:** List without deleted books

---

## Complete Test Sequence

Run these commands in order to test the full workflow:

```bash
# 1. Check empty list
curl http://localhost:8080/api/books

# 2. Create three books
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot in Action","author":"Craig Walls"}'

curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java","author":"Joshua Bloch"}'

curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert Martin"}'

# 3. Get all books
curl http://localhost:8080/api/books

# 4. Get specific book
curl http://localhost:8080/api/books/1

# 5. Search for book
curl "http://localhost:8080/api/books/search?title=Java"

# 6. Update a book
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot in Action - Updated","author":"Craig Walls"}'

# 7. Verify update
curl http://localhost:8080/api/books/1

# 8. Delete a book
curl -X DELETE http://localhost:8080/api/books/2

# 9. Verify deletion
curl http://localhost:8080/api/books
```

---

## Pretty Print JSON (Optional)

Add `| jq` to format JSON output (requires jq installation):

```bash
# Install jq first:
# Ubuntu/Debian: sudo apt-get install jq
# macOS: brew install jq

# Then use:
curl http://localhost:8080/api/books | jq

# Or use Python:
curl http://localhost:8080/api/books | python -m json.tool
```

---

## Verbose Output (Show Headers)

```bash
curl -v http://localhost:8080/api/books
```

---

## Save Response to File

```bash
curl http://localhost:8080/api/books -o books.json
```

---

## Test with Different Status Codes

### See HTTP Status Code
```bash
curl -w "\nHTTP Status: %{http_code}\n" http://localhost:8080/api/books
```

### Include Response Headers
```bash
curl -i http://localhost:8080/api/books
```

---

## Windows PowerShell Commands

If using PowerShell on Windows, use these formats:

```powershell
# GET
Invoke-WebRequest -Uri "http://localhost:8080/api/books" -Method GET

# POST
Invoke-WebRequest -Uri "http://localhost:8080/api/books" -Method POST `
  -ContentType "application/json" `
  -Body '{"title":"Spring Boot","author":"Craig Walls"}'

# PUT
Invoke-WebRequest -Uri "http://localhost:8080/api/books/1" -Method PUT `
  -ContentType "application/json" `
  -Body '{"title":"Updated Title","author":"Updated Author"}'

# DELETE
Invoke-WebRequest -Uri "http://localhost:8080/api/books/1" -Method DELETE
```

---
