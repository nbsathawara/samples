using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace Images
{	
	public partial class PlatformImagePage : ContentPage
	{	
		public PlatformImagePage ()
		{
			InitializeComponent ();

			btn.ImageSource = new FileImageSource { File = "clock" };
		}
	}
}

