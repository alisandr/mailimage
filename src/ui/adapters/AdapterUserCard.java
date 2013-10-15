package ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kozhurov.R;
import model.UserImageCard;
import utils.ImageHelper;

import java.util.List;

public class AdapterUserCard extends ArrayAdapter<UserImageCard> {

    public AdapterUserCard(Context pContext, List<UserImageCard> pUserImageCards) {
        super(pContext, 0, pUserImageCards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardHolder cardHolder;
        UserImageCard userImageCard;

        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.list_card_item, null);
            cardHolder = new CardHolder();

            cardHolder.mCardImage = (ImageView) convertView.findViewById(R.id.list_card_image_image_view);
            cardHolder.mCardEmail = (TextView) convertView.findViewById(R.id.list_card_email_text_view);
            cardHolder.mCardSubject = (TextView) convertView.findViewById(R.id.list_card_subject_text_view);

            convertView.setTag(cardHolder);
        } else {
            cardHolder = (CardHolder) convertView.getTag();
        }

        userImageCard = getItem(position);

        Drawable drawable = ImageHelper.getDrawableFromUri(userImageCard.getImageUri());

        cardHolder.mCardImage.setImageDrawable(drawable);
        cardHolder.mCardEmail.setText(userImageCard.getEmail());
        cardHolder.mCardSubject.setText(userImageCard.getSubject());

        return convertView;
    }

    private class CardHolder {

        public ImageView mCardImage;

        public TextView mCardEmail;

        public TextView mCardSubject;
    }
}
