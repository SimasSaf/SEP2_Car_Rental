package shared.transferObjects.user;

import shared.transferObjects.Address;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Customer extends User implements Serializable {
  private DriverLicense driverLicenseNo;

  public Customer(String fName, String lName, Address address, PhoneNo phoneNo,
      Password password, Email email,Date dob, DriverLicense driverLicenseNo)
  {
    super(fName, lName, address, phoneNo, password, email,dob);
    this.driverLicenseNo = driverLicenseNo;
  }

  public Customer(int userID, String fName, String lName, Address address, PhoneNo phoneNo,
                  Password password, Email email,Date dob, DriverLicense driverLicenseNo)
  {
    super(userID, fName, lName, address, phoneNo, password, email,dob);
    this.driverLicenseNo = driverLicenseNo;
  }



  public String getDriverLicenseNo()
  {
    return driverLicenseNo.getLicense();
  }

  @Override
  public String toString() {
    return super.toString() + "Customer{" +
            "driverLicenseNo=" + driverLicenseNo +
            '}';
  }

  @Override public LoginType getType()
  {
    return LoginType.CUSTOMER;
  }
}
