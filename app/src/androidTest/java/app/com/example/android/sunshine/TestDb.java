package app.com.example.android.sunshine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import app.com.example.android.sunshine.data.WeatherContract;
import app.com.example.android.sunshine.data.WeatherDbHelper;

/**
 * Created by anthony on 9/3/14.
 */
public class TestDb extends AndroidTestCase {
    private final String LOG_TAG = TestDb.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb() {
        String testName = "North Pole";
        String testLocation = "99705";
        Double testLatitude = 64.772;
        Double testLongitude = -147.355;

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeatherContract.LocationEntry.COLUMN_CITY, testName);
        values.put(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING, testLocation);
        values.put(WeatherContract.LocationEntry.COLUMN_LATITUDE, testLatitude);
        values.put(WeatherContract.LocationEntry.COLUMN_LONGITUDE, testLongitude);

        long locationRowId;
        locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, values);

        assertTrue(locationRowId != -1);
        Log.d(LOG_TAG, "New row id: " + locationRowId);

        String[] columns = {
                WeatherContract.LocationEntry._ID,
                WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING,
                WeatherContract.LocationEntry.COLUMN_CITY,
                WeatherContract.LocationEntry.COLUMN_LATITUDE,
                WeatherContract.LocationEntry.COLUMN_LONGITUDE };

        Cursor cursor = db.query(WeatherContract.LocationEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            int locationIndex = cursor.getColumnIndex((WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING));
            String location = cursor.getString(locationIndex);

            int nameIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_CITY);
            String name = cursor.getString(nameIndex);

            int latIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_LATITUDE);
            double latitude = cursor.getDouble(latIndex);

            int longIndex = cursor.getColumnIndex(WeatherContract.LocationEntry.COLUMN_LONGITUDE);
            double longitude = cursor.getDouble(longIndex);


        } else {
            fail("No values returned:(");
        }

    }

}
