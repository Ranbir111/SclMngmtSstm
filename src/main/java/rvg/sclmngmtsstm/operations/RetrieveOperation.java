package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import modals.CourseInfoModal;
import modals.StudentInfoModal;
import modals.TeacherInfoModal;

public class RetrieveOperation {
    // All variable and reference variables are declared here
    File file;
    JSONObject jsonObject;
    Object obj;
    JSONArray jArray;

    // Function to retrieve student data
    public StudentInfoModal getStudentData(int ID) {
        if (ID != 0) {
            file = new File("./DataStorage/studentData.json");
            if (file.exists()) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    jsonObject = null;
                    for (Object find : jArray) {
                        if ((Integer.parseInt(((JSONObject) find).get("ID").toString())) == ID) {
                            jsonObject = (JSONObject) find;
                        }
                    }
                    return new StudentInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),
                            jsonObject.get("name").toString(), jsonObject.get("grade").toString(),
                            jsonObject.get("level").toString(), jsonObject.get("address").toString(),
                            jsonObject.get("contact").toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                return null;
            }
        } else {
            return null;
        }
        return null;
    }

    // Function to retrieve teacher data
    public TeacherInfoModal getTeacherData(int ID) {
        if (ID != 0) {
            file = new File("./DataStorage/teacherData.json");
            if (file.exists()) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    jsonObject = null;
                    for (Object find : jArray) {
                        if ((Integer.parseInt(((JSONObject) find).get("ID").toString())) == ID) {
                            jsonObject = (JSONObject) find;
                        }
                    }
                    return new TeacherInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),
                            jsonObject.get("name").toString(), jsonObject.get("subject").toString(),
                            jsonObject.get("contact").toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                return null;
            }
        } else {
            return null;
        }
        return null;
    }

    // Function to retrieve course data
    public CourseInfoModal getCourseData(int ID) {
        if (ID != 0) {
            file = new File("./DataStorage/courseData.json");
            if (file.exists()) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    jsonObject = null;
                    for (Object find : jArray) {
                        if ((Integer.parseInt(((JSONObject) find).get("ID").toString())) == ID) {
                            jsonObject = (JSONObject) find;
                        }
                    }
                    return new CourseInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),
                            jsonObject.get("courseName").toString(), jsonObject.get("teacher").toString(),
                            jsonObject.get("schedule").toString(),Integer.parseInt(jsonObject.get("numOfStudInrolled").toString()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                return null;
            }
        } else {
            return null;
        }
        return null;
    }
}
