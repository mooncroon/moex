package steps;

import io.restassured.path.json.JsonPath;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class MoexJSON {

    public static String TQBRurl = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.json?date=";

    public List<List<String>> getTQBRHistoryData(JsonPath json) {
        return json.getList("history.data");
    }

    public List<String> getColumnsData(JsonPath json) {

        return json.getList("history.columns");
    }

    public JsonPath getTQBRHByDate(String date, String tqbrUrl) {
        String url = String.format("%s%s", tqbrUrl,date);
        System.out.println(url);
        JsonPath values = when().get(url)
                .then().extract().jsonPath();
        return values;
    }
    
    public int getColumnIndex(List<String> columnsData,String columnName){
        return columnsData.indexOf(columnName);
    }
    
    public int getRecordIndex(List<List<String>> values){
        int index;
        for (index = 0; index < values.size(); index++) {
            if (values.get(index).get(2).equals("Аэрофлот")){
                break;
            }
        }
        return index;
    }

    /**
     * Не работает
     */
    public void printHistoryRecordValue(int recordIndex, int columnIndex, List<List<String>> values){
        System.out.println(values.get(recordIndex).get(columnIndex));
    }

    public void checkRecordValue(List<List<String>> values, int recordIndex, int columnIndex, String expectedValue){
        String actualValue = values.get(recordIndex).get(columnIndex);
        assertTrue(actualValue.equals(expectedValue));
    }
}
