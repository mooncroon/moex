package tests;

import io.restassured.path.json.JsonPath;
import org.junit.Test;
import steps.DateUtil;
import steps.MoexJSON;
import java.util.List;

public class AppTest {

    @Test
    public void firstTest() {
        MoexJSON moexJSON = new MoexJSON();

        String date = DateUtil.getTodayDate();
        JsonPath json = moexJSON.getTQBRHByDate(date, MoexJSON.TQBRurl);
        List<List<String>> historyData = moexJSON.getTQBRHistoryData(json);
        List<String> columnsData = moexJSON.getColumnsData(json);

        int recordIndex = moexJSON.getRecordIndex(historyData);
        int columnIndexClose = moexJSON.getColumnIndex(columnsData, "CLOSE");
        int columnIndexTradeDate = moexJSON.getColumnIndex(columnsData, "TRADEDATE");
    //    moexREST.printHistoryRecordValue(recordIndex,columnIndexClose,historyData);
        moexJSON.checkRecordValue(historyData, recordIndex, columnIndexTradeDate, date);
    }

    @Test
    public void secondTest() {

    }
}
