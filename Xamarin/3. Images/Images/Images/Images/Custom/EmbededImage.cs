using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Images
{
    [ContentProperty("ResourceId")]
    public class EmbededImage : IMarkupExtension
    {
        public string ResourceId { get; set; }
        public object ProvideValue(IServiceProvider serviceProvider)
        {
            if (string.IsNullOrEmpty(ResourceId))
                return null;
            return ImageSource.FromResource("Images.Resources.Images.background.jpg");
        }
    }
}

