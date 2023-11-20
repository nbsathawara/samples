using System;
using SQLite;

namespace Car_Listing
{
    public class CarService
    {
        private SQLiteConnection connection;
        string _dbPath;
        int result = 0;
        public string StatusMsg = "";

        public CarService(string dbPath)
        {
            _dbPath = dbPath;
        }

        private void Init()
        {
            if (connection != null) return;

            connection = new SQLiteConnection(_dbPath);
            connection.CreateTable<Car>();
        }

        public List<Car> GetCars()
        {
            try
            {
                Init();
                return connection.Table<Car>().ToList();
            }
            catch (Exception ex)
            {
                StatusMsg = "Failed to read Cars.";
            }

            return new List<Car>();

        }

        public Car GetCar(int id)
        {
            Car car = null;
            try
            {
                Init();
                car = connection.Table<Car>().FirstOrDefault(q => q.Id == id);
            }
            catch (Exception ex)
            {
                StatusMsg = "Failed to retrive car.";
            }

            return car;

        }

        public int AddCar(Car car)
        {
            try
            {
                Init();

                if (car == null)
                    throw new Exception("Invalid Car.");

                result = connection.Insert(car);
                StatusMsg = result == 0 ? "Insert failed." : "Car Added Succesfully.";
            }
            catch (Exception ex)
            {
                StatusMsg = "Failed to add Car.";
            }
            return result;
        }

        public int DeleteCar(int id)
        {
            try
            {
                Init();

                result = connection.Table<Car>().Delete(q => q.Id == id);
            }
            catch (Exception ex)
            {
                StatusMsg = "Failed to delete car.";
            }
            return result;
        }

        public int UpdateCar(Car car)
        {
            try
            {
                Init();

                result = connection.Update(car);
                StatusMsg = result == 0 ? "Update Failed." : "Updated Succesfully.";
            }
            catch (Exception ex)
            {
                StatusMsg = "Failed to delete car.";
            }
            return result;
        }
    }
}

