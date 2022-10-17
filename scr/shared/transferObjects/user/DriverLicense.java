package shared.transferObjects.user;

import java.io.Serializable;

public class DriverLicense implements Serializable
{
  private String licence;

  public DriverLicense(String licence)
  {
    if (isValid(licence))
    {
      this.licence = licence;
    }
    else
    {
      throw new IllegalArgumentException(
          "The license number contains characters and/or is not 8 digits long");
    }

  }

  public String getLicense()
  {
    return licence;
  }

  private boolean isValid(String license)
  {

    return license.length() == 8 && license.matches("[0-9]+");
  }
}
