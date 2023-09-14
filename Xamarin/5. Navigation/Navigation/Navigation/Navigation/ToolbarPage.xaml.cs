using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace Navigation
{
    public partial class ToolbarPage : ContentPage
    {
        public ToolbarPage()
        {
            InitializeComponent();
        }

        void ToolbarItem_Clicked(System.Object sender, System.EventArgs e)
        {
            DisplayAlert("title", "Msg", "OK");
        }
    }
}

