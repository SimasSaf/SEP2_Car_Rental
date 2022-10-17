package shared.transferObjects.user;

import client.views.utils.other.Error;

import java.io.Serializable;

public class Password implements Serializable
{
  private String password;

  public Password(String password) {

    if (isValid(password))
    {
      this.password = password;
    }
    else
      throw new IllegalArgumentException(Error.PASSWORD_TYPE.getMessage());
  }

  public String getPassword()
  {
    return password;
  }

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

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Password other))
    {
      return false;
    }
    else
    {
      return password.equals(other.password);
    }
  }

  @Override public String toString()
  {
    return "Password{" + "password='" + password + '\'' + '}';
  }
}

