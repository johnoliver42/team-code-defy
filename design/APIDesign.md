# API Design for Reading List using Google Books API

## OBJECT MODELING

### Book Object
Represents a book retrieved from the Google Books API or added to the reading list.

```json
{
  "id": "unique_identifier",
  "title": "The Book Title",
  "author": "Author Name",
  "isbn": "ISBN Number",
  "thumbnail": "URL to book cover image",
  "description": "Book Description"
}
```

### ReadingList Object
Represents an item in the user's reading list.

```json
{
  "id": "unique_identifier",
  "book": {
    "id": "unique_identifier",
    "title": "The Book Title",
    "author": "Author Name",
    "isbn": "ISBN Number",
    "thumbnail": "URL to book cover image",
    "description": "Book Description"
  },
  "date_added": "timestamp"
}
```

## Resource URIs

1. **Retrieve Books from GoogleBooks API:**
    - **URI:** ''
    - **Method:** ''
    - **Description:**


2. **Add a Book to Reading List:**
    - **URI:** ''
    - **Method:** ''
    - **Description:**

3. **Get Reading List:**
    - **URI:** ''
    - **Method:** ''
    - **Description:**

4. **Update Book in Reading List:**
    - **URI:** ''
    - **Method:** ''
    - **Description:**

5. **Remove Book from Reading List:**
    - **URI:** ''
    - **Method:** ''
    - **Description:**

## RESOURCE REPRESENTATION

## HTTP METHODS