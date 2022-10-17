package shared.transferObjects.user;

import shared.transferObjects.Address;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class FrontDesk extends User implements Serializable
{

  private Ssn ssn;
  private Address workAddress;


  public FrontDesk(String fName, String lName, Address personalAddress, PhoneNo phoneNo,
      Password password, Email email,Date dob, Ssn ssn, Address workAddress)
  {
    super(fName, lName, personalAddress, phoneNo, password, email, dob);
    this.ssn = ssn;
    this.workAddress=workAddress;
  }

  public FrontDesk(int userID, String fName, String lName, Address personalAddress, PhoneNo phoneNo,
                   Password password, Email email,Date dob, Ssn ssn, Address workAddress)
  {
    super(userID, fName, lName, personalAddress, phoneNo, password, email, dob);
    this.ssn = ssn;
    this.workAddress=workAddress;
  }


  public String getSsn()
  {
    return ssn.getSsn();
  }


  @Override public String toString()
  {
    return "FrontDesk{" + "empId=" +  ", ssn='" + ssn + '\'' + '}';
  }

  @Override public LoginType getType()
  {
    return LoginType.FRONT_DESK;
  }

  public Address getWorkAddress(){
    return workAddress;
  }
}

