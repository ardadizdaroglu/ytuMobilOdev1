package com.example.mobilodev1.MailAtma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilodev1.Menu.MenuActivity;
import com.example.mobilodev1.R;
//by Arda Dizdaroglu 19574016
public class MailActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etSubject;
    EditText etMessage;
    Button Send;
    Button attachment;
    TextView tvAttachment;
    String email;
    String subject;
    String message;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        etEmail = findViewById(R.id.etTo);
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
        attachment = findViewById(R.id.btAttachment);
        tvAttachment = findViewById(R.id.tvAttachment);
        Send = findViewById(R.id.btSend);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kime = etEmail.getText().toString().trim();
                String konu = etSubject.getText().toString().trim();
                String msj = etMessage.getText().toString().trim();
                if(kime.equals("")){
                    Toast.makeText(MailActivity.this,"Lutfen kime gondermek istediginizi yaziniz.",Toast.LENGTH_LONG).show();
                }
                else if(konu.equals("")){
                    Toast.makeText(MailActivity.this,"Lutfen konuyu yaziniz.",Toast.LENGTH_LONG).show();
                }
                else if(msj.equals("")){
                    Toast.makeText(MailActivity.this,"Lutfen mesajinizi yaziniz.",Toast.LENGTH_LONG).show();
                }
                else{
                    sendEmail();
                }
            }
        });
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            assert URI != null;
            tvAttachment.setText(URI.getLastPathSegment());
            tvAttachment.setVisibility(View.VISIBLE);
        }
    }
    public void sendEmail() {
        try {
            email = etEmail.getText().toString();
            subject = etSubject.getText().toString();
            message = etMessage.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            if (URI != null) {
                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent, "Araciligiyla gonder..."));
        } catch (Throwable t) {
            Toast.makeText(this, "Lutfen tekrar deneyiniz: "+ t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void openFolder() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MailActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
