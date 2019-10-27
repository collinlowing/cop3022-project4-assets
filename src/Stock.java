
public class Stock extends Asset
{
	private double fiveYearReturn;
	private double oneYearReturn;
	private double ninetyDayReturn;
	
	private double fiveYearReturnRate;
	private double oneYearReturnRate;
	private double ninetyDayReturnRate;
	
	private int expectedReturn;
	
	public Stock()
	{
		this("PKL", "Pickle, inc", 0, 0, 0);
	}
	
	public Stock(String symbol, String name, double fiveYearReturn, double oneYearReturn, double ninetyDayReturn)
	{
		super(symbol, name);
		
		this.fiveYearReturnRate = fiveYearReturn;
		this.oneYearReturnRate = oneYearReturn;
		this.ninetyDayReturnRate = ninetyDayReturn;
	}
	
	private double calcCompoundingInterest(double returnRate, double years)
	{
		return (super.getInvestment() * Math.pow(1 + returnRate, years));
	}
	
	@Override
	public void calcExpectedReturn()
	{
		if(this.fiveYearReturn != 0 && this.oneYearReturn != 0 && this.ninetyDayReturn != 0)
		{
			this.expectedReturn = (int) (calcCompoundingInterest(this.fiveYearReturn, 5) * 0.6 + calcCompoundingInterest(this.oneYearReturn, 1) * 0.2 + calcCompoundingInterest(this.ninetyDayReturn, 0.25) * 0.2);
		}
		
		else if(this.oneYearReturn != 0 && this.ninetyDayReturn != 0)
		{
			this.expectedReturn = (int) (calcCompoundingInterest(this.oneYearReturn, 1) * 0.6 + calcCompoundingInterest(this.ninetyDayReturn, 0.25) * 0.4);
		} 
	}
	
	public String toString()
	{
		return "";
	}
}
