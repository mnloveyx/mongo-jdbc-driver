package com.dbschema.mongo;

import org.junit.Test;

import static com.dbschema.mongo.Util.insertCredentials;
import static org.junit.Assert.assertEquals;

public class InsertCredentialsToUriTest {
  private static final String NO_CREDENTIALS = "mongodb://localhost:27017/admin";
  private static final String NO_CREDENTIALS_NO_PORT = "mongodb://localhost";
  private static final String HAS_CREDENTIALS = "mongodb://user:password@localhost:27017/admin";
  private static final String ONLY_USERNAME = "mongodb://user@localhost:27017/admin";
  private static final String NO_CREDENTIALS_SRV = "mongodb+srv://172.16.254.1:27017";
  private final String USERNAME = "new_username";
  private final String PASSWORD = "new_password";

  @Test
  public void noInsert() {
    assertEquals(NO_CREDENTIALS, insertCredentials(NO_CREDENTIALS, null, null));
    assertEquals(NO_CREDENTIALS_NO_PORT, insertCredentials(NO_CREDENTIALS_NO_PORT, null, null));
    assertEquals(HAS_CREDENTIALS, insertCredentials(HAS_CREDENTIALS, null, null));
    assertEquals(ONLY_USERNAME, insertCredentials(ONLY_USERNAME, null, PASSWORD));
    assertEquals(NO_CREDENTIALS_SRV, insertCredentials(NO_CREDENTIALS_SRV, null, null));
  }

  @Test
  public void insert() {
    assertEquals("mongodb://new_username@localhost:27017/admin", insertCredentials(NO_CREDENTIALS, USERNAME, null));
    assertEquals("mongodb+srv://new_username:new_password@172.16.254.1:27017", insertCredentials(NO_CREDENTIALS_SRV, USERNAME, PASSWORD));
    assertEquals("mongodb://new_username:new_password@localhost", insertCredentials(NO_CREDENTIALS_NO_PORT, USERNAME, PASSWORD));
  }

  @Test
  public void invalidString() {
    assertEquals("abc", insertCredentials("abc", USERNAME, PASSWORD));
  }
}
