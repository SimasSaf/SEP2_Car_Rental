package shared.transferObjects.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverLicenseTest
{
  private boolean isValid(String license)
  {

    return license.length() == 8 && license.matches("[0-9]+");
  }
  @Test public void validDriverLicense()
  {
    DriverLicense DriverLicense = new DriverLicense("12345678");
    assertTrue(isValid(DriverLicense.getLicense()));
  }

  @Test public void shortDriverLicense()
  {
    try
    {
      DriverLicense shortPhoneNo = new DriverLicense("123543");
      assertFalse(isValid(shortPhoneNo.getLicense()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void longDriverLicense()
  {
    try
    {
      DriverLicense longDriverLicense = new DriverLicense("1234567890");
      assertFalse(isValid(longDriverLicense.getLicense()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void driverLicenseContainsCharacters()
  {
    try
    {
      DriverLicense driverLicenseWithCharacter = new DriverLicense("DK123456");
      assertFalse(isValid(driverLicenseWithCharacter.getLicense()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void driverLicenseInvalidFormat()
  {
    try
    {
      DriverLicense invalidFormatDriverLicense = new DriverLicense("123-456-78");
      assertFalse(isValid(invalidFormatDriverLicense.getLicense()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

}