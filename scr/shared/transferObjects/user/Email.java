package shared.transferObjects.user;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Email implements Serializable {
  private String email;
  public Email(String email) {
    if (isValid(email)) {
      this.email = email;
    }
    else {
      throw new IllegalArgumentException(
          "email does not follow   the proper format");
    }
  }

  private boolean isValid(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
        + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    return pat.matcher(email).matches();
  }

  public String getEmail()
  {
    return email;
  }
}
