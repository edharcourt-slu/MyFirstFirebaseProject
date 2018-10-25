package edu.stlawu.ehar.myfirstfirebaseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        DatabaseReference nameRef = database.getReference("name2");
        nameRef.setValue(new Name("Ed", "Harcourt"));

        DatabaseReference namesRef = database.getReference("names");
        namesRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> names = dataSnapshot.getChildren();
                        for (DataSnapshot name:names) {
                            String s = name.getValue(String.class);
                            Log.i("OOf2", s);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );

        // Read from the database
        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Name value = dataSnapshot.getValue(Name.class);
                Log.d("Oof", "Value is: " + value.last);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Oof2", "Failed to read value.", error.toException());
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Oof", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Oof2", "Failed to read value.", error.toException());
            }
        });
    }
}
