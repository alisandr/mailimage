package ui;

/**
 * User: Андрей
 * Date: 15.10.13
 */
public enum BundleConstants {

    CARD_RECORD_ID("card_record_id");

    private String mValue;

    private BundleConstants(String pValue) {
        mValue = pValue;
    }

    public String getValue(){
        return mValue;
    }
}
