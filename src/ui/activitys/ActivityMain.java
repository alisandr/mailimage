package ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.kozhurov.R;
import model.UserImageCard;
import ui.BundleConstants;
import ui.adapters.AdapterUserCard;
import ui.dialogs.DialogClean;
import ui.dialogs.DialogConfirm;
import ui.dialogs.UserChoiceListener;
import utils.DataBaseApi;

import java.util.List;

public class ActivityMain extends ActivityBase {

    private long mCardIdForDelete;

    private ListView mCardListView;

    private UserChoiceListener mChoiceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initVariables();
    }

    private void initViews() {
        View.OnClickListener clickListener = new Clicker();
        findViewById(R.id.main_add_new_card_button).setOnClickListener(clickListener);
        findViewById(R.id.main_remove_all_card_button).setOnClickListener(clickListener);

        mCardListView = (ListView) findViewById(R.id.main_card_list_view);
        mCardListView.setOnItemClickListener(new ItemClicker());
        mCardListView.setOnItemLongClickListener(new ItemLongClicker());
        mCardListView.setEmptyView(findViewById(R.id.main_empty_list_text));

    }

    private void initVariables() {
        mCardIdForDelete = -1;
        mChoiceListener = new DialogCallBack();
        List<UserImageCard> allCards = DataBaseApi.getDataBaseApi(getApplicationContext()).getAllCards();
        mCardListView.setAdapter(new AdapterUserCard(getApplicationContext(), allCards));
    }

    private void startImageCardCreatorActivity() {
        Intent intent = new Intent(getApplicationContext(), ActivityImageCardCreator.class);
        intent.putExtra(BundleConstants.CARD_RECORD_ID.getValue(), mCardIdForDelete);
        startActivity(intent);
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View pView) {
            switch (pView.getId()) {
                case R.id.main_add_new_card_button:
                    startImageCardCreatorActivity();
                    break;

                case R.id.main_remove_all_card_button:
                    DialogClean cleanDialog = new DialogClean();
                    cleanDialog.setChoiceListener(mChoiceListener);
                    break;
            }

        }
    }

    private class ItemClicker implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            UserImageCard selectedCard = (UserImageCard) parent.getItemAtPosition(position);
            mCardIdForDelete = selectedCard.getDataBaseId();
            startImageCardCreatorActivity();
        }
    }

    private class ItemLongClicker implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            DialogConfirm dialogConfirm = new DialogConfirm();
            dialogConfirm.setChoiceListener(mChoiceListener);
            return true;
        }
    }

    private class DialogCallBack implements UserChoiceListener {

        DataBaseApi mBaseApi = DataBaseApi.getDataBaseApi(getApplicationContext());

        @Override
        public void deleteAllCards() {
            mBaseApi.deleteAllCards();
            Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void deleteSingleCard() {
            mBaseApi.deleteTargetCard(mCardIdForDelete);
            Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
        }
    }
}
