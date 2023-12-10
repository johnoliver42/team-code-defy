
-- foreign keys
ALTER TABLE IF EXISTS Books
    DROP FOREIGN KEY Books_ReadingList;

-- tables
DROP TABLE IF EXISTS Books;

DROP TABLE IF EXISTS ReadingList;


-- tables
-- Table: Books
CREATE TABLE Books (
    id int  auto_increment NOT NULL,
    ReadingList_id int NOT NULL,
    isbn varchar(20)  NULL,
    isRead bool  NOT NULL DEFAULT false,
    lastPageRead int  NOT NULL DEFAULT 0,
    readingListSequenceNumber int,
    publisher varchar(100)  NULL,
    language varchar(50)  NULL,
    author varchar(100)  NULL,
    title varchar(100)  NOT NULL,
    pageCount int  NULL,
    averageRating varchar(4)  NULL,
    description text  NULL,
    thumbnailLink text  NULL,
    CONSTRAINT Books_pk PRIMARY KEY (id)
);

-- Table: ReadingList
CREATE TABLE ReadingList (
    id int  NOT NULL,
    listName varchar(100)  NOT NULL,
    createDate timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ReadingList_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Books_ReadingList (table: Books)
ALTER TABLE Books ADD CONSTRAINT Books_ReadingList FOREIGN KEY Books_ReadingList (ReadingList_id)
    REFERENCES ReadingList (id);



