using System;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MVVM
{
    public class PageService : IPageService
    {

        public async Task<bool> DisplayAlert(string title, string msg, string ok, string cancel)
        {
            return await Application.Current.MainPage.DisplayAlert(title, msg, ok, cancel);
        }

        public async Task PushAsync(Page page)
        {
            await Application.Current.MainPage.Navigation.PushAsync(page);
        }
    }
}

