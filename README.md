# spring-boot-standardized-error-handling

A Spring Boot app demonstrating standardized error responses for REST APIs using Spring’s `ProblemDetail` (RFC 7807).

---

## Highlights

- User CRUD API with error responses formatted as [RFC 7807 Problem Details](https://datatracker.ietf.org/doc/html/rfc7807).
- Global exception handling using Spring’s `ProblemDetail` class.

---

## Example Error Responses

```json
{
    "type": "about:blank",
    "title": "Validation Failed",
    "status": 400,
    "detail": "Request has validation errors.",
    "instance": "/users",
    "errors": [
        "email: Email is required",
        "name: Name is required"
    ]
}
```

```json
{
    "type": "about:blank",
    "title": "Duplicate Email",
    "status": 409,
    "detail": "Email already exists: test.user1@gmail.com",
    "instance": "/users"
}
```
