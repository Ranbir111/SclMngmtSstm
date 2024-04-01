package rvg.sclmngmtsstm.binHandel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class RecentHandler {
    public void setData(JSONObject dataObject) throws IOException, ParseException {
        File file = new File("./DataStorage/bin/recent.json");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(dataObject.toJSONString());
        fileWriter.close();
    }

    public JSONObject getData() throws IOException, ParseException {
        File file = new File("./DataStorage/bin/recent.json");
        if(file.length() != 0) {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(file));
            if (jsonObject.isEmpty()) {
                return null;
            }
            return jsonObject;
        }
        return null;
    }

    public void clearRecent() throws IOException {
        File file = new File("./DataStorage/bin/recent.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("");
        fileWriter.close();
    }
}
