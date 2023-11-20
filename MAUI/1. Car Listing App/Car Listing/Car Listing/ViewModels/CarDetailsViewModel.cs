using System;
using CommunityToolkit.Mvvm.ComponentModel;

namespace Car_Listing
{
	[QueryProperty(nameof(Car),"Car")]
	public partial class CarDetailsViewModel : BaseViewModel
	{
		[ObservableProperty]
		 Car car; 
	}
}

