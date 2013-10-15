package ui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kozhurov.R;

public class DialogConfirm extends DialogBase {

    private UserChoiceListener mChoiceListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View pView) {

            switch (pView.getId()) {
                case R.id.dialog_no_button:
                    dismiss();
                    break;
                case R.id.dialog_yes_button:
                    if (mChoiceListener != null) {
                        mChoiceListener.deleteSingleCard();
                    }
                    dismiss();
                    break;
            }
        }
    }
}
