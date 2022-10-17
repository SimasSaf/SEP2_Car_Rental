package shared.transferObjects;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reservation implements Serializable
{
  private Timestamp start, end;
  private int reservationId, customerId, carId;
  private int kmStart, kmEnd;

  public Reservation(Timestamp start, Timestamp end, int kmStart,
      int kmEnd, int customerId, int carId)
  {
    if (start.before(end) && kmStart < kmEnd)
    {
      this.kmStart= kmStart;
      this.kmEnd = kmEnd;
      this.start = start;
      this.end = end;
      this.customerId = customerId;
      this.carId = carId;
    }
    else throw new IllegalArgumentException(
        "end date is before start or km start is bigger than km end");
  }

  public Reservation(int reservationId, Timestamp start, Timestamp end, int kmStart,
      int kmEnd, int customerId, int carId)
  {
    if (start.before(end) && kmStart < kmEnd)
    {
      this.reservationId = reservationId;
      this.kmStart= kmStart;
      this.kmEnd = kmEnd;
      this.start = start;
      this.end = end;
      this.customerId = customerId;
      this.carId = carId;
    }
    else throw new IllegalArgumentException(
        "end date is before start or km start is bigger than km end");
  }

  public Timestamp getStart()
  {
    return start;
  }

  public void setStart(Timestamp start)
  {
    this.start = start;
  }

  public Timestamp getEnd()
  {
    return end;
  }

  public void setEnd(Timestamp end)
  {
    this.end = end;
  }

  public int getReservationId()
  {
    return reservationId;
  }

  public void setReservationId(int reservationId)
  {
    this.reservationId = reservationId;
  }

  public int getCustomerId()
  {
    return customerId;
  }

  public void setCustomerId(int customerId)
  {
    this.customerId = customerId;
  }

  public int getCarId()
  {
    return carId;
  }

  public void setCarId(int carId)
  {
    this.carId = carId;
  }

  public int getKmStart()
  {
    return kmStart;
  }

  public void setKmStart(int kmStart)
  {
    this.kmStart = kmStart;
  }

  public int getKmEnd()
  {
    return kmEnd;
  }

  public void setKmEnd(int kmEnd)
  {
    this.kmEnd = kmEnd;
  }

  @Override
  public String toString() {
    return "Reservation{" +
            "start=" + start +
            ", end=" + end +
            ", reservationId=" + reservationId +
            ", customerId=" + customerId +
            ", carId=" + carId +
            ", kmStart=" + kmStart +
            ", kmEnd=" + kmEnd +
            '}';
  }
}
