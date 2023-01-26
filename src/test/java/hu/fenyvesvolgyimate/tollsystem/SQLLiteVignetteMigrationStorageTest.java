package hu.fenyvesvolgyimate.tollsystem;

import hu.fenyvesvolgyimate.tollsystem.dao.SQLLiteVignetteMigrationStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLLiteVignetteMigrationStorageTest {

    @Test
    void listUniqueRegistrationNumbers() {
        SQLLiteVignetteMigrationStorage storage = new SQLLiteVignetteMigrationStorage();
        assertEquals(2, storage.listUniqueRegistrationNumbers().size());
    }
}