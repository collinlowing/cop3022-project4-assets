
public class StableAsset extends Asset
{
	private double yearlyReturn;
	private int expectedReturn;
	
	public StableAsset(String symbol, String name, double yearlyReturn)
	{
		super(symbol, name);
		this.yearlyReturn = yearlyReturn;
	}
	
	@Override
	public void calcExpectedReturn()
	{
		expectedReturn = (int) (super.getInvestment() * Math.pow(this.yearlyReturn, 10));
	}
	
	public String toString()
	{
		return "";
	}
}
