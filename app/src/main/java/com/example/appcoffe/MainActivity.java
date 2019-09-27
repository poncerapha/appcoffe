package com.example.appcoffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void increment(View view) {
        if (quantity == 100) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }


    public void decrement(View view) {
        if (quantity == 1) {

            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocalateCheckbox = findViewById(R.id.chocolate_checkbox);
        EditText nameField = findViewById(R.id.name_field);

        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocalateCheckbox.isChecked();
        String userName = nameField.getText().toString();
        String message = createOrderSummaty(calculatePrice(quantity, 5, hasWhippedCream, hasChocolate), hasWhippedCream, hasChocolate, userName);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + userName);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private int calculatePrice(int quantity, int price, boolean hasChocolate, boolean hasWhippedCream) {
        int basePrice = price;
        if (hasWhippedCream) {
            basePrice = price + 1;
        }

        if (hasChocolate) {
            basePrice = price + 2;
        }

        return basePrice * quantity;
    }

    private String createOrderSummaty(int number, boolean addWhippedCream, boolean addChocolate, String userName) {
        String priceMessage = "Name: " + userName;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nTotal: $" + number;
        priceMessage += "\nThank you!";
        return priceMessage;
    }
}
