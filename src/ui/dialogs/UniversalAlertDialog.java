package ui.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.gistr.R;
import ui.ui_consts.DialogBungleArgs;

public class UniversalAlertDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alert_fragment_dialog, null);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {

        Bundle bundle = getArguments();

        String dialogTitle = bundle.getString(DialogBungleArgs.DIALOG_TITLE.getValue());
        TextView dialogTitleTv = (TextView) view.findViewById(R.id.alert_dialogs_title);
        dialogTitleTv.setText(dialogTitle);

        String dialogText = bundle.getString(DialogBungleArgs.DIALOG_TEXT.getValue());
        TextView dialogAnnotationTv = (TextView) view.findViewById(R.id.alert_dialogs_annotation);
        dialogAnnotationTv.setText(dialogText);

        view.findViewById(R.id.alert_dialogs_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
