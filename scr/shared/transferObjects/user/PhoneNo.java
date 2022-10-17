package shared.transferObjects.user;

import java.io.Serializable;

public class PhoneNo implements Serializable
{
  private String number;

  public PhoneNo(String number)
  {
    if (isValid(number))
    {
      this.number = number;
    }
    else
    {
      System.out.println(number);
      throw new IllegalArgumentException(

          "The phone number contains characters and/or is not 8 digits long");
    }
  }

  public String getNumber()
  {

    return number;
  }

  private boolean isValid(String number)
  {
    return number.length() == 8 && number.matches("[0-9]+");
  }

  public void setNumber(String number)
  {
    if (!isValid(number))
      throw new IllegalArgumentException(
          "The phone number contains characters and/or is not 8 digits long");
    this.number = number;
  }
}
