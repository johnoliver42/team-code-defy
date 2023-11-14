package org.TeamCodeDefy.util;


/**
 * This class is used to contain utility methods for testing.
 *
 * @author John Oliver
 */
public final class UnitTestUtil {

    public static void createTestDatabase() {
        Database database = Database.getInstance();
        database.runSQL("ReadingList_create.sql");
    }

    public static void createTestData() {
        Database database = Database.getInstance();
        database.runSQL("ReadingList_Test_Data.sql");
        database.runSQL("Books_Test_Data.sql");
    }

}
