import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SalaryCalcTest {
	@RunWith(Theories.class)
	public static class 休憩も割増もない場合{
		@DataPoints
		public static Fixture[] PARAMS = {
			new Fixture(1000,"2014/10/12 09:00","2014/10/12 10:00",1000),
			new Fixture(1000,"2014/10/12 09:00","2014/10/12 12:00",3000),
			new Fixture(1000,"2014/10/12 13:00","2014/10/12 17:00",4000),
		};
		@Theory
		public void calcで給与計算できる(Fixture p) throws Exception{
			SalaryCalc sut = new SalaryCalc(p.salaryPerHour, "24:00", 0);
			assertThat(sut.calc(p.from, p.to, 0, 0), is(p.expected));
		}
		
		static class Fixture{
			int salaryPerHour;
			String from, to;
			int expected;
			
			Fixture(int salaryPerHour, String from, String to, int expected){
				this.salaryPerHour = salaryPerHour;
				this.from = from;
				this.to = to;
				this.expected = expected;
			}
		}
	}
	
	@RunWith(Theories.class)
	public static class 休憩時間がある場合{
		@DataPoints
		public static Fixture[] PARAMs = {			
			new Fixture(1000,"2014/10/12 09:00","2014/10/12 18:00",60,8000),
			new Fixture(1000,"2014/10/12 09:00","2014/10/12 19:00",120,8000),
			new Fixture(1000,"2014/10/12 09:00","2014/10/12 23:00",150,11500),
		};
		@Theory
		public void calcで給与計算できる(Fixture p) throws Exception{
			SalaryCalc sut = new SalaryCalc(p.salaryPerHour, "24:00", 0);
			assertThat(sut.calc(p.from, p.to, p.restMinutes, 0), is(p.expected));
		}
		
		static class Fixture{
			int salaryPerHour;
			String from, to;
			int restMinutes;
			int expected;
			
			Fixture(int salaryPerHour, String from, String to, int restMinutes, int expected){
				this.salaryPerHour = salaryPerHour;
				this.from = from;
				this.to = to;
				this.restMinutes = restMinutes;
				this.expected = expected;
			}
		}
	}

	@RunWith(Theories.class)
	public static class 深夜割増と休憩がある場合{
		@DataPoints
		public static Fixture[] PARAMs = {			
			new Fixture(1000,"22:00",10,"2014/10/12 09:00","2014/10/12 18:00",60,0,8000),
			new Fixture(1000,"22:00",10,"2014/10/12 09:00","2014/10/12 19:00",120,0,8000),
			new Fixture(1000,"22:00",10,"2014/10/12 09:00","2014/10/12 23:00",120,30,12050),
		};
		@Theory
		public void calcで給与計算できる(Fixture p) throws Exception{
			SalaryCalc sut = new SalaryCalc(p.salaryPerHour, p.nightFrom, p.nightExtraRate);
			assertThat(sut.calc(p.from, p.to, p.restMinutes, p.nightRestMinutes), is(p.expected));
		}
		
		static class Fixture{
			int salaryPerHour;
			String nightFrom;
			int nightExtraRate;
			String from, to;
			int restMinutes;
			int nightRestMinutes;
			int expected;
			
			Fixture(int salaryPerHour, String nightFrom, int nightExtraRate, String from, String to, int restMinutes,int nightRestMinutes, int expected){
				this.salaryPerHour = salaryPerHour;
				this.nightFrom = nightFrom;
				this.nightExtraRate = nightExtraRate;
				this.from = from;
				this.to = to;
				this.restMinutes = restMinutes;
				this.nightRestMinutes = nightRestMinutes;
				this.expected = expected;
			}
		}
	}
}
