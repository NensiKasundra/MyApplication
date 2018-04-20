package example.com.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by kasundne on 9/12/2017.
 */
public class Utility {
    private static ProgressDialog pDialog;

    public interface UpdateTimelineVideo {
        public void updateVideoIcon(String status);
    }

    public interface UpdateTimelineCamera {
        public void updateCameraIcon(String status);
    }

    public interface UpdateTimelineSummary {
        public void updateSummaryIcon(String status);
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null

                && connectivityManager.getActiveNetworkInfo().isAvailable()

                && connectivityManager.getActiveNetworkInfo().isConnected()) {

            return true;
        } else {
            Log.v("INTERNETWORKING", "Internet not present");
            return false;
        }

    }

    public static void showSnackbar(Context context, String msg) {
        View view = ((Activity) context).findViewById(android.R.id.content);
        Snackbar snack = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View sbView = snack.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.black));
        sbView.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        textView.setMaxLines(3);
        snack.show();
    }


    public static void showdialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void hideKeyboard(View v, Activity a) {
        final InputMethodManager imm = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void showLoading(Context mContext) {
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public static void hideLoading() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public static boolean isPasscodeValid(String passcode) {
        boolean isValid;
        String expression = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
        CharSequence inputStr = passcode;

        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;

        } else {

            isValid = false;

        }
        return isValid;
    }

    public static boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String emailString = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(emailString);

        m = p.matcher(email);
        check = m.matches();

        return check;
    }

    public static boolean isValidPanNo(String panNo) {
        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher matcher = pattern.matcher(panNo);
        return matcher.matches();
    }

    public static boolean isValidRegNo(String regNo) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$");
        Matcher matcher = pattern.matcher(regNo);
        return matcher.matches();

    }

    /**
     * @param mContext activity context
     * @return true if gps enable else false
     */
    public static boolean checkGpsEnable(Context mContext) {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static String getCurrentDate() {
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date curDate = new Date();
        dateFormatter.setLenient(false);
        return dateFormatter.format(curDate);
    }

    public static String getCurrentTime() {
        DateFormat timeFormatter = new SimpleDateFormat("hh:mm");
        Date curDate = new Date();
        timeFormatter.setLenient(false);
        return timeFormatter.format(curDate);
    }


}
