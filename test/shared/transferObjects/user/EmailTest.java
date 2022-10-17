package shared.transferObjects.user;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest
{
  private boolean isValid(String email)
  {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
        + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    return pat.matcher(email).matches();
  }


  @Test public void validEmail()
  {
    Email validEmail = new Email("bob@gmail.com");
    assertTrue(isValid(validEmail.getEmail()));
  }

  @Test public void validEmailWithDot()
  {
    Email validEmailWithDot = new Email("bob.oldenuff@gmail.com");
    assertTrue(isValid(validEmailWithDot.getEmail()));
  }

  @Test public void validEmailWithDifferentDomain()
  {
    Email validEmailWithDifferentDomain = new Email("bob.oldenuff@via.dk");
    assertTrue(isValid(validEmailWithDifferentDomain.getEmail()));
  }

  @Test public void validEmailWithNumbers()
  {
    Email validEmailWithNumber = new Email("bob123@gmial.com");
    assertTrue(isValid(validEmailWithNumber.getEmail()));
  }

  @Test public void validEmailWithCustomAddress()
  {
    Email validEmailWithCustomAddress = new Email(
        "bob.oldenuff@ohm.rentals.dk");
    assertTrue(isValid(validEmailWithCustomAddress.getEmail()));
  }

  @Test public void validEmailWithUppercaseCharacters()
  {
    Email validEmailWithUppercaseCharacters = new Email(
        "Bob.Oldenuff@Ohm.Rentals.dk");
    assertTrue(isValid(validEmailWithUppercaseCharacters.getEmail()));
  }

  @Test public void emailWithDoubleDomain()
  {

    Email emailWIthMultipleDomain = new Email("bob.oldenuff@gamil.dk.com");
    assertTrue(isValid(emailWIthMultipleDomain.getEmail()));

  }

  @Test public void emailWithNoAddress()
  {
    try
    {
      Email emailWithNoAddress = new Email("bob.oldenuff");
      assertFalse(isValid(emailWithNoAddress.getEmail()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void emailWithNoAtSign()
  {
    try
    {
      Email emailWithNoAtSign = new Email("bob.oldenuff.via.dk");
      assertFalse(isValid(emailWithNoAtSign.getEmail()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void emailWithNoDomain()
  {
    try
    {
      Email emailWithNoDomain = new Email("bob.oldenuff@gmail");
      assertFalse(isValid(emailWithNoDomain.getEmail()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void emailWithNoAddress2()
  {
    try
    {
      Email emailWithNoAddress = new Email("bob.oldenuff@com");
      assertFalse(isValid(emailWithNoAddress.getEmail()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void emailWithLongDomain()
  {
    try
    {
      Email emailWithLongDomain = new Email("bob.oldenuff@gmail.copenhagen");
      assertFalse(isValid(emailWithLongDomain.getEmail()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void emailWithDoubleAtSight()
  {
    try
    {
      Email emailWithDoubleAtSign = new Email("bob.oldenuff@@gmail.com");
      assertFalse(isValid(emailWithDoubleAtSign.getEmail()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }
}