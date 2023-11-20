using System;
namespace Car_Listing
{
    public class CarService
    {
        public List<Car> GetCars()
        {
            return new List<Car>() {
                new Car{
                    Id=1,Make="Honda",Model="Fit",Vin="ABC123"
                },  new Car{
                    Id=2,Make="Hyndai",Model="i10",Vin="ABC123"
                },  new Car{
                    Id=3,Make="Hyndai",Model="Baleno",Vin="ABC123"
                },  new Car{
                    Id=4,Make="Suzuki",Model="Creta",Vin="ABC123"
                },  new Car{
                    Id=5,Make="Maruti",Model="800",Vin="ABC123"
                },  new Car{
                    Id=6,Make="Honda",Model="Fit",Vin="ABC123"
                },  new Car{
                    Id=7,Make="Honda",Model="Fit",Vin="ABC123"
                },  new Car{
                    Id=8,Make="Honda",Model="Fit",Vin="ABC123"
                }
            };
        }
    }
}

