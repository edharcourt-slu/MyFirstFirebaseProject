package edu.stlawu.ehar.myfirstfirebaseproject;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Name {
    public String first;
    public String last;

    public Name() {}

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }
}
