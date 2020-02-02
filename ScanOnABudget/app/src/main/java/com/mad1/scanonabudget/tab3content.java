package com.mad1.scanonabudget;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.LOCATION_SERVICE;

public class tab3content extends Fragment implements View.OnClickListener  {
    private Button Themebutton, HandFbutton;
    private CheckBox soundcheckBox;
    private CheckBox locationcheckBox;
    private Switch themeswitch;
    private CheckBox vibratecheckBox;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab3, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            this.getActivity().setTheme(R.style.darktheme_NoActionBar);
        }else this.getActivity().setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        themeswitch = root.findViewById(R.id.themeswitch);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            themeswitch.setChecked(true);
            this.getActivity().setTheme(R.style.darktheme_NoActionBar);
           // this.getActivity().setTheme(R.style.darktheme_NoActionBar);
        }
        themeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getActivity().setTheme(R.style.darktheme_NoActionBar);

                //restartApp();
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getActivity().setTheme(R.style.AppTheme_NoActionBar);
                   // restartApp();
                }
            }
        });
        vibratecheckBox= root.findViewById(R.id.vibratecheckBox);
        vibratecheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                           try {
                                                               if (vibratecheckBox.isChecked()) {
                                                                   AudioManager amanager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                                                                   amanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                                               } else {
                                                                   AudioManager amanager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                                                                   amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                                                                   amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
                                                                   amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                                                                   amanager.setStreamMute(AudioManager.STREAM_RING, false);
                                                                   amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
                                                               }
                                                           } catch (Exception e) {
                                                               Toast.makeText(getContext().getApplicationContext(),"Can't turn on vibrate because phone is already on mute",Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                   });
        soundcheckBox= root.findViewById(R.id.soundcheckBox);
        soundcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                        if (soundcheckBox.isChecked()){
                                                            AudioManager amanager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                                                            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                                                            amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
                                                            amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                                                            amanager.setStreamMute(AudioManager.STREAM_RING, false);
                                                            amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
                                                           //amanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                                                        }else{
                                                            AudioManager amanager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                                                           amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                                                            amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
                                                            amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                                                            amanager.setStreamMute(AudioManager.STREAM_RING, true);
                                                            amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
                                                            //amanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                                        }
                                                    }
                                                }
        );
        locationcheckBox= root.findViewById(R.id.locationcheckBox);
        locationcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                         ActivityCompat.requestPermissions(tab3content.this.getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                                                         GpsTracker gt = new GpsTracker(getContext().getApplicationContext());
                                                         if (locationcheckBox.isChecked()){
                                                             Location l = gt.getLocation();
                                                             if( l == null){
                                                                Toast.makeText(getContext().getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
                                                             }else {
                                                                 double lat = l.getLatitude();
                                                                 double lon = l.getLongitude();
                                                                // Toast.makeText(getContext().getApplicationContext(), "GPS Lat = " + lat + "\n lon = " + lon, Toast.LENGTH_SHORT).show();
                                                             }
                                                         }else{
                                                            gt.onProviderDisabled(Context.LOCATION_SERVICE);

                                                         }
                                                     }
                                                 }
        );



        HandFbutton = root.findViewById(R.id.HandFbutton);
        HandFbutton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(tab3content.this.getActivity());
        alertdialogbuilder.setTitle("Help & Feedback");
        alertdialogbuilder.setPositiveButton("Feedback", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Subject = "FeedBack";
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "ScanOnABugdet@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, Subject);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        alertdialogbuilder.setNegativeButton("Help", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intenthelp = new Intent(getContext(), helpContent.class);
                startActivity(intenthelp);
            }
        });
        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();
    }
   @Override
    public void onResume(){
        super.onResume();


    }
    public void restartApp(){
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        //finish();
    }

    
}
