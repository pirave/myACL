package com.mobile.app.myacl.UserManager;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.mobile.app.myacl.DatabaseManager.UserDB;
import com.mobile.app.myacl.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pirave on 15-03-01.
 */
public class APIClient {
    private Context context;
    private static String URL;
    private static String phoneID;

    public APIClient(Context context) {
        this.context = context;
        this.URL = context.getString(R.string.api);
        this.phoneID = UserProfile.getInstance(context).getID();
    }

    public void sendProfileData(UserProfile profile){
        new HttpProfileAsyncTask().execute(profile);
    }

    public void updateProgress(){
        UserDB userDB = new UserDB(context);
        userDB.open();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1); //minus number would decrement the days
        Date endDate = cal.getTime();
        Date startDate = new Date(context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE)
                .getLong("progressLastSyncDate", endDate.getTime()));
        //startDate = UserProfile.getInstance(context).getSurgeryDate();
        List<UserProgress> progressList = userDB.getProgressData(startDate, endDate);
        userDB.close();
        new HttpProgressAsyncTask().execute(progressList);
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpProfileAsyncTask extends AsyncTask<UserProfile, Void, String> {
        @Override
        protected String doInBackground(UserProfile... profiles) {
            return POSTUserProfile(profiles[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject json = new JSONObject(s);
                if (json.getString("_id") != null){
                    context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE).edit()
                            .putBoolean("profileCreated", true).commit();
                } else{
                    context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE).edit()
                            .putBoolean("profileCreated", false).commit();
                }
            } catch (Exception e)
            {
                Log.e("Parsing Json Error", e.toString());
                context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE).edit()
                        .putBoolean("profileCreated", false).commit();
            }
        }
    }

    private class HttpProgressAsyncTask extends AsyncTask<List<UserProgress>, Void, Date> {
        @Override
        protected Date doInBackground(List<UserProgress>... progressList) {
            return POSTUserProgressData(progressList[0]);
        }

        @Override
        protected void onPostExecute(Date date) {
            super.onPostExecute(date);
            if (date != null)
                context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE).edit()
                    .putLong("progressLastSyncDate", date.getTime()).commit();
        }
    }

    public static String POSTUserProfile(UserProfile person){
        try {
            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("phoneID", person.getID());
            jsonObject.accumulate("name", person.getUsername());
            jsonObject.accumulate("gender", person.getGender());
            jsonObject.accumulate("age", person.getAge());
            jsonObject.accumulate("surgeryType", person.getSurgeryType());
            jsonObject.accumulate("surgeryDate", person.getSurgeryDate());
            jsonObject.accumulate("createDate", person.getCreateDate());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            return POST(URL + "users", json);


        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return null;
    }

    public static Date POSTUserProgressData(List<UserProgress> userProgressList){

        Date lastSyncDate = null;
        try {
            JSONObject json;
            for (UserProgress progress: userProgressList){
                json = new JSONObject(POSTUserProgress(progress));
                if (json.getString("type") == progress.getCatDescr())
                    lastSyncDate = progress.getDate();
            }
        } catch (Exception e)
        {
            Log.e("Parsing Json Error", e.toString());
        }
        return lastSyncDate;
    }

    public static String POSTUserProgress(UserProgress progress){
        try {
            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("phoneID", phoneID);
            jsonObject.accumulate("type", progress.getCatDescr());
            jsonObject.accumulate("day", progress.getDate());
            jsonObject.accumulate("complete", progress.isComplete());
            jsonObject.accumulate("rangeDegree", progress.getRangeDegree());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            return POST(URL + "progress", json);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return null;
    }

    public static String POST(String url, String json){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            // 3. & 4. incoming parameter json

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
}
