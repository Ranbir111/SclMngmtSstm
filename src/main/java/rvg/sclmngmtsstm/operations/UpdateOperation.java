package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import modals.CourseInfoModal;
import modals.StudentInfoModal;
import modals.TeacherInfoModal;

public class UpdateOperation {
    // All variable and reference variables are declared here
    File file;
    JSONObject oldObject,newObject;
    Object obj;
    JSONArray jArray;
    FileWriter fileWriter;

    // Function to update student data
    @SuppressWarnings("unchecked")
    public void updateStudentData(StudentInfoModal newStudentData){
        if(newStudentData != null){
            file = new File("./DataStorage/studentData.json");
            if (file.exists() && newStudentData.ID != 0) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    for(Object object : jArray){
                        if(Integer.parseInt((((JSONObject) object).get("ID")).toString()) == newStudentData.ID){
                            oldObject = (JSONObject) object;
                        }
                    }

                    jArray.remove(oldObject);

                    newObject = new JSONObject();
                    newObject.put("ID", newStudentData.ID);
                    newObject.put("name", newStudentData.name);
                    newObject.put("grade", newStudentData.grade);
                    newObject.put("level", newStudentData.level);
                    newObject.put("address", newStudentData.address);
                    newObject.put("contact", newStudentData.contact);

                    jArray.add(newObject);
                    fileWriter = new FileWriter(file);
                    fileWriter.write(jArray.toJSONString());   
                    fileWriter.close();                 
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Function to update teacher data
    @SuppressWarnings("unchecked")
    public void updateTeacherData(TeacherInfoModal newTeacherData){
        if(newTeacherData != null){
            file = new File("./DataStorage/teacherData.json");
            if (file.exists() && newTeacherData.ID != 0) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    for(Object object : jArray){
                        if(Integer.parseInt((((JSONObject) object).get("ID")).toString()) == newTeacherData.ID){
                            oldObject = (JSONObject) object;
                        }
                    }

                    jArray.remove(oldObject);

                    newObject = new JSONObject();
                    newObject.put("ID", newTeacherData.ID);
                    newObject.put("name", newTeacherData.name);
                    newObject.put("subject", newTeacherData.subject);
                    newObject.put("contact", newTeacherData.contact);

                    jArray.add(newObject);
                    fileWriter = new FileWriter(file);
                    fileWriter.write(jArray.toJSONString());   
                    fileWriter.close();                 
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Function to update course data
    @SuppressWarnings("unchecked")
    public void updateCourseData(CourseInfoModal newCourseData){
        if(newCourseData != null){
            file = new File("./DataStorage/courseData.json");
            if (file.exists() && newCourseData.ID != 0) {
                try {
                    obj = new JSONParser().parse(new FileReader(file));
                    jArray = (JSONArray) obj;

                    for(Object object : jArray){
                        if(Integer.parseInt((((JSONObject) object).get("ID")).toString()) == newCourseData.ID){
                            oldObject = (JSONObject) object;
                        }
                    }

                    jArray.remove(oldObject);

                    newObject = new JSONObject();
                    newObject.put("ID", newCourseData.ID);
                    newObject.put("courseName", newCourseData.courseName);
                    newObject.put("teacher", newCourseData.teacher);
                    newObject.put("schedule", newCourseData.schedule);
                    newObject.put("numOfStudInrolled", newCourseData.numOfStudInrolled);

                    jArray.add(newObject);
                    fileWriter = new FileWriter(file);
                    fileWriter.write(jArray.toJSONString());   
                    fileWriter.close();                 
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
