using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace Layouts
{	
	public partial class GridPage : ContentPage
	{	
		public GridPage ()
		{
			InitializeComponent ();
		}

        void Button_Clicked(System.Object sender, System.EventArgs e)
        {
			string btnText= (sender as Button).Text;
			int n;
			if (int.TryParse(btnText, out n))
				lblNumber.Text = lblNumber.Text + btnText;
			else
				lblNumber.Text = lblNumber.Text.Length>0 ? lblNumber.Text.Substring(0, lblNumber.Text.Length - 1)
					: "";
        }
    }
}

