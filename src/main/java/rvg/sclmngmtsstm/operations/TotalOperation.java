package rvg.sclmngmtsstm.operations;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TotalOperation {
    public int getNoOfStudent() throws IOException, ParseException {
        File file = new File("./DataStorage/studentData.json");
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
        return jsonArray.size();
    }
    public int getNoOfTeacher() throws IOException, ParseException {
        File file = new File("./DataStorage/teacherData.json");
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
        return jsonArray.size();
    }
    public int getNoOfCourse() throws IOException, ParseException {
        File file = new File("./DataStorage/courseData.json");
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(file));
        return jsonArray.size();
    }
}
