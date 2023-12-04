package org.TeamCodeDefy.persistance;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.util.UnitTestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;


class GenericDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final GenericDao<Book> genericBookDao = new GenericDao<>(Book.class);
    private final GenericDao<ReadingList> genericReadingListDao = new GenericDao<>(ReadingList.class);



    @BeforeEach
    void setUp() {
        UnitTestUtil.createTestDatabase();
        UnitTestUtil.createTestData();
    }

    @Test
    void getById() {
        GenericDao<Book> bookDao = new GenericDao<>(Book.class);
        Book book = bookDao.getById(1028);
        assertEquals("Upgradable systemic software", book.getTitle());
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void findByPropertyEqual() {
    }
}