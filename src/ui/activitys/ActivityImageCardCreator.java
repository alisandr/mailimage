package ui.activitys;

import android.content.Intent;
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

    private UserImageCard mUserImageCard;

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
        mUserImageCard = DataBaseApi.getDataBaseApi(getApplicationContext()).getTargetCard(recordId);
        if (mUserImageCard != null) {
            unpackingCardToViews();
        } else {
            mUserImageCard = new UserImageCardBuilder().buildNoneNullImageCard();
        }

    }

    private void unpackingCardToViews() {
        String path = mUserImageCard.getImageUri();
        mCardImageView.setImageDrawable(ImageHelper.getDrawableFromUri(path));
        mMailEditText.setText(mUserImageCard.getEmail());
        mSubjectEditText.setText(mUserImageCard.getSubject());
        mBodyEditText.setText(mUserImageCard.getBody());
    }

    private void saveCardExecute() {
        mUserImageCard = reviewFieldsAndRunBuilder();

        if (mUserImageCard == null) {
            return;
        }

        onBackPressed();
    }

    private UserImageCard reviewFieldsAndRunBuilder() {
        UserImageCardBuilder cardBuilder = new UserImageCardBuilder();
        cardBuilder.mEmail = mMailEditText.getText().toString();
        cardBuilder.mSubject = mSubjectEditText.getText().toString();
        cardBuilder.mBody = mBodyEditText.getText().toString();
        return cardBuilder.buildUserImageCard(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

       saveCardInDB();
    }

    private void saveCardInDB(){
        DataBaseApi.getDataBaseApi(getApplicationContext()).insertNewCard(mUserImageCard);
    }

    private void shareCard(){
        private Intent buildShareIntent() {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(INTENT_TYPE);
            intent.putExtra(Intent.EXTRA_STREAM, getSharedImageUri());
            return intent;
        }
    }


    private void sharePhoto() {
        Intent intent = buildShareIntent();
        Intent.createChooser(intent, "");
        startActivity(intent);
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View v) {
           saveCardInDB();

        }
    }
}
