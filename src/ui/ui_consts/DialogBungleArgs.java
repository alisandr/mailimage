package ui.ui_consts;

/**
 * User: Андрей
 * Date: 14.10.13
 */
public enum DialogBungleArgs {
    DIALOG_TITLE ("UniversalAlertDialog.DIALOG_TITLE"),
    DIALOG_TEXT ("UniversalAlertDialog.DIALOG_TEXT");

    private String mValue;

    private DialogBungleArgs(String pValue){
        mValue = pValue;
    }

    public String getValue(){
        return mValue;
    }
}
