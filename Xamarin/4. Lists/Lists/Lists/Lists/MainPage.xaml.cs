using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Lists
{
    public partial class MainPage : ContentPage
    {
        private ObservableCollection<ContactGroup> _contactGroups;
        public MainPage()
        {
            InitializeComponent();

            _contactGroups = getContacts();

            listView.ItemsSource = _contactGroups;
        }

        async void listview_ItemSelected(System.Object sender, Xamarin.Forms.SelectedItemChangedEventArgs e)
        {
            if (e.SelectedItem != null)
            {
                var contact = e.SelectedItem as Contact;
                await Navigation.PushAsync(new ContactDetailsPage(contact));
                listView.SelectedItem = null;
            }
        }

        void callContact(System.Object sender, System.EventArgs e)
        {
            var menuItem = sender as MenuItem;
            var contact = menuItem.CommandParameter as Contact;
            DisplayAlert("Call ", contact.name, "OK");
        }


        void removeContact(System.Object sender, System.EventArgs e)
        {
            var contact = (sender as MenuItem).CommandParameter as Contact;

            foreach (ContactGroup group in _contactGroups)
            {
                if (group.Contains(contact))
                    group.Remove(contact);
            }

        }

        void listView_Refreshing(System.Object sender, System.EventArgs e)
        {
            listView.ItemsSource = getContacts();
            listView.EndRefresh();
        }

        ObservableCollection<ContactGroup> getContacts(string searchText = null)
        {
            var _contactGroups = new ObservableCollection<ContactGroup> {

                new ContactGroup("N","N"){ new Contact{name="Nikhil Sathawara",status="Lets Talk!!!",imgUrl="https://picsum.photos/100/100"},},
                 new ContactGroup("H","H"){  new Contact{name="Hemangi Kadiya",imgUrl="https://picsum.photos/100/100"},
                },
                  new ContactGroup("Y","Y"){ new Contact{name="Yash Kadiya",imgUrl="https://picsum.photos/100/100"}},
                   new ContactGroup("D","D"){
                       new Contact{name="Devanshi Kadiya",status="Busy!!!!!",imgUrl="https://picsum.photos/100/100"},
                    new Contact{name="Dhriti Sathavara",imgUrl="https://picsum.photos/100/100"}},
            };

            if (string.IsNullOrEmpty(searchText))
                return _contactGroups;
            else
            {
                var _groups = new ObservableCollection<ContactGroup>();

                foreach (ContactGroup group in _contactGroups)
                {
                    foreach (Contact contact in group)
                    {

                        if (contact.name.Contains(searchText) && !_groups.Contains(group))
                        {
                            _groups.Add(group);
                        }
                    }
                }

                return _groups;
            }
        }

        void SearchBar_TextChanged(System.Object sender, Xamarin.Forms.TextChangedEventArgs e)
        {
            listView.ItemsSource = getContacts(e.NewTextValue);
        }
    }
}

