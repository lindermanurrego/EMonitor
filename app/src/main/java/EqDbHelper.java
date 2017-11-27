import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lul.emonitor.EqContract;

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
                     EqContract.EqColumns._ID + " INTEGER PRIMARI KEY AUTOINCREMENT, " +
                     EqContract.EqColumns.MAGNITUDE + " REAL NOT NULL, " +
                     EqContract.EqColumns.PLACE + " TEXT NOT NULL, " +
                     EqContract.EqColumns.LONGITUDE + " TEXT NOT NULL, " +
                     EqContract.EqColumns.LATITUDE + " TEXT NOT NULL, " +
                     EqContract.EqColumns.TIME + " TEXT NOT NULL, " +
                     ")";
             sqLiteDatabase.execSQL(EARTHQUAKES_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(sqLiteDatabase);

    }
}
