using System;
using System.Collections.ObjectModel;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace MVVM
{
    public class PlayListsViewModel : BaseViewModel
    {

        private PlaylistViewModel _selectedPlayList;
        private readonly IPageService _pageService;


        public ObservableCollection<PlaylistViewModel> Playlists { get; set; } = new ObservableCollection<PlaylistViewModel>();

        public PlaylistViewModel SelectedPlayList
        {
            get { return _selectedPlayList; }
            set
            {
                SetValue(ref _selectedPlayList, value);
            }
        }

        public ICommand AddPlaylistCommand { get; private set; }

        public ICommand SelectPlaylistCommand { get; private set; }

        public PlayListsViewModel(IPageService pageService)
        {
            _pageService = pageService;
            AddPlaylistCommand = new Command(AddPlayList);
            SelectPlaylistCommand = new Command<PlaylistViewModel>(async plvm=>await SelectPlayList(plvm));
        }

        private void AddPlayList()
        {
            var newPlaylist = "Playlist " + (Playlists.Count + 1);

            Playlists.Add(new PlaylistViewModel { Title = newPlaylist });
        }

        private async Task SelectPlayList(PlaylistViewModel playlist)
        {
            if (playlist == null)
                return;

            playlist.IsFavorite = !playlist.IsFavorite;

            SelectedPlayList = null;

            await _pageService.PushAsync(new PlaylistDetailPage(playlist));
        }
    }
}

