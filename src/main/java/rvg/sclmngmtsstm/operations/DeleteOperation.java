package operations;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DeleteOperation {
    // All variable and reference variables are declared here
    File file;
    JSONObject jsonObject;
    Object obj;
    JSONArray jArray;
    FileWriter fileWriter;

    // Function to delete student data
    public void deleteStudentData(int ID){
        if (ID != 0) {
            file = new File("./DataStorage/studentData.json");
            if (file.exists()) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    for(Object object : jArray){
                        if(Integer.parseInt(((JSONObject) object).get("ID").toString()) == ID){
                            jsonObject = (JSONObject) object;
                        }
                    }
                    jArray.remove(jsonObject);
                    fileWriter = new FileWriter(file);
                    fileWriter.write(jArray.toJSONString());
                    fileWriter.close();

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Function to delete teacher data
    public void deleteTeacherData(int ID){
        if (ID != 0) {
            file = new File("./DataStorage/teacherData.json");
            if (file.exists()) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    for(Object object : jArray){
                        if(Integer.parseInt(((JSONObject) object).get("ID").toString()) == ID){
                            jsonObject = (JSONObject) object;
                        }
                    }
                    jArray.remove(jsonObject);
                    fileWriter = new FileWriter(file);
                    fileWriter.write(jArray.toJSONString());
                    fileWriter.close();

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Function to delete course data
    public void deleteCourseData(int ID){
        if (ID != 0) {
            file = new File("./DataStorage/courseData.json");
            if (file.exists()) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    for(Object object : jArray){
                        if(Integer.parseInt(((JSONObject) object).get("ID").toString()) == ID){
                            jsonObject = (JSONObject) object;
                        }
                    }
                    jArray.remove(jsonObject);
                    fileWriter = new FileWriter(file);
                    fileWriter.write(jArray.toJSONString());
                    fileWriter.close();

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
