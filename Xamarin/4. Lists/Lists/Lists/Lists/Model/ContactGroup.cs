using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;

namespace Lists
{
    public class ContactGroup : ObservableCollection<Contact>
    {
        public string title { get; set; }
        public string shortTitle { get; set; }

        public ContactGroup(string title, string shortTitle)
        {
            this.title = title;
            this.shortTitle = shortTitle;
        }
    }
}

