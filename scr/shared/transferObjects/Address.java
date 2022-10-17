package shared.transferObjects;

import java.io.Serializable;

public class Address implements Serializable
{
  private  int zip;
  private String city, country, street, number;

  public Address(String country, String city, String street,String number, int zip){

    this.city=city;
    this.country=country;
    this.street=street;
    this.zip=zip;
    this.number=number;
  }

  public int getZip()
  {
    return zip;
  }

  public void setZip(int zip)
  {
    this.zip = zip;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getStreet()
  {
    return street;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  public String getNumber()
  {
    return number;
  }

  public void setNumber(String number)
  {
    this.number = number;
  }

  @Override public String toString()
  {
    return "Address{" + "zip=" + zip + ", city='" + city + '\'' + ", country='"
        + country + '\'' + ", street='" + street + '\'' + ", number='" + number
        + '\'' + '}';
  }
}

