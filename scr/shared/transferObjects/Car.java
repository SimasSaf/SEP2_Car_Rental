package shared.transferObjects;

import java.io.Serializable;
import java.util.List;

public class Car implements Serializable {

  private String make, model, type;
  private PlateNo plateNo;
  private int year, seats, range, km, carId;
  private double price;
  private String pickupPoint;

  public Car(String make, String model, int year, double price, String type,
             int range, int km, String pickupPoint, PlateNo plateNo)
  {
    this.make = make;
    this.model = model;
    this.year = year;
    this.price = price;
    this.type = type;
    this.range = range;
    this.km = km;
    this.plateNo = plateNo;
    this.pickupPoint = pickupPoint;
  }

  public Car(int  carId, String make, String model, int year, double price, String type,
      int range, int km, String pickupPoint, PlateNo plateNo)
  {
    this.carId = carId;
    this.make = make;
    this.model = model;
    this.year = year;
    this.price = price;
    this.type = type;
    this.range = range;
    this.km = km;
    this.plateNo = plateNo;
    this.pickupPoint = pickupPoint;
  }

  public String getMake()
  {
    return make;
  }

  public void setMake(String make)
  {
    this.make = make;
  }

  public String getModel()
  {
    return model;
  }

  public void setModel(String model)
  {
    this.model = model;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public PlateNo getPlateNo()
  {
    return plateNo;
  }

  public void setPlateNo(PlateNo plateNo)
  {
    this.plateNo = plateNo;
  }

  public int getYear()
  {
    return year;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getSeats()
  {
    return seats;
  }

  public void setSeats(int seats)
  {
    this.seats = seats;
  }

  public int getRange()
  {
    return range;
  }

  public void setRange(int range)
  {
    this.range = range;
  }

  public int getKm()
  {
    return km;
  }

  public void setKm(int km)
  {
    this.km = km;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public int getCarId()
  {
    return carId;
  }

  public void setCarId(int carId)
  {
    this.carId = carId;
  }

  public String getPickupPoint()
  {
    return pickupPoint;
  }

  public void setPickupPoint()
  {
    this.pickupPoint = pickupPoint;
  }

  @Override
  public String toString() {
    return "Car{" +
            "make='" + make + '\'' +
            ", model='" + model + '\'' +
            ", type='" + type + '\'' +
            ", plateNo=" + plateNo +
            ", year=" + year +
            ", seats=" + seats +
            ", range=" + range +
            ", km=" + km +
            ", carId=" + carId +
            ", price=" + price +
            ", pickupPoint=" + pickupPoint +
            '}';
  }
}
