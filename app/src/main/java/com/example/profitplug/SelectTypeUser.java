package com.example.profitplug;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SelectTypeUser extends AppCompatActivity {
    ImageButton business, customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_user);
        business = findViewById(R.id.business);
        customer = findViewById(R.id.customer);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectTypeUser.this, OnBoardMain.class);
                i.putExtra("type", "business");
                startActivity(i);
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectTypeUser.this, OnBoardMain.class);
                i.putExtra("type", "customer");
                startActivity(i);
            }
        });
    }
}
