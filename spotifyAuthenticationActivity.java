package com.example.spotifyapp2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import android.content.Intent;

public class spotifyAuthenticationActivity extends AppCompatActivity {
    private String CLIENT_ID = "1f675cc0632f4dac87e247ed3e198f2b";
    private String REDIRECT_URI = "http://com.yourdomain.yourapp/callback";
    private static final int REQUEST_CODE = 1234;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.com_spotify_sdk_login_activity);
        AuthenticationRequest.Builder authreq = new AuthenticationRequest.Builder(
                CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI
        );
        authreq.setScopes(new String[]{"Streaming"});
        AuthenticationRequest request = authreq.build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    Log.d("Authentication:","Logged In.");
                    // Handle successful response
                    break;

                // Auth flow returned an error
                case ERROR:

                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
}
