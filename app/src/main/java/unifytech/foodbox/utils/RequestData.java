package unifytech.foodbox.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import unifytech.foodbox.FoodMenuFragment;
import unifytech.foodbox.LocateFoodBox;
import unifytech.foodbox.R;
import unifytech.foodbox.RestaurantList;
import unifytech.foodbox.app.AppController;
import unifytech.foodbox.data.FoodItems;

public class RequestData {
    public static final String BASE_URL = "http://103.21.76.186:9099";

    public static String sUrl = "http://103.21.76.186:9099/restaurants/";
    public static String sUrlFoodItems = "http://103.21.76.186:9099/fooditems/";
    private static String sOutLetURL = "http://103.21.76.186:9099/outlets/";

    public static SparseArray<OutletInfo> sOutletData;

    private int mKey = 5;

    private static RequestData sRequestData;

    private boolean mSuccess = false;

    private final FragmentActivity mActivity;

    private RequestData(FragmentActivity activity) {

        this.mActivity = activity;
    }

    public static RequestData getInstance(FragmentActivity activity) {
        if (sRequestData == null) {
            sRequestData = new RequestData(activity);
        }
        return sRequestData;
    }

    public boolean request(String outletId) {

        System.out.println("@Nishanth: outletId is " + outletId + "  URL is  " + sUrl + outletId);
        final ProgressDialog pd = new ProgressDialog(LocateFoodBox.getInstance().getActivity());
        pd.setCancelable(false);
        pd.setMessage("Please Wait...");
        pd.show();
        JsonObjectRequest dataReq = new JsonObjectRequest(
                Request.Method.GET, sUrl + outletId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jArray = response.getJSONArray("restaurants");
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jObject = jArray.getJSONObject(i);
                                DataItems.DataItem data = new DataItems.DataItem(jObject.getString("name"),
                                        jObject.getString("image_path"), jObject.getInt("id"));
                                DataItems.ITEMS.add(data);
                            }
                            pd.dismiss();
                            RestaurantList restaurantList = RestaurantList.getInstance();
                            Bundle bundle = new Bundle();
                            bundle.putInt("key", mKey);
                            restaurantList.setArguments(bundle);
                            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.fragment_container, restaurantList).commit();
                            mSuccess = true;
                        } catch (JSONException e) {
                            Log.e("JSONException", "Error: " + e.toString());
                            pd.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("@Error", "Error: " + error.getMessage());
                pd.dismiss();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(dataReq);
        return mSuccess;
    }

    public void requestForFoodItems(Context context, int restaurentId, int outletId) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...");
        pd.show();
        Log.e("REQUEST", sUrlFoodItems + outletId + "/" + restaurentId);
        // Creating volley request obj
        JsonObjectRequest dataReq = new JsonObjectRequest(Request.Method.GET, sUrlFoodItems + outletId + "/" + restaurentId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if (FoodItems.VEG_ITEMS != null) {
                            FoodItems.VEG_ITEMS.clear();
                        }
                        if (FoodItems.RECOMMENDED_ITEMS != null) {
                            FoodItems.RECOMMENDED_ITEMS.clear();
                        }
                        if (FoodItems.NONVEG_ITEMS != null) {
                            FoodItems.NONVEG_ITEMS.clear();
                        }
                        if (FoodItems.SNACKS_ITEMS != null) {
                            FoodItems.SNACKS_ITEMS.clear();
                        }
                        // Parsing json
                        try {
                            JSONArray jArray = response.getJSONArray("fooditems");
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jObject = jArray.getJSONObject(i);
                                int id = jObject.getInt("id");
                                String name = jObject.getString("name");
                                FoodItems.FoodItem data = new FoodItems.FoodItem();
                                data.setId(id);
                                data.setTitle(name);
                                data.setConcat(jObject.getString("concat"));
                                data.setItem_tag(jObject.getString("item_tag"));
                                data.setExpiry_time(jObject.getString("expiry_time"));
                                data.setVeg(jObject.getBoolean("veg"));
                                data.setLocation(jObject.getString("location"));
                                data.setMrp(jObject.getInt("mrp"));
                                data.setMaster_id(jObject.getInt("master_id"));
                                data.setIssnacks(jObject.getString("issnacks"));
                                data.setIsrecommded(jObject.getString("isrecommded"));
                                if (jObject.getBoolean("veg") == true) {
                                    FoodItems.VEG_ITEMS.add(data);
                                } else if (!jObject.getString("isrecommded").equalsIgnoreCase("") &&
                                        jObject.getString("isrecommded").equalsIgnoreCase("true")) {
                                    FoodItems.RECOMMENDED_ITEMS.add(data);
                                } else if (!jObject.getString("issnacks").equalsIgnoreCase("") &&
                                        jObject.getString("issnacks").equalsIgnoreCase("true")) {
                                    FoodItems.SNACKS_ITEMS.add(data);
                                } else {
                                    FoodItems.NONVEG_ITEMS.add(data);
                                }

                            }
                            pd.dismiss();
                            FoodMenuFragment foodMenuFragment = new FoodMenuFragment();
                            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
                            transaction.addToBackStack(null);
                            transaction.replace(R.id.fragment_container, foodMenuFragment).commit();
                        } catch (JSONException e) {
                            Log.e("JSONException", "Error: " + e.toString());
                            pd.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("@Error", "Error: " + error.getMessage());
                pd.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(dataReq);
    }

    public void getOutlets(String cityCode) {
        final ProgressDialog pd = new ProgressDialog(LocateFoodBox.getInstance().getActivity());
        pd.setMessage("loading");
        pd.show();
        sOutletData = new SparseArray<>();
        JsonObjectRequest outletRequest = new JsonObjectRequest(Request.Method.GET, sOutLetURL + cityCode, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jArray = response
                            .getJSONArray("outlets");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jObject = jArray
                                .getJSONObject(i);
                        int id = jObject.getInt("id");
                        OutletInfo data = new OutletInfo(jObject
                                .getString("address"), jObject.getString("name"));
                        sOutletData.put(id, data);
                        pd.dismiss();
                    }
                } catch (JSONException e) {
                    Log.e("JSONException",
                            "Error: " + e.toString());
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("@Error",
                        "Error: " + error.getMessage());
                pd.dismiss();
            }
        });
        AppController.getInstance().addToRequestQueue(outletRequest);
    }

    public static class OutletInfo {
        public final String address;
        public final String outletName;

        public OutletInfo(String address, String outletName) {
            this.address = address;
            this.outletName = outletName;
        }
    }

}
