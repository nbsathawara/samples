using System;
using System.Collections.Generic;
using System.Net.Http;
using Newtonsoft.Json;
using Xamarin.Forms;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Xml.Linq;

namespace DataAccess
{
    public partial class RestAPIPage : ContentPage
    {
        private string url = "https://jsonplaceholder.typicode.com/posts";
        private HttpClient _client = new HttpClient();

        private ObservableCollection<Album> _albums;

        public RestAPIPage()
        {
            InitializeComponent();
        }

        protected override async void OnAppearing()
        {
            base.OnAppearing();

            var content = await _client.GetStringAsync(url);
            var albums = JsonConvert.DeserializeObject<List<Album>>(content);

            _albums = new ObservableCollection<Album>(albums);
            listView.ItemsSource = _albums;
        }

        async void OnAdd(object sender, System.EventArgs e)
        {
            var album = new Album { Title = "Title " + DateTime.Now.Ticks };
            _albums.Insert(0, album);

            var content = JsonConvert.SerializeObject(album);
            await _client.PostAsync(url, new StringContent(content));
        }

        async void OnUpdate(object sender, System.EventArgs e)
        {
            var album = _albums[0];
            album.Title += "_updated";

            var content = JsonConvert.SerializeObject(album);
            await _client.PostAsync(url + "/" + album.Id, new StringContent(content));
        }

        async void OnDelete(object sender, System.EventArgs e)
        {
            var album = _albums[0];
            _albums.Remove(album);

            await _client.DeleteAsync(url + "/" + album.Id);
        }
    }

    public class Album : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        public int Id { get; set; } 
        public string Body { get; set; }

        private string _title;
        public string Title
        {
            get { return _title; }
            set
            {
                if (_title == value)
                    return;
                _title = value;
                OnPropertyChanged();
            }
        }

        private void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}

