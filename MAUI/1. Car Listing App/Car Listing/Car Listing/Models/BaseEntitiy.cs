using SQLite;

namespace Car_Listing
{
    public abstract class BaseEntitiy
    {

        [PrimaryKey, AutoIncrement]
        public int Id { get; set; }

    }
}

