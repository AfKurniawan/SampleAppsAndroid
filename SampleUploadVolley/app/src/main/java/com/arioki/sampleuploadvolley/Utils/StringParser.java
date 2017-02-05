package com.arioki.sampleuploadvolley.Utils;

/**
 * SampleUploadVolley Created by arioki on 05/02/2017.
 */
import org.json.JSONException;
import org.json.JSONObject;

import heends.uploadfile.Template.Template;

/**
 * Created by -H- on 10/12/2015.
 */
public class StringParser {


    public static String getCode(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            return String.valueOf(jsonObject.getInt(Template.Query.KEY_CODE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMessage(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(Template.Query.KEY_MESSAGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}