package com.itestin.guitest.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.sf.json.JSONObject;

public class ReadJson
{
    private String ReadFile()
    {
        String jsonData = "";
        BufferedReader reader = null;
        try
        {
            InputStream inputStream = getClass().getResourceAsStream("/sendAndRecive.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                jsonData = jsonData + tempString;
            }
            reader.close();

            return jsonData;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null) {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return jsonData;
    }

    private static String AnalyJson(String jsonData)
    {
        JSONObject json = JSONObject.fromObject(jsonData);
        String jsondata = json.getString("Recipients");
        jsondata = jsondata.replace("[", "").replace("]", "");
        return jsondata;
    }

    public static String getEmailAccounts()
    {
        ReadJson readJson = new ReadJson();
        String accounts = readJson.ReadFile();
        return AnalyJson(accounts);
    }
}
