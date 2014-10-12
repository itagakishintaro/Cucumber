package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	static public int calcMinutes(String from, String to) {
		Date dateFrom = null;
		Date dateTo = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		// 日付を作成します。
		try {
			dateFrom = f.parse(from);
			dateTo = f.parse(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 日付をlong値に変換します。
		long dateTimeFrom = dateFrom.getTime();
		long dateTimeTo = dateTo.getTime();

		// 差分の時間（分）を算出します。
		return (int) (dateTimeTo - dateTimeFrom) / (1000 * 60);
	}
}
