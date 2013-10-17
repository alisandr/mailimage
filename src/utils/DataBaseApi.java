package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.UserImageCard;
import model.UserImageCardBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: andrey
 * Date: 15.10.13
 */
public class DataBaseApi {

    private static DataBaseApi sApi;

    private DBHelper mHelper;

    private SQLiteDatabase mDatabase;

    private DataBaseApi(Context pContext) {
        mHelper = new DBHelper(pContext);
    }

    public static DataBaseApi getDataBaseApi(Context pContext) {
        if (sApi == null) {
            sApi = new DataBaseApi(pContext);
        }
        return sApi;
    }

    public void deleteAllCards() {
        mDatabase = mHelper.getWritableDatabase();
        mDatabase.delete(DBHelper.TABLE_NAME, null, null);
        mDatabase.close();
    }

    public List<UserImageCard> getAllCards() {
        mDatabase = mHelper.getReadableDatabase();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        mDatabase.close();
        return unpackingCursorToUserCards(cursor);
    }

    public void deleteTargetCard(long pCardId) {
        String where = getWhereString(pCardId);
        mDatabase = mHelper.getWritableDatabase();
        mDatabase.delete(DBHelper.TABLE_NAME, where, null);
        mDatabase.close();
    }

    private String getWhereString(long pCardId) {
        return new StringBuilder(DBHelper.CARD_ID).append(" = ").append(pCardId).toString();
    }

    public long insertNewCard(UserImageCard pImageCard) {
        ContentValues cv = packingCardToCv(pImageCard);
        mDatabase = mHelper.getWritableDatabase();
        return mDatabase.insertWithOnConflict(DBHelper.TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public UserImageCard getTargetCard(long recordId) {
        mDatabase = mHelper.getReadableDatabase();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_NAME, null, getWhereString(recordId), null, null, null, null);
        List<UserImageCard> result = unpackingCursorToUserCards(cursor);
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    private ContentValues packingCardToCv(UserImageCard pImageCard) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.CARD_IMAGE_URI, pImageCard.getImageUri());
        values.put(DBHelper.CARD_OPPONENT_EMAIL, pImageCard.getEmail());
        values.put(DBHelper.CARD_SUBJECT, pImageCard.getSubject());
        values.put(DBHelper.CARD_BODY, pImageCard.getBody());
        return values;
    }

    private List<UserImageCard> unpackingCursorToUserCards(Cursor pCursor) {
        List<UserImageCard> cards = new ArrayList<UserImageCard>();
        if (!pCursor.moveToFirst()) {
            return cards;
        }

        int cardId = pCursor.getColumnIndex(DBHelper.CARD_ID);
        int imageUri = pCursor.getColumnIndex(DBHelper.CARD_IMAGE_URI);
        int cardEmail = pCursor.getColumnIndex(DBHelper.CARD_OPPONENT_EMAIL);
        int cardSubject = pCursor.getColumnIndex(DBHelper.CARD_SUBJECT);
        int cardBody = pCursor.getColumnIndex(DBHelper.CARD_BODY);

        do {
            UserImageCardBuilder builder = new UserImageCardBuilder();
            builder.mCardRecordId = pCursor.getLong(cardId);
            builder.mImageUri = pCursor.getString(imageUri);
            builder.mEmail = pCursor.getString(cardEmail);
            builder.mSubject = pCursor.getString(cardSubject);
            builder.mBody = pCursor.getString(cardBody);
            cards.add(builder.buildUserImageCard());
        } while (pCursor.moveToNext());
        return cards;
    }
}
