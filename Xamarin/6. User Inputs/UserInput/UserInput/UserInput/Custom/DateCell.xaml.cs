using System;
using System.Collections.Generic;

using Xamarin.Forms;

namespace UserInput
{
    public partial class DateCell : ViewCell
    {
        public static readonly BindableProperty lblProperty =
            BindableProperty.Create("lbl", typeof(string), typeof(DateCell));

        public string lbl
        {
            get
            {
                return (string)GetValue(lblProperty);
            }

            set
            {
                SetValue(lblProperty, value);
            }
        }

        public DateCell()
        {
            InitializeComponent();

            BindingContext = this;
        }
    }
}

