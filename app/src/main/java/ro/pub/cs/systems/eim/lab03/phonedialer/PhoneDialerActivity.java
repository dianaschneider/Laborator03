package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {
    private ImageButton hangupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        hangupButton = (ImageButton) findViewById(R.id.KEY_HANGUP);
        hangupButton.setOnClickListener(hangupButtonClickListener);
    }


    public void updateEditText(String newChar) {
        EditText dialedNumber = (EditText) findViewById(R.id.dialedNumber);
        String number = dialedNumber.getText().toString();
        dialedNumber.setText(number + newChar);
    }

    public void onClickKeyButton(View view) {
        switch (view.getId()) {
            case R.id.KEY0:
                updateEditText("0");
                break;
            case R.id.KEY1:
                updateEditText("1");
                break;
            case R.id.KEY2:
                updateEditText("2");
                break;
            case R.id.KEY3:
                updateEditText("3");
                break;
            case R.id.KEY4:
                updateEditText("4");
                break;
            case R.id.KEY5:
                updateEditText("5");
                break;
            case R.id.KEY6:
                updateEditText("6");
                break;
            case R.id.KEY7:
                updateEditText("7");
                break;
            case R.id.KEY8:
                updateEditText("8");
                break;
            case R.id.KEY9:
                updateEditText("9");
                break;
            case R.id.KEY_STAR:
                updateEditText("*");
                break;
            case R.id.KEY_DIEZ:
                updateEditText("#");
                break;
        }
    }

    public void onClickBackspaceButton(View view) {
        EditText dialedNumber = (EditText) findViewById(R.id.dialedNumber);
        String number = dialedNumber.getText().toString();
        if (number.length() > 0) {
            dialedNumber.setText(number.substring(0, number.length() - 1));
        }
    }

    public void onClickCallButton(View view) {
        if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    PhoneDialerActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Constants.PERMISSION_REQUEST_CALL_PHONE
                    );
        } else {
            EditText dialedNumber = (EditText) findViewById(R.id.dialedNumber);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + dialedNumber.getText().toString()));
            startActivity(intent);
            Log.d("CALL", "calling?");
        }
    }

    private HangupButtonClickListener hangupButtonClickListener = new HangupButtonClickListener();
    private class HangupButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }
}