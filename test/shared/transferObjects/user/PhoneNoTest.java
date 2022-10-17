package shared.transferObjects.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNoTest
{
  private boolean isValid(String number)
  {
    return number.length() == 8 && number.matches("[0-9]+");
  }

  @Test public void validPhoneNo()
  {
    PhoneNo phoneNo = new PhoneNo("12345678");
    assertTrue(isValid(phoneNo.getNumber()));
  }

  @Test public void shortPhoneNo()
  {
    try
    {
      PhoneNo shortPhoneNo = new PhoneNo("123543");
      assertFalse(isValid(shortPhoneNo.getNumber()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void longPhoneNo()
  {
    try
    {
      PhoneNo longPhoneNo = new PhoneNo("1234567890");
      assertFalse(isValid(longPhoneNo.getNumber()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void phoneNoContainsCharacters()
  {
    try
    {
      PhoneNo phoneNoWithCharacter = new PhoneNo("DK123456");
      assertFalse(isValid(phoneNoWithCharacter.getNumber()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void phoneNumberInvalidFormat()
  {
    try
    {
      PhoneNo invalidFormatPhoneNo = new PhoneNo("123-456-78");
      assertFalse(isValid(invalidFormatPhoneNo.getNumber()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }
}