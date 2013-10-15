package ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.kozhurov.R;
import model.UserImageCard;
import model.UserImageCardBuilder;

/**
 * User: andrey
 * Date: 15.10.13
 */
public class ActivityImageCardCreator extends ActivityBase {

    private EditText mMailEditText;
    private EditText mSubjectEditText;
    private EditText mBodyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_imaged_card);
        initViews();
    }

    private void initViews(){
        mMailEditText = (EditText) findViewById(R.id.create_email_edit_text);
        mSubjectEditText = (EditText) findViewById(R.id.create_subject_edit_text);
        mBodyEditText = (EditText) findViewById(R.id.create_subject_edit_text);

        findViewById(R.id.create_share_button).setOnClickListener(new Clicker());
    }

    private void saveCardExecute(){
        UserImageCard imageCard = reviewFieldsAndRunBuilder();

        if (imageCard == null){
            return;
        }



    }

    private UserImageCard reviewFieldsAndRunBuilder(){
        UserImageCardBuilder cardBuilder = new UserImageCardBuilder();
        cardBuilder.mEmail = mMailEditText.getText().toString();
        cardBuilder.mSubject = mSubjectEditText.getText().toString();
        cardBuilder.mBody = mBodyEditText.getText().toString();
        return cardBuilder.buildUserImageCard(getApplicationContext());
    }

    private long insertCardInDB(UserImageCard  pImageCard){

    }

    private class Clicker implements View.OnClickListener{

        @Override
        public void onClick(View v) {


        }
    }
}
