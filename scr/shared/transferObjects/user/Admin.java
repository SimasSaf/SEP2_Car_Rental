package shared.transferObjects.user;

import shared.transferObjects.Address;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Admin extends User implements Serializable
{
  private Ssn ssn;


  public Admin(String fName, String lName, Address address, PhoneNo phoneNo,
      Password password, Email email,Date dob, Ssn ssn)
  {
    super(fName, lName, address, phoneNo, password, email, dob);
    this.ssn = ssn;
  }
  public Admin(int userID, String fName, String lName, Address address, PhoneNo phoneNo,
               Password password, Email email,Date dob, Ssn ssn)
  {
    super(userID, fName, lName, address, phoneNo, password, email, dob);
    this.ssn = ssn;
  }

  public String getSsn()
  {
    return ssn.getSsn();
  }


  @Override public String toString()
  {
    return super.toString() + " Admin{" + "ssn=" + ssn + ", empId="  + '}';
  }

  @Override public LoginType getType()
  {
    return LoginType.ADMIN;}
}
