package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: andrey
 * Date: 15.10.13
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "image_card";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "cards_list";
    public static final String CARD_ID = "_id";
    public static final String CARD_IMAGE_URI = "uri";
    public static final String CARD_OPPONENT_EMAIL = "email";
    public static final String CARD_SUBJECT = "subject";
    public static final String CARD_BODY = "body";

    public DBHelper(Context pContext) {
        super(pContext, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(buildCreateDBSqlString());
        } catch (Error pError) {
            pError.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String buildCreateDBSqlString() {
        return new StringBuffer("create table ").
                append(TABLE_NAME).append("(").
                append(CARD_ID).append(" integer primary key autoincrement, ").
                append(CARD_IMAGE_URI).append(" text, ").
                append(CARD_OPPONENT_EMAIL).append(" text, ").
                append(CARD_SUBJECT).append(" text, ").
                append(CARD_BODY).append(" text ").append(")").toString();

    }
}
