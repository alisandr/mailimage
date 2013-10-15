package ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.kozhurov.R;
import model.UserImageCard;
import model.UserImageCardBuilder;
import ui.BundleConstants;
import utils.DataBaseApi;
import utils.ImageHelper;

/**
 * User: andrey
 * Date: 15.10.13
 */
public class ActivityImageCardCreator extends ActivityBase {

    private static final long NO_DB_RECORD_ID = -1;

    private EditText mMailEditText;

    private EditText mSubjectEditText;

    private EditText mBodyEditText;

    private ImageView mCardImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_imaged_card);
        initViews();
        initVariables();
    }

    private void initViews() {
        mCardImageView = (ImageView) findViewById(R.id.new_card_image);
        mMailEditText = (EditText) findViewById(R.id.new_card_create_email_edit_text);
        mSubjectEditText = (EditText) findViewById(R.id.new_card_create_subject_edit_text);
        mBodyEditText = (EditText) findViewById(R.id.new_card_create_subject_edit_text);

        findViewById(R.id.new_card_create_share_button).setOnClickListener(new Clicker());
    }

    private void initVariables() {

        long recordId = getIntent().getLongExtra(BundleConstants.CARD_RECORD_ID.getValue(), NO_DB_RECORD_ID);
        UserImageCard imageCard = DataBaseApi.getDataBaseApi(getApplicationContext()).getTargetCard(recordId);
        if (imageCard != null) {
            unpackingCardToViews(imageCard);
        }

    }

    private void unpackingCardToViews(UserImageCard pImageCard) {
        String path = pImageCard.getImageUri();
        mCardImageView.setImageDrawable(ImageHelper.getDrawableFromUri(path));
        mMailEditText.setText(pImageCard.getEmail());
        mSubjectEditText.setText(pImageCard.getSubject());
        mBodyEditText.setText(pImageCard.getBody());
    }

    private void saveCardExecute() {
        UserImageCard imageCard = reviewFieldsAndRunBuilder();

        if (imageCard == null) {
            return;
        }
    }

    private UserImageCard reviewFieldsAndRunBuilder() {
        UserImageCardBuilder cardBuilder = new UserImageCardBuilder();
        cardBuilder.mEmail = mMailEditText.getText().toString();
        cardBuilder.mSubject = mSubjectEditText.getText().toString();
        cardBuilder.mBody = mBodyEditText.getText().toString();
        return cardBuilder.buildUserImageCard(getApplicationContext());
    }

    private long insertCardInDB(UserImageCard pImageCard) {
        return 0;
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View v) {


        }
    }
}
