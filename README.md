# Spring Boot Standardized Error Handling

A Spring Boot app demonstrating standardized error responses for REST APIs using Spring’s `ProblemDetail` (RFC 7807).

## ProblemDetail Object (RFC 7807)

A `ProblemDetail` object provides a consistent structure for API error responses.

**Standard fields:**
- `type`: A URI that identifies the problem type.
- `title`: A short, human-readable summary of the problem.
- `status`: The HTTP status code.
- `detail`: A human-readable explanation specific to this occurrence of the problem.
- `instance`: A URI reference identifying the specific occurrence of the problem.

You can also add your own fields (like `timestamp`, `errors`, `path`, etc.) as needed.

---

## Highlights

- User CRUD API with error responses formatted as [RFC 7807 Problem Details](https://datatracker.ietf.org/doc/html/rfc7807).
- Global exception handling using Spring’s `ProblemDetail` class.

---

## Example Error Responses

```json
{
    "type": "about:blank",
    "title": "Duplicate Email",
    "status": 409,
    "detail": "Email already exists: test.user1@gmail.com",
    "instance": "/users"
}
```

With additional field errors
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

With additional field timestamp
```json
{
    "type": "about:blank",
    "title": "User Not Found",
    "status": 404,
    "detail": "User not found with id: 2",
    "instance": "/users/2",
    "timestamp": "2025-06-07T07:10:20.439414600Z"
}
```
