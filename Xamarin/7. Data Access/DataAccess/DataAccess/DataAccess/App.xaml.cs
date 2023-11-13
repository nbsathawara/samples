using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace DataAccess
{
    public partial class App : Application
    {
        private const String keyTitle = "title";
        private const String keyTitleEnabled = "titleEnabled";
        public App()
        {
            InitializeComponent();

            MainPage = new RestAPIPage();
            //MainPage = new SQLitePage();
            //MainPage = new ApplicationProperties();
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

        public String Title
        {
            get
            {
                if (Properties.ContainsKey(keyTitle))
                   return Properties[keyTitle].ToString();
                return "Default";
            }
            set
            {
                Properties[keyTitle] = value;
            }
        }

        public bool TitleEnabled
        {
            get
            {
                if (Properties.ContainsKey(keyTitleEnabled))
                   return (bool)  Properties[keyTitleEnabled];
                return false;
            }
            set
            {
                Properties[keyTitleEnabled] = value;
            }
        }
    }
}

