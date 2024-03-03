package rvg.sclmngmtsstm.operations;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SearchOperation {
    File file1, file2, file3;
    JSONArray jsonArray1,jsonArray2,jsonArray3, matchArray;
    JSONObject jsonObject;
    Object obj1, obj2, obj3;
    
    // Function to searchData
    @SuppressWarnings("unchecked")
    public JSONArray searchData(String str) {
        str = str.toLowerCase();
        file1 = new File("./DataStorage/courseData.json");
        file2 = new File("./DataStorage/studentData.json");
        file3 = new File("./DataStorage/teacherData.json");
        matchArray = new JSONArray();
        try {
            obj1 = new JSONParser().parse(new FileReader(file1));
            obj2 = new JSONParser().parse(new FileReader(file2));
            obj3 = new JSONParser().parse(new FileReader(file3));
            jsonArray1 = (JSONArray) obj1;
            jsonArray2 = (JSONArray) obj2;
            jsonArray3 = (JSONArray) obj3;
            // jsonArray.add((JSONArray) obj2);
            // jsonArray.add((JSONArray) obj3);
            for (Object object : jsonArray1) {
                if (((JSONObject) object).get("courseName").toString().toLowerCase().contains(str)) {
                    matchArray.add((JSONObject) object);
                }
            }
            for (Object object : jsonArray2) {
                if (((JSONObject) object).get("name").toString().toLowerCase().contains(str)) {
                    matchArray.add((JSONObject) object);
                }
            }
            for (Object object : jsonArray3) {
                if (((JSONObject) object).get("name").toString().toLowerCase().contains(str)) {
                    matchArray.add((JSONObject) object);
                }
            }
            return matchArray;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
