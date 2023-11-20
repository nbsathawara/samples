using System;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using CommunityToolkit.Mvvm.ComponentModel;

namespace Car_Listing
{
    public partial class BaseViewModel : ObservableObject
    {
        [ObservableProperty]
        string title;
        [ObservableProperty]
        [NotifyPropertyChangedFor(nameof(isFinishedLoading))]
        bool isLoading;

        public bool isFinishedLoading => !IsLoading;
    }
}

