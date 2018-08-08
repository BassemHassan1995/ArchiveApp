package com.watanya.archive.data.remote;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.jaredrummler.android.device.DeviceName;
import com.watanya.archive.data.local.prefs.AppPreferencesHelper;
import com.watanya.archive.ui.base.BaseApplication;
import com.watanya.archive.utils.Logger;
import com.watanya.archive.utils.Utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final boolean IS_PRODUCTION = true;

    private static final String REQUESTS_URL_STAGING = "http://163.172.104.238/";
    private static final String REQUESTS_URL_PRODUCTION = "http://163.172.104.238/";
    private static final String REQUESTS_BASE_URL = (IS_PRODUCTION ? REQUESTS_URL_PRODUCTION : REQUESTS_URL_STAGING);
    static final String SERVER_APIS_EXTENSION_URL = (IS_PRODUCTION ? "/aqarak/api/" : "/aqarak/api");
    private static Retrofit retrofit = null;
    public static DeviceName.DeviceInfo deviceInfo;

    long CACHE_SIZE = 20 * 1024 * 1024; // 10 MB
    private static OkHttpClient.Builder clientBuilder;

    static {
        clientBuilder = new OkHttpClient
                .Builder()
                .connectTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
//                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        okhttp3.Headers headers = request.headers();
                        Logger.instance().v("Request Headers", (headers != null) ? headers.toString() : "Null headers");
                        Logger.instance().v("Request URL", request.url());
                        Logger.instance().v("Request Method", request.method());
                        Logger.instance().v("Request Body", (request.body() != null) ? bodyToString(request.body()) : "NULL/Empty");

                        return chain.proceed(request);
                    }
                });
    }

    static Retrofit getClient() {
        if(retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(REQUESTS_BASE_URL)
                    .client(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static HashMap<String, String>  getDefaultHeaders() {

        HashMap<String, String> headers = new HashMap<>();

        putConnectionType(headers);
        putMobileCodes(headers);
        putAppVersion(headers);
        String model = deviceInfo!=null ? (deviceInfo.manufacturer + " " + deviceInfo.marketName): DeviceName.getDeviceName();
        headers.put("Accept-Language", AppPreferencesHelper.getInstance().getLanguage());
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        headers.put("deviceId", Utilities.getDeviceId());
        headers.put("deviceOs","Android");
        headers.put("deviceType",Utilities.isXlargeScreen() ? "Tablet":"Mobile");
        headers.put("deviceName",model);
        String token = AppPreferencesHelper.getInstance().getAccessToken();
        if(token != null)
            headers.put("Authorization", "Bearer " +token);

        return headers;
    }

    private static void putMobileCodes(HashMap<String, String> headers) {

        TelephonyManager tel = (TelephonyManager) BaseApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        if (networkOperator != null && networkOperator.length()>0) {
            String mcc = networkOperator.substring(0, 3);
            headers.put("mcc",mcc);
            String mnc = networkOperator.substring(3);
            headers.put("mnc",mnc);

        }

    }

    private static void putConnectionType(HashMap<String, String> headers) {
        ConnectivityManager con = (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = con.getActiveNetworkInfo();
        int connectionType = -1;
        String connectionValue = null;
        if (info != null)
            connectionType = info.getType();
        if (connectionType == ConnectivityManager.TYPE_WIFI)
            connectionValue = "wifi";
        else if (connectionType == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    connectionValue = "2g";
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    connectionValue = "3g";
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    connectionValue = "4g";
                    break;
                default:
                    break;
            }
        }

        if (connectionValue !=null)
            headers.put("connectionType",connectionValue);

    }

    private static String bodyToString(final RequestBody request){
        try {
            final Buffer buffer = new Buffer();
            request.writeTo(buffer);
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }

    private static void putAppVersion(HashMap<String,String > headers) {

        try {
            PackageInfo packageInfo = BaseApplication.getApplication().
                    getPackageManager().
                    getPackageInfo(BaseApplication.getApplication().getPackageName(), 0);
            String versionName = packageInfo.versionName;
            if (versionName!=null)
                headers.put("applicationVersion",versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
