package com.felixserrano.intents;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_web_page = (Button)findViewById(R.id.btn_web_page);
        btn_web_page.setOnClickListener(this);

        Button btn_call = (Button)findViewById(R.id.btn_call);
        btn_call.setOnClickListener(this);

        Button btn_gmaps = (Button)findViewById(R.id.btn_gmaps);
        btn_gmaps.setOnClickListener(this);

        Button btn_take_photo = (Button)findViewById(R.id.btn_take_photo);
        btn_take_photo.setOnClickListener(this);

        Button btn_send_mail = (Button)findViewById(R.id.btn_send_mail);
        btn_send_mail.setOnClickListener(this);

        Button btn_web_search = (Button)findViewById(R.id.btn_web_search);
        btn_web_search.setOnClickListener(this);

        Button btn_dialer = (Button)findViewById(R.id.btn_dialer);
        btn_dialer.setOnClickListener(this);

        Button btn_street_view = (Button)findViewById(R.id.btn_street_view);
        btn_street_view.setOnClickListener(this);

        Button btn_share = (Button)findViewById(R.id.btn_share);
        btn_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_web_page:
                pgWeb();
                break;
            case R.id.btn_call:
                llamadaTelefono();
                break;
            case R.id.btn_gmaps:
                googleMaps();
                break;
            case R.id.btn_take_photo:
                hacerFoto();
                break;
            case R.id.btn_send_mail:
                mandarCorreo();
                break;
            case R.id.btn_web_search:
                busquedaWeb();
                break;
            case R.id.btn_dialer:
                dialerTelefono();
                break;
            case R.id.btn_street_view:
                streetView();
                break;
            case R.id.btn_share:
                share();
                break;
        }
    }

    private void pgWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://campus.somtic.net/"));
        startActivity(intent);
    }

    private void llamadaTelefono() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(
                    Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                Intent intent =
                        new Intent(Intent.ACTION_CALL,Uri.parse(
                                "tel:966870700"));
                startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:966870700"));
            startActivity(intent);
        }
    }

    private void googleMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:38.553468,-0.121579"));
        startActivity(intent);
    }

    private void hacerFoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    private void mandarCorreo() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[] {"smira@iesperemaria.com" });
        startActivity(intent);
    }

    private void busquedaWeb(){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,"IES Pere Maria Orts");
        startActivity(intent);
    }

    private void dialerTelefono()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:966870700"));
        startActivity(intent);
    }

    private void streetView()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=38.553468,0.121579"));
        startActivity(intent);
    }

    private void share()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Compartido desde IES Pere Maria Orts.");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.share)));
    }
}
