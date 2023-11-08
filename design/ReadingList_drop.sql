-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-08 23:22:16.665

-- foreign keys
ALTER TABLE Books
    DROP FOREIGN KEY Books_ReadingList;

-- tables
DROP TABLE Books;

DROP TABLE ReadingList;

-- End of file.

