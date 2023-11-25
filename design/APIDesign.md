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
1. **Create Reading List:**
   - **URI:** '/reading-list/create-list'
   - **Method:** 'POST'
   - **Description:** Create a new reading-list.

2. **Delete Reading List:**
   - **URI:** '/reading-list/{id}'
   - **Method:** 'DELETE'
   - **Description:** Delete a reading list based on the ID.

3. **Get Reading List:**
   - **URI:** '/reading-list/{id}'
   - **Method:** 'GET' 
   - **Description:** Get the details of a reading list based on the ID.
     
4. **Add Book to Reading List by ISBN:**
   - **URI:** '/reading-list/{id}/add-book-by-isbn/{isbn}'
   - **Method:** 'POST'
   - **Description:** Add a book to the reading list using its ISBN.
   
5. **Add Book to Reading List by Name:**
   - **URI:** '/reading-list/{id}/add-book-by-name'
   - **Method:** 'POST'
   - **Description:** Add a book to the reading list using user-provided information.
   
6. **Remove Book from Reading List:**
   - **URI:** '/reading-list/{id}/remove-book-by-id/{bookId}'
   - **Method:** 'DELETE'
   - **Description:** Remove a book from the reading list.
   
7. **Update Book Reading Order:**
   - **URI:** '/reading-list/{id}/update-book-reading-order/{bookId}/{newPosition}'
   - **Method:** 'PUT'
   - **Description:** Update the order/position of a book in the reading list.
   
8. **Mark Book as Read:**
   - **URI:** '/reading-list/{id}/mark-book-as-read/{bookId}'
   - **Method:** 'PUT'
   - **Description:** Mark a book as read in the reading list.
   
9. **Get Book Details:**
   - **URI:** '/reading-list/{id}/get-book/{bookId}'
   - **Method:** 'GET'
   - **Description:** Get details of a specific book in the reading list.
   
10. **Update Last Page Read:**
   - **URI:** '/reading-list/{id}/update-last-page-read/{bookId}/{lastPageRead}'
   - **Method:** 'PUT'
   - **Description:** Update the last page read for a book in the reading list.

11. **Update Book Details:**
   - **URI:** '/reading-list/{id}/update-book/{bookId}'
   - **Method:** 'PUT'
   - **Description:** Update details of a book in the reading list.

1. **Retrieve Books from GoogleBooks API:**
    - **URI:** '/api/books'
    - **Method:** 'GET'
    - **Description:** Retrieve a list of books from the Google Books API based on user input

2. **Add a Book to Reading List:**
    - **URI:** '/api/readinglist'
    - **Method:** 'POST'
    - **Description:** Add a book to the reading list

3. **Get Reading List:**
    - **URI:** 'api/readingList'
    - **Method:** 'GET'
    - **Description:** Retrieve the reading list.

4. **Update Book in Reading List:**
    - **URI:** '/api/readinglist/{readinglist_id}'
    - **Method:** 'PUT'
    - **Description:** Update the details of a book in the reading list.

5. **Remove Book from Reading List:**
    - **URI:** '/api/readinglist/{readinglist_id}'
    - **Method:** 'DELETE'
    - **Description:** Remove a book from the reading list.

