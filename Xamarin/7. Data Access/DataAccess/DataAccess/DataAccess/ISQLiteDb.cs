using SQLite;

namespace DataAccess
{
    public interface ISQLiteDb
    {
        SQLiteAsyncConnection GetConnection();
    }
}

