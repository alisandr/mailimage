package ui.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.kozhurov.R;

public class DialogBase extends DialogFragment {

    protected UserChoiceListener mChoiceListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setChoiceListener(UserChoiceListener pChoiceListener) {
        mChoiceListener = pChoiceListener;
    }

    protected void addListenersToElements(View.OnClickListener pListener){

        getView().findViewById(R.id.dialog_no_button).setOnClickListener(pListener);
        getView().findViewById(R.id.dialog_yes_button).setOnClickListener(pListener);
    }
}
