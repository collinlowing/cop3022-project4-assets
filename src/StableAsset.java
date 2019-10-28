
public class StableAsset extends Asset
{
	private double yearlyReturn;
	
	public StableAsset(String symbol, String name, double yearlyReturn)
	{
		super(symbol, name);
		this.yearlyReturn = yearlyReturn;
	}
	
	@Override
	public int calcExpectedReturn()
	{
		return (int) (super.getInvestment() * Math.pow(this.yearlyReturn, 10));
	}
}
