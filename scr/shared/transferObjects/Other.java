package shared.transferObjects;


import java.sql.Timestamp;

public class Other extends Status
{
  private String description;

  public Other(int carId, Timestamp start, Timestamp end, String description)
  {
    super(carId, start, end);
    this.description=description;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }
}
