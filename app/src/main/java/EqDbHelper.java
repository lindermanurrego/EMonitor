import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usre on 27/11/2017.
 */

public class EqDbHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "earthquakes.db";
    private static  final int DATABASE_VERSION = 1;

    public EqDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
             String EARTHQUAKES_DATABASE = "CREATE TABLE " + DATABASE_NAME + "("+
                     "_id" + " INTEGER PRIMARI KEY AUTOINCREMENT, " +
                     "magnitude" + " REAL NOT NULL, " +
                     "place" + " TEXT NOT NULL, " +
                     "longitude" + " TEXT NOT NULL, " +
                     "latitude" + " TEXT NOT NULL, " +
                     "time" + " TEXT NOT NULL, " +
                     ")";
             sqLiteDatabase.execSQL(EARTHQUAKES_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
