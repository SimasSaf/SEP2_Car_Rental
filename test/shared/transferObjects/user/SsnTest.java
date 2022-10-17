package shared.transferObjects.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SsnTest
{



  @Test public void shortSsn()
  {

    assertThrows(IllegalArgumentException.class,
        () -> new Ssn("1232353"));
  }

  @Test public void ssnContainsCharacters()
  {

    assertThrows(IllegalArgumentException.class,
        () -> new Ssn("123456hjk"));
  }

  @Test public void longSsn()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Ssn("123456781234567"));
  }
}