using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Advanced
{
    public partial class App : Application
    {
        public App ()
        {
            InitializeComponent();

            MainPage = new ResourceDictionaryPage();
        }

        protected override void OnStart ()
        {
        }

        protected override void OnSleep ()
        {
        }

        protected override void OnResume ()
        {
        }
    }
}

