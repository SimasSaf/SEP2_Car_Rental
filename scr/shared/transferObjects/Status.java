package shared.transferObjects;


import java.io.Serializable;
import java.sql.Timestamp;

public abstract class Status implements Serializable
{
  private Timestamp start, end;
  private int carId;

  public Status(int carId,Timestamp start, Timestamp end){
    this.carId=carId;
  }

  public Timestamp getEnd()
  {
    return end;
  }

  public Timestamp getStart()
  {
    return start;
  }

  public void setStart(Timestamp start)
  {
    this.start = start;
  }

  public void setEnd(Timestamp end)
  {
    this.end = end;
  }

  public void setCarId(int carId)
  {
    this.carId = carId;
  }

  public int getCarId()
  {
    return carId;
  }

  @Override public String toString()
  {
    return "Status{" + "start=" + start + ", end=" + end + ", carId=" + carId
        + '}';
  }
}
