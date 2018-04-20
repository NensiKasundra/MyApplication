package example.com.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import example.com.myapplication.PermissionHandler.PermissionHandlerActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends PermissionHandlerActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String imgUrl = "http://111.93.75.62:6215/1.png";
    //String imgUrl = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";
    Context mContext;
    Button btnGo;
    Button btn_app_download;
    Button btn_service;
    Button btn_share;
    Button btn_more_apps;
    Button btn_rate_us;
    Button btn_exericse;
    Button btn_webview;
    Button btn_permission;
    APIInterface apiInterface;
    private int permissionsCode = 123;

    private int requestGallery = 2;
    private File photoFile;

    DrawerLayout drawer;


    private List<StateModel> stateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Menu 1");
        mContext = MainActivity.this;

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View view = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView img = view.findViewById(R.id.Glide_Image);

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        btnGo = (Button) findViewById(R.id.btn_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(MainActivity.this, true);
                //ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pairs);
                Intent i = new Intent(MainActivity.this, TwoActvity.class);
                //startActivity(i, transitionActivityOptions.toBundle());
                startActivity(i);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);


                /*String regNo = "DL 01 AA 1111";
                Pattern pattern = Pattern.compile("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$");
                Matcher matcher = pattern.matcher(regNo);
                if (matcher.matches()) {
                    Toast.makeText(mContext, "valid", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "not valid", Toast.LENGTH_LONG).show();
                }*/
            }
        });

        btn_service = (Button) findViewById(R.id.btn_service);

        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("userId", "7208998738@hdfcergo.com");
                jsonObject.addProperty("password", "hdfc1234");
                jsonObject.addProperty("passcode", "");

                Call loginDataCall = apiInterface.login(jsonObject);
                loginDataCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        LoginResponse loginResponse = (LoginResponse) response.body();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.d("error", t.getMessage());
                        //give custom msg for error handling
                    }
                });
            }
        });


        btn_app_download = (Button) findViewById(R.id.btn_app_download);
        btn_app_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        btn_webview = findViewById(R.id.btn_webView);
        btn_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });

        btn_exericse = (Button) findViewById(R.id.btn_exericse);
        btn_exericse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });

        btn_permission = (Button) findViewById(R.id.btn_permission);
        btn_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

        Button btn_notification = (Button) findViewById(R.id.btn_notification);
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btn_gallery = findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestGallery);
            }
        });

        AutoCompleteTextView autotvHospitalState = (AutoCompleteTextView) findViewById(R.id.autotvHospitalState);
        CustomListAdapter adapter = new CustomListAdapter(mContext, getData());
        autotvHospitalState.setThreshold(1);
        autotvHospitalState.setAdapter(adapter);
        autotvHospitalState.setOnItemClickListener(onItemClickListener);

    }

    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(MainActivity.this,
                            "Clicked item from auto completion list "
                                    + adapterView.getItemAtPosition(i)
                            , Toast.LENGTH_SHORT).show();
                }
            };

    private List<StateModel> getData() {
        StateModel stateModel = new StateModel("01", "Gujarat");
        StateModel stateModel1 = new StateModel("02", "Maharastra");
        StateModel stateMode2 = new StateModel("03", "Asam");
        StateModel stateMode3 = new StateModel("04", "Goa");

        stateList.add(stateModel);
        stateList.add(stateModel1);
        stateList.add(stateMode2);
        stateList.add(stateMode3);
        return stateList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestGallery && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            // CALL THIS METHOD TO GET THE ACTUAL PATH
            String imgPath = getRealPathFromURI(selectedImage);
            photoFile = new File(imgPath);
            storeImageInFolder();
        }
    }

    private void storeImageInFolder() {
        Bitmap myBitmap = Utility.decodeSampledBitmapFromFile(photoFile.toString(), 100, 100);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/HDFC/EValut/Demo/";
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String format = "WCM_img" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        final File file = new File(dir, format + ".jpg");
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            myBitmap.compress(Bitmap.CompressFormat.PNG, 50, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
        setImageInImageView(file.getPath());
    }

    private void setImageInImageView(String path) {
        ImageView imageView = findViewById(R.id.img);
        File image = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(image.getPath());
        imageView.setImageBitmap(bitmap);
    }


    public String getRealPathFromURI(Uri uri) {
        String selectedImagePath;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor == null) {
            selectedImagePath = uri.getPath();
        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            selectedImagePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return selectedImagePath;
    }

    private void checkPermissions() {
        permission();
        /*if (isPermissionNotGranted()) {
            checkPermission();
        } else {
            Toast.makeText(mContext, "All Permission", Toast.LENGTH_LONG).show();
        }*/
    }

    private void permission() {
        String[] permissions = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};

        boolean stickyMode = true;
        askForPermission(permissions, stickyMode, new PermissionHandlerActivity.PermissionCallBack() {

            @Override
            public void onPermissionsGranted() {
                Toast.makeText(mContext, "All Permission", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionsDenied(String[] permissions) {
                //Toast.makeText(MainActivity.this, "onPermissionsDenied ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(R.string.permission_denied)
                .setMessage(R.string.permission_message)
                .setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }


    private void showAlertDialog() {
        final AlertDialog.Builder aleBuilder = new AlertDialog.Builder(mContext);
        aleBuilder.setTitle("App Update");
        aleBuilder.setMessage("App Update available!!!");
        aleBuilder.setIcon(android.R.drawable.ic_dialog_alert);


        aleBuilder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with download
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.hdfcergo.com/csm/wcm/wcm.apk"));
                startActivity(browserIntent);
            }
        });
        aleBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = aleBuilder.create();
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_camera:
                fragment = new Menu1();
                break;
            case R.id.nav_gallery:
                fragment = new Menu2();
                break;
            case R.id.nav_slideshow:
                fragment = new Menu3();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
