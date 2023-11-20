using System;
using System.Collections.ObjectModel;
using System.Diagnostics;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace Car_Listing
{
    public partial class CarListViewModel : BaseViewModel
    {
        private readonly CarService carService;
        public ObservableCollection<Car> Cars { get; private set; } = new();

        [ObservableProperty]
        bool isRefreshing;

        public CarListViewModel(CarService carService)
        {
            Title = "Car List";
            this.carService = carService;
        }

        [RelayCommand]
        async Task GetCarList()
        {
            if (IsLoading) return;
            try
            {
                IsLoading = true;

                if (Cars.Any()) Cars.Clear();

                var cars = carService.GetCars();

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
        async Task GoToCarDetails(Car car)
        {
            if (car == null)
                return;

            var data = new Dictionary<string, object>
               {
                {nameof(Car),car}
               };
            await Shell.Current.GoToAsync(nameof(CarDetailsPage), true, data);
        }
    }
}

