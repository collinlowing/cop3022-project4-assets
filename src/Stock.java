
public class Stock extends Asset
{
	private double fiveYearReturn;
	private double oneYearReturn;
	private double ninetyDayReturn;
	
	public Stock()
	{
		this("PKL", "Pickle, inc", 0, 0, 0);
	}
	
	public Stock(String symbol, String name, double fiveYearReturn, double oneYearReturn, double ninetyDayReturn)
	{
		super(symbol, name);
		
		this.fiveYearReturn = fiveYearReturn;
		this.oneYearReturn = oneYearReturn;
		this.ninetyDayReturn = ninetyDayReturn;
	}
	
	private double calcCompoundingInterest(double returnRate, double years)
	{
		return (super.getInvestment() * Math.pow(1 + returnRate, years));
	}
	
	@Override
	public int calcExpectedReturn()
	{
		if(this.fiveYearReturn != 0 && this.oneYearReturn != 0 && this.ninetyDayReturn != 0)
		{
			return (int) (calcCompoundingInterest(this.fiveYearReturn, 5) * 0.6 + calcCompoundingInterest(this.oneYearReturn, 1) * 0.2 + calcCompoundingInterest(this.ninetyDayReturn, 0.25) * 0.2);
		}
		
		else if(this.oneYearReturn != 0 && this.ninetyDayReturn != 0)
		{
			return (int) (calcCompoundingInterest(this.oneYearReturn, 1) * 0.6 + calcCompoundingInterest(this.ninetyDayReturn, 0.25) * 0.4);
		}
		return (int) super.getInvestment();
		
	}
}
