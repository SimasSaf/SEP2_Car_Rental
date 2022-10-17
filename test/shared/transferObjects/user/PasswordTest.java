package shared.transferObjects.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest
{
  private boolean isValid(String password)
  {
    boolean hasDigits = false;
    boolean hasUppercase = false;
    boolean hasLowercase = false;
    for (char c : password.toCharArray())
    {
      if (Character.isDigit(c))
      {
        hasDigits = true;
      }
      if (Character.isUpperCase(c))
      {
        hasUppercase = true;
      }
      if (Character.isLowerCase(c))
      {
        hasLowercase = true;
      }
    }
    return password.length() > 7 && hasDigits && hasLowercase && hasUppercase;
  }
  @Test public void validPassword()
  {

    Password validPassword = new Password("Bob12345");

    assertTrue(isValid(validPassword.getPassword()));
  }

  @Test public void passwordWithNoDigits()
  {
    try
    {
      Password noDigitPassword = new Password("BobIsCool");
      assertFalse(isValid(noDigitPassword.getPassword()));
    }

    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void passwordWithNoUppercase()
  {
    try
    {
      Password noUppercasePassword = new Password("bob12345");
      assertFalse(isValid(noUppercasePassword.getPassword()));
    }

    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }

  }

  @Test public void passwordWithNoLowercase()
  {
    try
    {
      Password noLowercasePassword = new Password("BOB12345");
      assertFalse(isValid(noLowercasePassword.getPassword()));
    }

    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void passwordNotLongEnough()
  {
    try
    {
      Password shortPassword = new Password("Bob123");
      assertFalse(isValid(shortPassword.getPassword()));
    }

    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }
}