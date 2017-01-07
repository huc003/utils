package com.huc.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JSONUtils {

	private boolean compress;
	private final static int INDENTFACTOR = 4;

	public JSONUtils() {
		compress = true;
	}

	public JSONUtils(boolean compress) {
		//this.compress = true;
		this.compress = compress;
	}

	public String toJSON(String root, Object object) throws Exception {
		JSONObject jsonObj = getJSON(root, object);
		return toJSON(jsonObj);
	}

	public String toJSON(JSONObject jsonObj) throws Exception {
		if (jsonObj == null)
			return "";
		if (compress){
			return jsonObj.toString();
		}else{
			return jsonObj.toString(INDENTFACTOR);
		}
	}

	public JSONObject getJSON(String root, Object object) throws Exception {
		if (root == null || root.trim().length() == 0)
			throw new Exception("root error");
		if (object == null) {
			return null;
		} else {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(root, object);
			return jsonObj;
		}
	}
	
	/**
	 *  将map集合转换成json字符串 ,不支持嵌套转换
	 * @param map map集合 键，值对应
	 * @return json字符串
	 */
	public static String toJson(Map<String,String> map){
		return new Gson().toJson(map);
	}
	
	public static <T> T toJsonString(String json,Class<T> classOfT){
		return new Gson().fromJson(json, classOfT);
	}
	
	public JSONObject string2JSON(String str, String split)
			throws JSONException {
		JSONObject json = new JSONObject();
		String arrStr[] = str.split(split);
		for (int i = 0; i < arrStr.length; i++) {
			String arrKeyValue[] = arrStr[i].split("=");
			json.put(arrKeyValue[0],
					arrStr[i].substring(arrKeyValue[0].length() + 1));
		}

		return json;
	}
	
	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * @param jsonString JSON字符串
	 * @return Map<code><</code>String, Object>
	 * @throws JSONException 
	 */
	public static Map<String, String> getJson2Map(String jsonString) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(jsonString);
		Iterator<?> keyIter = jsonObject.keys();
		String key;
		String value;
		Map<String, String> valueMap = new LinkedHashMap<String, String>();
		while (keyIter.hasNext())
		{
			key = String.valueOf(keyIter.next());
			value = jsonObject.getString(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}

}

