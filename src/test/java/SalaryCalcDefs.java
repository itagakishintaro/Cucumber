import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import cucumber.api.java.ja.ならば;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.前提;

public class SalaryCalcDefs {
	int salaryPerHour = 0;
	String nightFrom = "24:00";
	double nightExtraRate = 1.0;
	String from, to;
	int restMinutes = 0;
	int nightRestMinutes = 0;

	@前提("^時給(\\d+)円$")
	public void 時給_円(int salaryPerHour) throws Throwable {
		this.salaryPerHour = salaryPerHour;
	}

	@もし("^\"(.*?)\"から\"(.*?)\"働いた$")
	public void から_働いた(String from, String to) throws Throwable {
		this.from = from;
		this.to = to;
	}

	@もし("^休憩も割増もない$")
	public void 休憩も割増もない() throws Throwable {
	}

	@ならば("^給与は(\\d+)円であるべき$")
	public void 給与は_円であるべき(int salary) throws Throwable {
		SalaryCalc sut = new SalaryCalc(salaryPerHour, nightFrom,
				nightExtraRate);
		assertThat(sut.calc(from, to, restMinutes, nightRestMinutes), is(salary));
	}

	@もし("^(\\d+)分休憩した$")
	public void 分休憩した(int restMinutes) throws Throwable {
		this.restMinutes = restMinutes;
	}

	@前提("^深夜割増が\"(.*?)\"から発生し、深夜増分が(\\d+)%$")
	public void 深夜割増が_から発生し_深夜増分が(String nightFrom, int nightExtraRate)
			throws Throwable {
		this.nightFrom = nightFrom;
		this.nightExtraRate = nightExtraRate;
	}
	
	@もし("^日中に(\\d+)分、深夜に(\\d+)分休憩した$")
	public void 日中に_分_深夜に_分休憩した(int restMinutes, int nightRestMinutes) throws Throwable {
	    this.restMinutes = restMinutes;
	    this.nightRestMinutes = nightRestMinutes;
	}
}
