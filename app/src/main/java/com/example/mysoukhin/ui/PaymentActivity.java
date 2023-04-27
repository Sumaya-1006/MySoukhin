package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mysoukhin.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    Toolbar toolbar;
    TextView subTotal, discount, shipping, total;
    Button paymentBtn;
    int totalAmount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //Toolbar
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
           totalAmount = getIntent().getIntExtra("amount", 0);

            subTotal = findViewById(R.id.sub_total);
            discount = findViewById(R.id.textView17);
            shipping = findViewById(R.id.textView18);
            total = findViewById(R.id.total_amt);
            paymentBtn = findViewById(R.id.pay_btn);

            subTotal.setText(totalAmount + "৳");
            shipping.setText("Free");
            total.setText(totalAmount + "৳");

            paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               paymentMethod();
            }
        });


    }

 private void paymentMethod() {
        Checkout checkout = new Checkout();
        checkout.setImage(R.drawable.shoukhin);

        final Activity activity = PaymentActivity.this;

        try {
            JSONObject options = new JSONObject();
            //Set Company Name
            options.put("name", "@string/app_name");
            //Ref no
            options.put("description", "Reference No. #123456");
            //Image to be display
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            // Currency type
            options.put("currency", "Tk");
            //double total = Double.parseDouble(mAmountText.getText().toString());
            //multiply with 100 to get exact amount in rupee
            totalAmount = totalAmount * 100;
            //amount
            options.put("amount", totalAmount);
            options.put("order_id", "order_DBJOWzybf0sJbb");
            JSONObject preFill = new JSONObject();
            //email
            preFill.put("email", "sumia2997@gmail.com");
            //contact
            preFill.put("contact", "7489347378");

            options.put("prefill", preFill);
            checkout.open(activity,preFill);


        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
       }
      }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this, "Payment Cancel", Toast.LENGTH_SHORT).show();
    }
}
