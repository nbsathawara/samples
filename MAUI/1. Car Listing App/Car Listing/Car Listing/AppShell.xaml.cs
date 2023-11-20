namespace Car_Listing;

public partial class AppShell : Shell
{
    public AppShell()
    {
        InitializeComponent();

        Routing.RegisterRoute(nameof(CarDetailsPage), typeof(CarDetailsPage));
    }
}

