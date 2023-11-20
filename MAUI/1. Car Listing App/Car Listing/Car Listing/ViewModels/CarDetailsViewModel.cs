using System;
using System.Web;
using CommunityToolkit.Mvvm.ComponentModel;

namespace Car_Listing
{
	[QueryProperty(nameof(Id),nameof(Id))]
	public partial class CarDetailsViewModel : BaseViewModel,IQueryAttributable
	{
		[ObservableProperty]
		 Car car;

        [ObservableProperty]
        int id;

        public void ApplyQueryAttributes(IDictionary<string, object> query)
        {
            Id = Convert.ToInt32(HttpUtility.UrlDecode(query[nameof(Id)].ToString()));
            Car = App.CarService.GetCar(Id);
        }
    }
}

