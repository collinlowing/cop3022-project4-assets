import java.util.Scanner;

public class UI 
{

	public static void main(String[] args)
	{
		int investAmount = 0;
		String symbol;
		String fileName;
		
		Market m = new Market();
		Scanner user = new Scanner(System.in);
		
		do
		{
			System.out.println("Enter the file name and extention of data:");
			fileName = user.next();
		}
		while(!m.readFile(fileName));
		
		System.out.println(m.printAssets());
		
		while(true)
		{
			System.out.println("Enter the amount to invest in dollars:");
			investAmount = user.nextInt();
			
			if(investAmount < 0)
			{
				break;
			}
			
			System.out.println("Enter the asset symbol to invest in:");
			symbol = user.next();
			
			while(!m.investInAsset(symbol, investAmount))
			{
				symbol = user.next();
				
				if(m.investInAsset(symbol, investAmount))
				{
					System.out.println("Investing " + investAmount + " in " + symbol + "has an expected future value of: " + m.getExpectedTotal());
				}
				else
				{
					System.err.println(symbol + " is not in the input, or had invalid input data. Choose something else to invest in.");
				}
			}
		}
		
		m.outputFile();
	}

}
