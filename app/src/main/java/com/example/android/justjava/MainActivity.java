package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int quantity;
    int price;




    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Add name field and get user name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        //Add whipped cream check box and determine if user wants it
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //Add chocolate checkbox and determine if user wants it
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    /*
    * This method calculates the price of the order
     */
    public int calculatePrice(boolean wantWhippedCream, boolean wantChocolate) {
        int pricePerCup = 5;
        int plusWhippedCream = 2;
        int plusChocolate = 1;
        if (wantWhippedCream && wantChocolate) {
            price = quantity * (pricePerCup + plusWhippedCream + plusChocolate);
        } else if (wantChocolate) {
            price = quantity * (pricePerCup + plusChocolate);
        } else if (wantWhippedCream) {
            price = quantity * (pricePerCup + plusWhippedCream);
        } else {
            price = quantity * pricePerCup;
        }

        return price;
    }





    public void increment(View view) {
        if (quantity == 100) {
            displayQuantity(100);
        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0)
        {displayQuantity(0);
        }else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    /*
    * This method creates the order summary
     */
    public String createOrderSummary(String nameOnOrder, int price, boolean whippedCreamChecked, boolean chocolateChecked) {
        String priceMessage = getString(R.string.order_summary_name, nameOnOrder);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, whippedCreamChecked);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, chocolateChecked);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_total, price);
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }
}
