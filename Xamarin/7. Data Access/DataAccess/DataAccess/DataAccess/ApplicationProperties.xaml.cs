using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace DataAccess
{	
	public partial class ApplicationProperties : ContentPage
	{	
		public ApplicationProperties ()
		{
			InitializeComponent ();
			BindingContext = Application.Current;
		}
	}
}

