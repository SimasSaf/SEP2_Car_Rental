package shared.transferObjects;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class PlateNoTest
{
  private boolean isValid(String plateNo)
  {
    String licenseRegex = "[A-Za-z][A-Za-z][0-9][0-9][0-9][0-9][0-9]";
    Pattern pattern = Pattern.compile(licenseRegex);
    return pattern.matcher(plateNo).matches();
  }

  @Test public void validPlateNo()
  {
    PlateNo plateNo = new PlateNo("AA12345");
    assertTrue(isValid(plateNo.getPlate()));
  }

  @Test public void validPlateNoWithLowercase()
  {
    PlateNo plateNoLowercase = new PlateNo("aa12345");
    assertTrue(isValid(plateNoLowercase.getPlate()));
  }

  @Test public void plateNoWithBadFormatting()
  {
    try
    {
      PlateNo plateNoWithBadFormatting = new PlateNo("AA-233-44");
      assertFalse(isValid(plateNoWithBadFormatting.getPlate()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void plateNoWithNoNumbers()
  {
    try
    {
      PlateNo plateNoWithNoNumbers = new PlateNo("QWERTYU");
      assertFalse(isValid(plateNoWithNoNumbers.getPlate()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void plateNoWithNoLetters()
  {
    try
    {
      PlateNo plateNoWithNoLetters = new PlateNo("1234567");
      assertFalse(isValid(plateNoWithNoLetters.getPlate()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }

  @Test public void plateWithMixedOrder()
  {
    try
    {
      PlateNo plateWithMixedOrder = new PlateNo("12SS123");
      assertFalse(isValid(plateWithMixedOrder.getPlate()));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("exception caught");
    }
  }
}