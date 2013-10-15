package utils;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: Daniel Goncharov
 * Date: 15.07.13
 */
public class FieldValidator {

    private static final String EMAIL_FILTER = "[a-zA-Z0-9\\.\\_\\-]{1,50}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,20}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,4})";

    public static boolean isValidEmail(String target) {
        return isValidField(target, EMAIL_FILTER);
    }

    public static boolean isAllEditTextNotNull(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText == null || editText.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidField(String validationField, String symbolFilter) {
        Pattern pattern = Pattern.compile(symbolFilter);
        Matcher matcher = pattern.matcher(validationField);
        return matcher.matches();
    }

    public static boolean isFieldsEmpty(String... strings) {
        if (strings == null || strings.length == 0) {
            return true;
        }
        for (String string : strings) {
            if (TextUtils.isEmpty(string)) {
                return true;
            }
        }
        return false;
    }
}
