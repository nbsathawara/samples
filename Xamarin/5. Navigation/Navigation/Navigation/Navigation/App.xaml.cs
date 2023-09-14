using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Navigation
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();

            //MainPage = new NavigationPage(new MainPage()) {
            //    BarBackgroundColor = Color.Green,
            //    BarTextColor=Color.White
            //};

            //MainPage = new MyTabbedPage();

            //MainPage = new MyCarouselPage();

            //MainPage = new PopUpPage();

            MainPage = new NavigationPage(new ToolbarPage());
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}

