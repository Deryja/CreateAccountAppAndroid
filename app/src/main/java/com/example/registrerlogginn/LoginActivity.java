package com.example.registrerlogginn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {



   //1. Først går vi til activity design koden og kopierer og limer inn EditText for username og password fra activity main, eneste forskjellen er at vi kaller de for username1 og password1
//Så kopierer vi og limer vi inn signin knappen, den skal også hete signin1

        //2. Nå definerer vi username og password og button som i MainActivity


 EditText username, password;
 Button btnlogin;
 DBHelper DB; //Steg 7 fortsettelse fra MainActivity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //3. Nå fester vi de til id'ene deres

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);

        btnlogin = (Button) findViewById(R.id.btnsignin1);

        DB = new DBHelper(this); //Fortsettelse for steg 7 fra mainActivity


        //4. Nå går vi til MainActivity java koden og skriver inn handlingen som skal skje med signin knappen


btnlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        //Fortsettelse på steg 7
        String user = username.getText().toString();
        String pass = password.getText().toString();

//Nå feilmeldinger for å se om feltene fylles ut eller om brukeren har en registrert bruker/oppfylt riktig informasjon

        if (user.equals("") || pass.equals(""))
            Toast.makeText(LoginActivity.this, "Du må fylle ut alle feltene for å logge inn", Toast.LENGTH_SHORT).show();

        else {
            Boolean checkuserpass = DB.checkusernamepassword(user, pass);
            if (checkuserpass == true) {
                Toast.makeText(LoginActivity.this, "Innloggin var suksessfull", Toast.LENGTH_SHORT).show();
                //Hvis brukernavn og passord er i databasen, så logger den inn og sender brukeren til HomeActivity siden
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

      else {
                Toast.makeText(LoginActivity.this, "Ugyldig brukernavn eller passord", Toast.LENGTH_SHORT).show();
            }

        }

    }

//Det som er mellom her og "fortsettelse på steg 7" er alt steg 7, og den siste delen

});


    }
}