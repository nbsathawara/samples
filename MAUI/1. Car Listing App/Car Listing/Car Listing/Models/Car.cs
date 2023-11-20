using System;
using CommunityToolkit.Mvvm.ComponentModel;
using SQLite;

namespace Car_Listing
{
    [Table("Cars")]
    public class Car : BaseEntitiy
    { 
        public string Make { get; set; }

        public string Model { get; set; }

        [MaxLength(12), Unique]
        public string Vin { get; set; }
         
    }
}

