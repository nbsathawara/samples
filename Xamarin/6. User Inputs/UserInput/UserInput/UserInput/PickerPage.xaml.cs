using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace UserInput
{
    public partial class PickerPage : ContentPage
    {
        public PickerPage()
        {
            InitializeComponent();
        }

        void Picker_SelectedIndexChanged(System.Object sender, System.EventArgs e)
        {
            DisplayAlert("Selection", picker.Items[picker.SelectedIndex], "OK");
        }
    }
}

