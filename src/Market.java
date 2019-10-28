import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;



public class Market 
{
	private Asset [] assets;
	private Asset [] invest;
	private int filledAssets = 0;
	private int filledInvest = 0;
	
	public boolean readFile(String fileName)
	{
		double [] rate = new double[3];
		boolean canRead = true;
		String line;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			int fileSize = countLines(fileName);
			
			assets = new Asset[fileSize];
			invest = new Asset[fileSize];
			
			while((line = reader.readLine()) != null)
			{
				String [] assetData = line.split(",");
				
				if(assetData.length == 5 && isDouble(assetData[2]) && isDouble(assetData[3]) && isDouble(assetData[4]))
				{
					for(int i = 2; i < 5; i++)
					{
						int j = 0;
						rate[j] = Double.parseDouble(assetData[i]);
						j++;
					}
					assets[filledAssets] = new Stock(assetData[0], assetData[1], rate[0], rate[1], rate[2]);
					filledAssets++;
				}
				else if(assetData.length == 4 && isDouble(assetData[2]) && isDouble(assetData[3]))
				{
					for(int i = 2; i < 4; i++)
					{
						int j = 0;
						rate[j] = Double.parseDouble(assetData[i]);
						j++;
					}
					assets[filledAssets] = new Stock(assetData[0], assetData[1], rate[0], rate[1], 0);
					filledAssets++;
				}
				else if(isDouble(assetData[2]))
				{
					rate[0] = Double.parseDouble(assetData[2]);
					assets[filledAssets] = new StableAsset(assetData[0], assetData[1], rate[0]);
					filledAssets++;
				}
			}
		}
		catch (IOException e)
		{
			System.err.println("File could not be opened, please try again.");
			canRead = false;
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			canRead = false;
		}
		return canRead;
	}
	
	public boolean investInAsset(String symbol, int amount)
	{
		for(int i = 0; i < filledAssets; i++)
		{
			if(assets[i].getSymbol().equals(symbol))
			{
				invest[filledInvest] = assets[i];
				invest[filledInvest].setInvestment(amount);
				filledInvest++;
				return true;
			}
		}
		return false;
	}
	
	public int countLines(String fileName)
	{
		int count = 0;
		
		try(FileInputStream infs = new FileInputStream(fileName))
		{
			Scanner in = new Scanner(infs);
			
			while(in.hasNextLine())
			{
				count++;
				in.nextLine();
			}
		} 
		catch (IOException e)
		{
			System.err.println("Count: File could not be opened, please try again.");
		}
		return count;
	}
	
	public boolean isDouble(String string)
	{
		boolean isNum = true;
		try
		{
			Double num = Double.parseDouble(string);
		}
		catch (NumberFormatException e)
		{
			isNum = false;
		}
		
		return isNum;
	}
	
	public int getInvestTotal()
	{
		int total = 0;
		for(int i = 0; i < filledInvest; i++)
		{
			total += (int) invest[i].getInvestment();
		}
		return total;
	}
	
	public int getExpectedTotal()
	{
		int total = 0;
		for(int i = 0; i < filledInvest; i++)
		{
			total += (int) invest[i].calcExpectedReturn();
		}
		return total;
	}
	
	public String printAssets()
	{
		String output;
		
		output = "Available assets for investment\n"
				+"-------------------------------\n";
		for(int i = 0; i < filledAssets; i++)
		{
			output += assets[i].toString() + "\n";
		}
		
		return output;
	}
	
	public String printInvest()
	{
		String output;
		output = "+--------------+-----------------+--------------------+\n"
				+ "| ASSET SYMBOL | AMOUNT INVESTED | VALUE IN TEN YEARS |\n"
				+ "+==============+=================+====================+\n";
		for(int i = 0; i < filledInvest; i++)
		{
			output += String.format("| %-13s| %-16d| %-19d|\n", invest[i].getSymbol(), invest[i].getInvestment(), invest[i].calcExpectedReturn());
		}
		output += "+--------------+-----------------+--------------------+"
				+ String.format("| %-13s| %-16d| %-19d|\n", "TOTAL", getInvestTotal(), getExpectedTotal())
				+ "+--------------+-----------------+--------------------+";
		
		return output;
	}
	
	public void outputFile()
	{
		String outFileName = "portfolio.txt";
		
		try(PrintWriter out = new PrintWriter(outFileName, "UTF-8"))
		{
			out.print(printInvest());
			out.close();
		}
		catch(IOException e)
		{
			System.err.println("portfolio.txt could not be written");
		}
		
		
	}
}
