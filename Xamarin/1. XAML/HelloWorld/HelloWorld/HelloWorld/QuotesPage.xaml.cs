using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace HelloWorld
{
    public partial class QuotesPage : ContentPage
    {
        Random random = new Random();
        public QuotesPage()
        {
            InitializeComponent(); 
        }

        void Button_Clicked(System.Object sender, System.EventArgs e)
        {
            lblQuote.Text = "Quote " + random.Next(101);
        }
    }
}

