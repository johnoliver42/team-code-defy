package org.TeamCodeDefy.persistance;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.util.UnitTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericDaoTest {

    private GenericDao genericBookDao = new GenericDao(Book.class);
    private GenericDao genericReadingListDao = new GenericDao(ReadingList.class);



    @BeforeEach
    void setUp() {

        UnitTestUtil.createTestDatabase();
        UnitTestUtil.createTestData();

    }

    @Test
    void getById() {
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