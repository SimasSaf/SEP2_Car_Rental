package shared.transferObjects.user;

import java.io.Serializable;

public class Ssn implements Serializable
{
  private String ssn;

  public Ssn(String ssn)
  {
    if (isValid(ssn))
    {
      this.ssn = ssn;
    }
    else {
      throw new IllegalArgumentException(
          "ssn contains characters and/or is not 10 digits long");
    }

  }

  public String getSsn()
  {
    return ssn;
  }

  private boolean isValid(String ssn)
  {
    return ssn.length() == 10 && ssn.matches("[0-9]+");
  }

  @Override public String toString()
  {
    return ssn;
  }
}
