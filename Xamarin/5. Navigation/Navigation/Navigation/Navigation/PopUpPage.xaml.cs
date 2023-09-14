using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace Navigation
{
    public partial class PopUpPage : ContentPage
    {
        public PopUpPage()
        {
            InitializeComponent();
        }

        async void Button_Clicked(System.Object sender, System.EventArgs e)
        {
            var response = await DisplayActionSheet("Title", "Cancel", "Delete", "Option 1", "Option 2", "Option 3");
            DisplayAlert("Response", "You have selected..." + response, "OK");

        }
    }
}

