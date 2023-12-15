# Project Plan

### Project Backlog
- [x] Create a repository for the project (John)
- [x] Create a README.md file (John)
- [x] Create project plan file (John)
- [x] Create a team charter (Everyone)
- [x] Create a team resume (Everyone)
- [x] Create a project journal (John)
- [x] Come up with ideas for the project to present to the team on Sunday (Everyone)
- [x] Decide on the project to move forward with (Everyone)
- [x] Determine API to use with project. (Everyone)
- [x] Create the Problem Statement (Everyone)
- [x] Design the API front end. (Everyone)

### Create Repository (John)
- [x] Create a repository for the project 
- [x] Create a README.md file 
- [x] Create journal, charter, resume files. 

### Create basic project structure/design (John)
- [x] Create project folders/packages.
- [x] Create log4j and Hibernate base config files and sample files.
- [x] Create major classes.
- [x] Stub out example functions in major classes.

### Create Google Books API Code (Kailyn)
- [x] Create a Google Books package/classes
- [x] Create POJO for API response using Jackson
- [x] Create DAO that returns response
- [x] Write unit tests using junit
- [x] Map to Book entity
- [x] Documentation for all classes/methods

### API Documentation (Shilpa)
- [ ] Create API documentation for the Reading List API using Swagger.io

### API Documentation On GitHub
- [x] Add APIDesign.md to the project repository (Shilpa)
- [x] Update APIDesign.md to reflect the current API object model (John)

### Service Classes (John)
- [x] Stub out service functions. 
- [x] Create test class for BookListService.
- [x] Create test functions for each service function.
- [x] Write code for each service function.
  - [x] createReadingList()
  - [x] deleteReadingList()
  - [x] getReadingList()
  - [x] addBookToReadingListByIsbn()
  - [x] addBookToReadingListByName()
  - [x] removeBookFromReadingListByIsbn()
  - [x] updateReadingListOrder()
  - [x] assignOrUpdateReadingListSequenceNumber()
  - [x] reOrderReadingListBookSequence()
  - [x] addBookToReadingList()
  - [x] updateReadingList()
  - [x] setBookReadStatus()
  - [x] getBook()
  - [x] updateLastPageRead()
  - [x] updateBook()
- [x] Refactor code to properly manage the reading list sequence number.
  - [x] Add sequence number to Book entity.
  - [x] Update the sequence number when a book is added to the reading list or removed from the reading list.
  - [x] Update the sequence number when a book is moved up or down in the reading list.
- [x] Update/add tests to verify the sequence number is properly managed.

### Web/Route Classes (Shilpa)
- [x] Stub out web/route functions.
- [x] Create test class for BookListRouts.
- [x] Add annotations with proper paths.
- [ ] Add code to convert from URL parameters to functions/objects.
- [ ] Add code to convert from Java POJOse to JSON.
- [x] Create test functions for each route.

### Update Web/Route Classes (John)
- [x] Add Application class to start the server.
- [x] Update route functions to correctly use the static service functions.
- [x] Add in basic error handling.
- [x] Add in logging.
- [x] Add/update Java POJO to JSON object mapping.
- [x] Add in Jackson annotations to properly map JSON to Java POJOs.
- [x] Fix Jackson mapping issues.
- [x] Add in redirect to GitHub project page.
- [x] Add/update tests to use the actual route functions instead of replicating the og code, complete with JSON to Java POJO mapping.
- [x] Create HTTP Requests tests using IntelliJ built in service to test the API when it is live.

### Amazon Web Hosting (John)
- [x] Create an Elastic Beanstalk instance
- [x] Create a database instance
- [x] Add link to project site to README.md

### Database Persistence (John)
- [x] Create a database/Hibernate package/classes
- [x] Create test data for database.
- [x] Add helper class to run SQL scripts for testing. 
- [x] Create POJO for DB tables.
- [x] Create test class for DB classes.
- [x] Create functions to test DB CRUD operations.
  - [x] Create/add
  - [x] Delete
  - [x] Update
  - [x] Read
- [x] Create test functions for each DB function.
- [x] Added a custom ID generator for the ReadingList table so that the ID is long enough to discourage guessing and is not sequential.
- [x] Update database design to fix issues found during testing. Mainly remove some not null constraints.



