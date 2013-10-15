package model;

/**
 * User: Андрей
 * Date: 14.10.13
 */
public class UserImageCard {

    private String mImageUri;
    private String mEmail;
    private String mSubject;
    private String mBody;
    private long mDataBaseId;


    public UserImageCard(UserImageCardBuilder pBuilder){
        mImageUri = pBuilder.mImageUri;
        mEmail = pBuilder.mEmail;
        mSubject = pBuilder.mSubject;
        mBody = pBuilder.mBody;
        mDataBaseId = pBuilder.mCardRecordId;
    }

    public String getImageUri() {
        return mImageUri;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getBody() {
        return mBody;
    }

    public long getDataBaseId() {
        return mDataBaseId;
    }
}
