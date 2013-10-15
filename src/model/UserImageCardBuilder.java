package model;

import android.content.Context;
import android.widget.Toast;
import com.kozhurov.R;
import utils.FieldValidator;

/**
 * User: Андрей
 * Date: 14.10.13
 */
public class UserImageCardBuilder {

    public long mCardRecordId;

    public String mImageUri;

    public String mEmail;

    public String mSubject;

    public String mBody;

    public UserImageCard buildUserImageCard(Context pContext) {

        if (FieldValidator.isFieldsEmpty(mImageUri, mEmail, mSubject, mBody)) {
            Toast.makeText(pContext, R.string.empty_fields_alert, Toast.LENGTH_SHORT).show();
            return null;
        }

        if (!FieldValidator.isValidEmail(mEmail)){
            Toast.makeText(pContext, R.string.invalid_email_alert, Toast.LENGTH_SHORT).show();
            return null;
        }

        return buildCard();

    }

    public UserImageCard buildUserImageCard() {
        return buildCard();
    }


    private UserImageCard buildCard(){
        return new UserImageCard(this);
    }
}
