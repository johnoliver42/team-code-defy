# API Design for Reading List using Google Books API

## OBJECT MODELING

### Book Object
Represents a book in the user's reading list.

```json
{
  "id": "unique_identifier",
  "isbn": "ISBN Number",
  "isRead": "true/false",
  "lastPageRead": "page_number",
  "readingListSequenceNumber": "sequence_number",
  "publisher": "Publisher Name",
  "language": "Language",
  "author": "Author Name",
  "pageCount": "number_of_pages",
  "averageRating": "average_rating", 
  "title": "The Book Title",
  "thumbnail": "URL to book cover image",
  "description": "Book Description"
}
```

### ReadingList Object
Represents an item in the user's reading list. 

```json
{
  "id": "unique_identifier",
  "listName": "Reading List Name",
  "createDate": "timestamp",
  "books": [
    {
      "id": "unique_identifier",
      "isbn": "ISBN Number",
      "isRead": "true/false",
      "lastPageRead": "page_number",
      "readingListSequenceNumber": "sequence_number",
      "publisher": "Publisher Name",
      "language": "Language",
      "author": "Author Name",
      "pageCount": "number_of_pages",
      "averageRating": "average_rating",
      "title": "The Book Title",
      "thumbnail": "URL to book cover image",
      "description": "Book Description"
    }
  ]  
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