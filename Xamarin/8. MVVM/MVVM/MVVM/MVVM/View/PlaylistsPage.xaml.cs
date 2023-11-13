using System.Collections.ObjectModel;
using Xamarin.Forms;

namespace MVVM
{
    public partial class PlaylistsPage : ContentPage
    {
        private PlayListsViewModel ViewModel {
            get { return BindingContext as PlayListsViewModel; }
            set { BindingContext = value; }
        }

        public PlaylistsPage()
        {
            ViewModel = new PlayListsViewModel(new PageService());
            InitializeComponent();
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();
        }

        void OnPlaylistSelected(object sender, Xamarin.Forms.SelectedItemChangedEventArgs e)
        {
            ViewModel.SelectPlaylistCommand.Execute(e.SelectedItem as PlaylistViewModel);
        }
    }
}
