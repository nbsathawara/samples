using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace Lists
{	
	public partial class ContactDetailsPage : ContentPage
	{	
		public ContactDetailsPage (Contact contact)
		{
			if (contact == null)
				throw new ArgumentNullException();
			contact.imgUrl = "https://picsum.photos/300/300";

            BindingContext = contact;

			InitializeComponent ();
		}
	}
}

