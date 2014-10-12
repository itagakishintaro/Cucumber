package util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DateTimeUtilTest {
	@DataPoints
	public static Fixture[] PARAMS = {
			new Fixture("2014/10/12 09:00", "2014/10/12 18:00", 9 * 60),
			new Fixture("2014/10/12 00:00", "2014/10/12 24:00", 24 * 60),
			new Fixture("2014/10/12 00:00", "2014/10/12 25:00", 25 * 60),
			new Fixture("2014/10/12 09:00", "2014/10/13 09:00", 24 * 60),
			new Fixture("2014/10/13 09:00", "2014/10/12 09:00", -24 * 60),
			new Fixture("2012/02/28 09:00", "2012/03/01 09:00", 48 * 60), // うるう年
			new Fixture("2014/02/28 09:00", "2014/03/01 09:00", 24 * 60), 
			};

	@Theory
	public void calcMinutesで何分の差があるか計算できる(Fixture p) throws Exception {
		assertThat(DateTimeUtil.calcMinutes(p.from, p.to), is(p.expected));
	}

	static class Fixture {
		String from, to;
		int expected;

		Fixture(String from, String to, int expected) {
			this.from = from;
			this.to = to;
			this.expected = expected;
		}
	}
}
