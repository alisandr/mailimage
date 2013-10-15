package ui.dialogs;

import java.io.Serializable;

/**
 * User: Андрей
 * Date: 14.10.13
 */
public interface UserChoiceListener extends Serializable {

    public void deleteAllCards();

    public void deleteSingleCard();

}
