package app.com.example.android.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by anthony on 9/3/14.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "weather.db";

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create tables
        StringBuilder SQL_CREATE_WEATHER_TABLE_STRBLDR = new StringBuilder("CREATE TABLE ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.TABLE_NAME);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" (");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry._ID);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" INTEGER NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_DATE);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" TEXT NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" TEXT NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" INTEGER NOT NULL, ");

                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" REAL NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" REAL NOT NULL, ");

                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" REAL NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" REAL NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" REAL NOT NULL, ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_DEGREES);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" REAL NOT NULL, ");

                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" FOREIGN KEY (");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(") REFERENCES ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.LocationEntry.TABLE_NAME);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" (");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.LocationEntry._ID);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append("), ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(" UNIQUE (");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_DATE);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(", ");
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
                SQL_CREATE_WEATHER_TABLE_STRBLDR.append(") ON CONFLICT REPLACE);");

        StringBuilder SQL_CREATE_LOCATION_TABLE_STRBLDR = new StringBuilder("CREATE TABLE ");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry.TABLE_NAME);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" (");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry._ID);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" INTEGER PRIMARY KEY,");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" TEXT UNIQUE NOT NULL, ");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry.COLUMN_CITY);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" TEXT UNIQUE NOT NULL, ");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry.COLUMN_LATITUDE);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" REAL NOT NULL, ");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry.COLUMN_LONGITUDE);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" REAL NOT NULL, ");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" UNIQUE (");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(") ON CONFLICT IGNORE");
                SQL_CREATE_LOCATION_TABLE_STRBLDR.append(" );");

        Log.v("CREATE_LOCATION_TABLE_STRING", SQL_CREATE_LOCATION_TABLE_STRBLDR.toString());

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE_STRBLDR.toString());
        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE_STRBLDR.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        StringBuilder dropLoc = new StringBuilder("DROP TABLE IF EXISTS ");
        dropLoc.append(WeatherContract.LocationEntry.TABLE_NAME);
        sqLiteDatabase.execSQL(dropLoc.toString());
        StringBuilder dropWeather = new StringBuilder("DROP TABLE IF EXISTS ");
        dropWeather.append(WeatherContract.WeatherEntry.TABLE_NAME);
        sqLiteDatabase.execSQL(dropWeather.toString());
        onCreate(sqLiteDatabase);
    }
}
