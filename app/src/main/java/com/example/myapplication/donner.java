package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donner extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b;
    DatabaseReference ref ;
    member m;
    long max=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donner);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        b=findViewById(R.id.b);
        ref= FirebaseDatabase.getInstance().getReference().child("member");
        m=new member();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    max=(dataSnapshot.getChildrenCount());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long p= Long.parseLong(e3.getText().toString().trim());
                m.setName(e1.getText().toString().trim());
                m.setBg(e2.getText().toString().trim());
                m.setMn(p);
                ref.child(String.valueOf(max+1)).setValue(m);

                Toast.makeText(donner.this, " Successfully send", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
