package shared.transferObjects;

import java.sql.Timestamp;

public class Cleaning extends Status
{

  public Cleaning(int carId, Timestamp start, Timestamp end)
  {
    super(carId, start, end);
  }
}
