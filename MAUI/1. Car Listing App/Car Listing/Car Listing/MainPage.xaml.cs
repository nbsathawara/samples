﻿namespace Car_Listing;

public partial class MainPage : ContentPage
{  
	public MainPage(CarListViewModel carListViewModel)
	{
		InitializeComponent();
        BindingContext = carListViewModel;
    }
}


