
public abstract class Asset 
{
	private String symbol;
	private String name;
	private double investmentAmount;

	public Asset(String symbol, String name) 
	{
		this.symbol = symbol;
		this.name = name;
		this.investmentAmount = 0;
	}
	
	public void setInvestment(double amount)
	{
		this.investmentAmount = amount;
	}
	
	public double getInvestment()
	{
		return this.investmentAmount;
	}
	
	public abstract void calcExpectedReturn();
	
	public String toString()
	{
		return "";
	}
}
