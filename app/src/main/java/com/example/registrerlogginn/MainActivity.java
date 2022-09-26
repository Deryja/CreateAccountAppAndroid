package com.example.registrerlogginn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


 EditText username, password, repassword;
 Button signup, signin;


 //Fortsettelse på steg 5
 DBHelper DB;





//Vi legger til de tomme variablene over

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Nå knytter vi de til design-koden:

username = (EditText) findViewById(R.id.username);
password = (EditText) findViewById(R.id.password);
repassword = (EditText) findViewById(R.id.repassword);

signup = (Button) findViewById(R.id.btnsignup);
signin = (Button) findViewById(R.id.btnsignin);


//Fortsettelse på steg 5 (etter LoginActivity):
        DB = new DBHelper(this);


//Nå legger vi til en handling som skal skje med signup knappen
        //Og signin

   signup.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {

//Fortsettelse på steg 5 (etter LoginActivity) under her


           //Dette er for opprettelsen av en bruker/Registrering
           String user = username.getText().toString();
           String pass = password.getText().toString();
           String repass = repassword.getText().toString();

//Feilmelding hvis ikke alle feltene fylles ut:
           if (user.equals("") || pass.equals("") || repass.equals(""))
               Toast.makeText(MainActivity.this, "Alle feltene må fylles ut", Toast.LENGTH_SHORT).show();


               //Hvis passord og repassord er like, så sjekker den om brukernavnet er inni databasen
           else {
               if (pass.equals(repass)) {

                   Boolean checkuser = DB.checkusername(user);
                   if (checkuser == false) {
                       Boolean insert = DB.insertData(user, pass);

                       if (insert == true) {
                           Toast.makeText(MainActivity.this, "Registrering var vellykket", Toast.LENGTH_SHORT).show();


//Nå som steg 5 fra LoginActivity har blitt vellykket, så går vi over til steg 6. Vi oppretter en ny empty Activity
                           //Som skal hete HomeActivity

                           //Steg 6: Nå lager vi Intent funksjon som gjør at HomeActivity åpner seg når innlogging er suksessfult:
                           //(Eventuelt en feilmelding hvis registrering ikke var suksessfullt), og en feilmelding hvis bruker allerede eksisterer,
                           //og en siste feilmelding hvis passordene ikke matcher

                           Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                           startActivity(intent);
                       } else {
                           Toast.makeText(MainActivity.this, "Registering misslykket", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(MainActivity.this, "Bruker eksisterer allerde, vennligst logg inn", Toast.LENGTH_SHORT).show();
                   }

               } else {
                   Toast.makeText(MainActivity.this, "Passordene matcher ikke", Toast.LENGTH_SHORT).show();
               }


           }
       }
//Nå er steg 6 over, så vi går over til LoginActivity og definerer DBHelper under btlogin

//Steg 6 fortsettelse--> Det som er mellom her og steg 6 over er alt for steg 6.


       //Fortsettelse på steg 5 (etter LoginActivity) over her

   });



   signin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
 //Dette er steg 4. som fortsettelse fra LoginActivity:

           Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
           startActivity(intent);

//5. Fortsettelse etter LoginActivity --> Nå går vi øverst og definerer variabelen DBHelper DB


       }
   });

   //Nå lager vi ny java class for SQlite database hjelper





    }
}