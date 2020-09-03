package com.example.subscriptionforme.home.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.UserSubscriptionData;
import com.example.subscriptionforme.home.Listener.DeleteUserSubscriptionListener;
import com.example.subscriptionforme.main.MainActivity;

public class DeleteUserSubscriptionDiaolg extends Dialog {

    private Button deleteButton, cancelButton;
    private TextView subscriptionName, subscriptionPaymentSystem, subscriptionDescription, deleteUrlTextView;
    private ImageView subscriptionImage;
    private UserSubscriptionData userSubscriptionData;
    private DeleteUserSubscriptionListener deleteUserSubscriptionListener;

    public DeleteUserSubscriptionDiaolg(@NonNull final Context context, final MainActivity mainActivity, final UserSubscriptionData userSubscriptionData) {
        super(context);

        this.userSubscriptionData = userSubscriptionData;

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(R.layout.dialog_delete_subscription);

        subscriptionName = findViewById(R.id.name_delete_dialog);
        subscriptionPaymentSystem = findViewById(R.id.payment_system_delete_dialog);
        subscriptionDescription = findViewById(R.id.description_delete_dialog);
        subscriptionImage = findViewById(R.id.image_delete_dialog);
        deleteUrlTextView = findViewById(R.id.url_text_delete_dialog);

        subscriptionName.setText(userSubscriptionData.getSubscriptionName());
        subscriptionDescription.setText(userSubscriptionData.getSubscriptionDescription().replaceAll("\n",""));
        subscriptionPaymentSystem.setText(userSubscriptionData.getSubscriptionPaymentSystem());
        subscriptionImage.setImageResource(userSubscriptionData.getSubscriptionImageID());

        deleteButton = findViewById(R.id.delete_button_delete_dialog);
        cancelButton = findViewById(R.id.cancel_button_delete_dialog);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckDeleteUserSubscriptionDialog checkDeleteUserSubscriptionDialog = new CheckDeleteUserSubscriptionDialog(context,userSubscriptionData.getRegisterNumber());

                //리스너 지정, 이 다이얼로그도 dissmiss 될수 있도록.
                checkDeleteUserSubscriptionDialog.setDeleteUserSubscriptionListener(new DeleteUserSubscriptionListener() {
                    @Override
                    public void deleteButtonClicked() {
                        deleteUserSubscriptionListener.deleteButtonClicked();
                        dismiss();
                    }
                });

            }
        });

        deleteUrlTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteUrl = mainActivity.subscriptionModelDataList.get(Integer.parseInt(userSubscriptionData.getSubscriptionNumberID())).getCancellationUrl();

                if (deleteUrl != null) {
                    dismiss();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(deleteUrl));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "죄송합니다. 등록된 URL 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        show();
    }

    public void setDeleteUserSubscriptionListener(DeleteUserSubscriptionListener deleteUserSubscriptionListener) {
        this.deleteUserSubscriptionListener = deleteUserSubscriptionListener;
    }
}
