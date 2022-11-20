package de.secretj12.hackatum22;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

import de.secretj12.hackatum22.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MapView map;
    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // be happy
        } else {
            // gimme the permission
            requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION

            );
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //load/initialize the osmdroid configuration, this can be done
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string

        //inflate and create the map
        setContentView(R.layout.activity_main);

        map = (MapView) findViewById(R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(false);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(16d);
        GeoPoint startPoint = new GeoPoint(48.2637, 11.6684);
        mapController.setCenter(startPoint);

        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), map);
        this.mLocationOverlay.enableMyLocation();
        map.getOverlays().add(this.mLocationOverlay);

        this.mCompassOverlay = new CompassOverlay(getApplicationContext(), new InternalCompassOrientationProvider(getApplicationContext()), map);
        this.mCompassOverlay.enableCompass();
        map.getOverlays().add(this.mCompassOverlay);

        final Context context = getApplicationContext();
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScaleBarOverlay mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(true);
        //play around with these values to get the location on screen in the right place for your application
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        map.getOverlays().add(mScaleBarOverlay);

        //your items
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.111355593621, 11.61430161647961)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.17566584451249, 11.606248275586244)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.13569262741428, 11.63329945842623)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.1765304782419, 11.58813361092573)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.14912076050485, 11.570784175202212)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.17607553069282, 11.60678544203654)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.106241100000005, 11.5806306)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.13046701048668, 11.613351477617488)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.16163430223077, 11.622674470519335)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.172420882348, 11.608645678780396)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.11291668736461, 11.547805204701106)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.16265466072837, 11.6237920164228)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.118031503459626, 11.61900312193212)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.17604819685549, 11.60673962645799)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.12177302648248, 11.62046123985182)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.11290549141834, 11.615932367309346)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.11925483880852, 11.61818097587384)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.16881681797188, 11.616833182061308)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.104692400000005, 11.5873091)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.17208671297508, 11.603695515414293)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.11211300211448, 11.615292419261202)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.14868644684231, 11.525206316022905)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.171796512604, 11.601411486290576)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.16089514763812, 11.549527023953246)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.11835266871433, 11.618987385257748)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.16302274431093, 11.624135105357796)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.10089803370931, 11.541217267708962)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.11503273780353, 11.617648147817643)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.17299008497127, 11.613106928911195)));
        items.add(new OverlayItem("Issue point", "Here's a problem", new GeoPoint(48.14774795111176, 11.527965543942017)));


        //the overlay
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        //do something
                        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(intent);
                        updateProgressBar();
                        return false;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return true;
                    }
                }, context);
        mOverlay.setFocusItemsOnTap(true);

        map.getOverlays().add(mOverlay);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    public void setGps(View view) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            updateProgressBar();

            requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION

            );
        }
        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        map.getController().setCenter(new GeoPoint(locationGPS.getLatitude(), locationGPS.getLongitude()));
    }

    public void updateProgressBar(){
        ProgressBar progressBar =  findViewById(R.id.progressBar);
        TextView textView =  findViewById(R.id.textView);
        int progress = progressBar.getProgress();
        if(progress==100){
            progressBar.setProgress(10);
            int lvl = Integer.parseInt(textView.getText().toString())+1;
            textView.setText((lvl + "").toCharArray(),0,(lvl+"").length());
        }else{
            progressBar.setProgress(progress+10);
        }

    }

}