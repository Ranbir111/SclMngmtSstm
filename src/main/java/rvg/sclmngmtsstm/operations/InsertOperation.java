package rvg.sclmngmtsstm.operations;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import rvg.sclmngmtsstm.modals.*;

public class InsertOperation {
    // All variable and reference variables are declared here
    File file;
    JSONObject jsonObject;
    Object obj;
    JSONArray jArray;
    FileWriter fileWriter;
    JSONObject idObj;
    int lastID;

    // Function to add a new student
    @SuppressWarnings("unchecked")
    public void addNewStudent(StudentInfoModal studentInfoModal) {

        file = new File("./DataStorage/studentData.json");
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (studentInfoModal != null) {
            try {
                obj = new JSONParser().parse(new FileReader(file));
                jArray = (JSONArray) obj;
                if (jArray.size() == 0) {
                    lastID = 0;
                } else {
                    idObj = (JSONObject) jArray.get(jArray.size() - 1);
                    lastID = Integer.parseInt(idObj.get("ID").toString());
                }

                jsonObject = new JSONObject();
                jsonObject.put("ID", lastID + 1);
                jsonObject.put("name", studentInfoModal.name);
                jsonObject.put("grade", studentInfoModal.grade);
                jsonObject.put("level", studentInfoModal.level);
                jsonObject.put("address", studentInfoModal.address);
                jsonObject.put("contact", studentInfoModal.contact);

                jArray.add(jsonObject);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jArray.toJSONString());
                fileWriter.close();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Function to add a new teacher
    @SuppressWarnings("unchecked")
    public void addNewTeacher(TeacherInfoModal teacherInfoModal) {
        file = new File("./DataStorage/teacherData.json");
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (teacherInfoModal != null) {
            try {
                obj = new JSONParser().parse(new FileReader(file));
                jArray = (JSONArray) obj;

                if (jArray.size() == 0) {
                    lastID = 0;
                } else {
                    idObj = (JSONObject) jArray.get(jArray.size() - 1);
                    lastID = Integer.parseInt(idObj.get("ID").toString());
                }

                jsonObject = new JSONObject();
                jsonObject.put("ID", lastID + 1);
                jsonObject.put("name", teacherInfoModal.name);
                jsonObject.put("subject", teacherInfoModal.subject);
                jsonObject.put("contact", teacherInfoModal.contact);

                jArray.add(jsonObject);
                fileWriter = new FileWriter(file);
                fileWriter.write(jArray.toJSONString());
                fileWriter.close();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Function to add a new course
    @SuppressWarnings("unchecked")
    public void addNewCourse(CourseInfoModal courseInfoModal) {
        file = new File("./DataStorage/courseData.json");
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (courseInfoModal != null) {
            try {
                obj = new JSONParser().parse(new FileReader(file));
                jArray = (JSONArray) obj;

                if (jArray.size() == 0) {
                    lastID = 0;
                } else {
                    idObj = (JSONObject) jArray.get(jArray.size() - 1);
                    lastID = Integer.parseInt(idObj.get("ID").toString());
                }

                jsonObject = new JSONObject();
                jsonObject.put("ID", lastID + 1);
                jsonObject.put("courseName", courseInfoModal.courseName);
                jsonObject.put("teacher", courseInfoModal.teacher);
                jsonObject.put("schedule", courseInfoModal.schedule);
                jsonObject.put("numOfStudInrolled", courseInfoModal.numOfStudInrolled);

                jArray.add(jsonObject);
                fileWriter = new FileWriter(file);
                fileWriter.write(jArray.toJSONString());
                fileWriter.close();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
