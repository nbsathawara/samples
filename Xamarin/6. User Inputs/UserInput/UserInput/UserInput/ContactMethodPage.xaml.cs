using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace UserInput
{
    public partial class ContactMethodPage : ContentPage
    {
        public ListView contactMethods { get { return listView; } }
        public ContactMethodPage()
        {
            InitializeComponent();

            listView.ItemsSource = new List<String> {
                "None","Email","SMS"
            };
        }

         
    }
}

