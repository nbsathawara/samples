namespace FirstApp;

public partial class TestPage : ContentPage
{
	private int count = 0;
	public TestPage()
	{
		InitializeComponent();
	}

    void Button_Clicked(System.Object sender, System.EventArgs e)
    {
		count++;
		lbl.Text = "Count : " + count;
    }
}
