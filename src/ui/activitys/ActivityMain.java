package ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.kozhurov.R;
import ui.dialogs.UserChoiceListener;
import utils.DataBaseApi;

/**
 * User: Андрей
 * Date: 14.10.13
 */
public class ActivityMain extends ActivityBase {

    private long mCardIdForDelete;

    private ListView mCardListView;

    private UserChoiceListener mChoiceListener;
    //private

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
    }

    private void initVariables() {
        mCardIdForDelete = -1;
        mChoiceListener = new DialogCallBack();
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View pView) {
            switch (pView.getId()) {
                case R.id.main_add_new_card_button:
                    break;
                case R.id.main_remove_all_card_button:
                    break;
            }

        }
    }

    private class ItemClicker implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    private class ItemLongClicker implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
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
            if (mCardIdForDelete != -1) {
                mBaseApi.deleteTargetCard(mCardIdForDelete);
            }
            Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
        }
    }
}
