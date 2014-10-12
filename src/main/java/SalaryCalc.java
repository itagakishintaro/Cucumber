import static util.DateTimeUtil.calcMinutes;

public class SalaryCalc {
	int salaryPerHour;
	String nightFrom;
	double nightExtraRate;

	public SalaryCalc(int salaryPerHour, String nightFrom, double nightExtraRate) {
		this.salaryPerHour = salaryPerHour;
		this.nightFrom = nightFrom;
		this.nightExtraRate = nightExtraRate;
	}

	public Integer calc(String from, String to, int restMinutes, int nightRestMinutes) {
		int actualMinutes = calcMinutes(from, to) - restMinutes;
		int nightMinutes = calcMinutes(to.substring(0, 11) + nightFrom, to) - nightRestMinutes;
		if (nightMinutes > 0) {
			return (int) ((actualMinutes * salaryPerHour / 60)
					+ (nightMinutes * salaryPerHour * (nightExtraRate / 100) / 60));
		} else {
			return (int) (actualMinutes * salaryPerHour / 60);
		}		
	}

}
