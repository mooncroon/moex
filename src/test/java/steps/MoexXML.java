package steps;

import io.restassured.path.xml.XmlPath;
import static io.restassured.RestAssured.*;


public class MoexXML {
    public static String TQBRurl = "http://iss.moex.com/iss/history/engines/stock/markets/shares/boards/tqbr/securities.xml?date=";

    public XmlPath getTQBRHByDate(String date, String tqbrUrl) {
        String url = String.format("%s%s", tqbrUrl,date);
        System.out.println(url);
        XmlPath values = when().get(url)
                .then().extract().xmlPath();
        System.out.println(values);
        return values;
    }
}
