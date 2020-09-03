package com.example.subscriptionforme.home.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Listener.DeleteUserSubscriptionListener;
import com.example.subscriptionforme.main.UserDatabase;

public class CheckDeleteUserSubscriptionDialog extends Dialog {

    private Button cancelButton, deleteButton;
    private DeleteUserSubscriptionListener deleteUserSubscriptionListener;

    public CheckDeleteUserSubscriptionDialog(@NonNull final Context context, final String registerNumber) {
        super(context);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setDimAmount(0.81f);

        setContentView(R.layout.dialog_check_delete_subscription);

        cancelButton = findViewById(R.id.cancel_button_check_delete_dialog);
        deleteButton = findViewById(R.id.delete_button_check_delete_dialog);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDatabase.getInstance(context).deleteSubscription(UserDatabase.getInstance(context).getWritableDatabase(),registerNumber);
                deleteUserSubscriptionListener.deleteButtonClicked();

                dismiss();
            }
        });

        show();
    }

    public void setDeleteUserSubscriptionListener(DeleteUserSubscriptionListener deleteUserSubscriptionListener) {
        this.deleteUserSubscriptionListener = deleteUserSubscriptionListener;
    }
}
