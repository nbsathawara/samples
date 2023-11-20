using System;
using System.Collections.ObjectModel;
using System.Diagnostics;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace Car_Listing
{
    public partial class CarListViewModel : BaseViewModel
    {
        public ObservableCollection<Car> Cars { get; private set; } = new();

        [ObservableProperty]
        bool isRefreshing = false;

        [ObservableProperty]
        string make;
        [ObservableProperty]
        string model;
        [ObservableProperty]
        string vin;

        [ObservableProperty]
        string addUpdateText = "Add Car";

        public CarListViewModel()
        {
            Title = "Car List";
            GetCarList().Wait();
        }

        private void setData(Car car)
        {
            if (car == null)
            {
                Make = Model = Vin = "";
                AddUpdateText = "Add Car";
            }
            else
            {
                Make = car.Make;
                Model = car.Model;
                Vin = car.Vin;

                AddUpdateText = "Update Car";
            }
        }

        [RelayCommand]
        async Task GetCarList()
        {
            if (IsLoading) return;
            try
            {
                IsLoading = true;

                if (Cars.Any()) Cars.Clear();

                var cars = App.CarService.GetCars();
                cars.Reverse();

                foreach (var car in cars) Cars.Add(car);

            }
            catch (Exception ex)
            {
                Debug.WriteLine("Unable to get cars." + ex.Message);
                await Shell.Current.DisplayAlert("Error", "Unable to read cars.", "OK");
            }
            finally
            {
                IsLoading = false;
                IsRefreshing = false;
            }
        }

        [RelayCommand]
        async Task GoToCarDetails(int id)
        {
            if (id == 0)
                return;

            await Shell.Current.GoToAsync($"{nameof(CarDetailsPage)}?Id={id}", true);
        }

        [RelayCommand]
        async Task AddCar()
        {
            if (string.IsNullOrEmpty(Make) || string.IsNullOrEmpty(Model) || string.IsNullOrEmpty(Vin))
            {
                await Shell.Current.DisplayAlert("Message", "Invalid data...", "Ok");
                return;
            }

            if (currentCar != null)
            {
                await updateCarRecord();
                return;
            }

            var car = new Car
            {
                Make = Make,
                Model = Model,
                Vin = Vin
            };

            var result = App.CarService.AddCar(car);
            await Shell.Current.DisplayAlert("Info", App.CarService.StatusMsg, "Ok");
            if (result > 0)
            {
                await GetCarList();
                setData(null);
            }
        }


        [RelayCommand]
        async Task DeleteCar(int id)
        {
            var shouldDelte = await Shell.Current.DisplayAlert("Confirmation", "Are you sure?", "Yes", "No");
            if (!shouldDelte)
                return;
            if (id == 0)
            {
                await Shell.Current.DisplayAlert("Message", "Invalid data...", "Ok");
                return;
            }

            var result = App.CarService.DeleteCar(id);

            if (result == 0)
                await Shell.Current.DisplayAlert("Error", "Unabler to remove car.", "Ok");
            else
            {
                await Shell.Current.DisplayAlert("Success", "Car removed.", "Ok");
                await GetCarList();
            }
        }

        private Car currentCar;
        [RelayCommand]
        void UpdateCar(int id)
        {
            currentCar = App.CarService.GetCar(id);
            if (currentCar != null)
            {
                setData(currentCar);
            }
        }

        async Task updateCarRecord()
        {
            currentCar.Make = Make;
            currentCar.Model = Model;
            currentCar.Vin = Vin;

            var result = App.CarService.UpdateCar(currentCar);

            if (result == 0)
                await Shell.Current.DisplayAlert("Error", "Unabler to update car.", "Ok");
            else
            {
                await Shell.Current.DisplayAlert("Success", "Car updated.", "Ok");
                await GetCarList();
            }
            setData(null);
        }
    }
}

