using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace UserInput
{	
	public partial class TablePage : ContentPage
	{	
		public TablePage ()
		{
			InitializeComponent ();
		}

        void ViewCell_Tapped(System.Object sender, System.EventArgs e)
        {
			var page = new ContactMethodPage();
			page.contactMethods.ItemSelected += (source, args) =>
			{
				contactMethod.Text = args.SelectedItem.ToString();
				Navigation.PopAsync();
			};


			Navigation.PushAsync(page);
        }
    }
}

