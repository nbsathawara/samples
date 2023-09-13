using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace Images
{	
	public partial class ImagePage : ContentPage
	{
		public ImagePage()
		{
			InitializeComponent();

			var imageSource = new UriImageSource
			{
				Uri = new Uri("https://picsum.photos/1920/1080")
			};
			imageSource.CachingEnabled = false;

			image.Source = imageSource;
		}
	}
}

