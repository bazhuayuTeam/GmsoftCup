package com.cqut.util.json;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class JsonUtil {

	public static JSONObject getJSONObject(String str) {
		if (str == null || str.trim().length() == 0)
			return null;
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(str);
		} catch (JSONException e) {
			e.printStackTrace(System.err);
		}
		return jsonObject;
	}
	
	public static JSONObject recived2Json(String str) {
		if (str == null || str.trim().length() == 0)
			return null;
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(str);
		} catch (JSONException e) {
			e.printStackTrace(System.err);
		}
		return jsonObject;
	}
}
