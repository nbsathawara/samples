using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace Layouts
{
    public partial class MainPage : ContentPage
    {
        public ICommand NavigateCommand { private set; get; }

        public MainPage()
        {
            InitializeComponent();

            NavigateCommand = new Command<Type>(
                (Type parameter) =>
                {
                    openPage(parameter);
                }
                );

            BindingContext = this;
        }

       async void openPage(Type pageType)
        {
            Page page = (Page)Activator.CreateInstance(pageType);
            await Navigation.PushAsync(page);
        }

    }
}

