-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-08 23:22:16.665

-- tables
-- Table: Books
CREATE TABLE Books (
    id int  NOT NULL,
    ReadingList_id int  NOT NULL,
    isbn int  NULL,
    isRead bool  NOT NULL DEFAULT false,
    lastPageRead int  NOT NULL DEFAULT 0,
    readingListSequenceNumber int  NOT NULL,
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

-- End of file.

