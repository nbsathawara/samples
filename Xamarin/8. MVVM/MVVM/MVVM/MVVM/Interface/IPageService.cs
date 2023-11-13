using System;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace MVVM
{
	public interface IPageService
	{
		Task PushAsync(Page page);

		Task<bool> DisplayAlert(string title,string msg,string ok,string cancel);
	}
}

