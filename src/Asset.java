
public abstract class Asset 
{
	private String symbol;
	private String name;
	private int investmentAmount;

	public Asset(String symbol, String name) 
	{
		this.symbol = symbol;
		this.name = name;
		this.investmentAmount = 0;
	}
	
	public String getSymbol()
	{
		return this.symbol;
	}
	
	public void setInvestment(int amount)
	{
		this.investmentAmount += amount;
	}
	
	public int getInvestment()
	{
		return this.investmentAmount;
	}
	
	public abstract int calcExpectedReturn();
	
	public String toString()
	{
		return "\t" + this.name + "(" + this.symbol + ")";
	}
}
