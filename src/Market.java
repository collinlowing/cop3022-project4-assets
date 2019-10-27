import java.io.BufferedReader;
import java.io.FileReader;

public class Market 
{
	private Asset [] assets;
	private Asset [] invest;
	private String fileName;
	
	public Market(String fileName)
	{
		this.fileName = fileName;
	}
	
	public void readFile() throws Exception
	{
		String line;
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
		{
			while((line = reader.readLine()) != null)
			{
				String [] assetData = line.split(",");
				//if()
				//{
					// 
				//}
			}
			reader.close();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());
		}
		
	}
	
	public String printAssets()
	{
		return "";
	}
	
	public String toString()
	{
		return "";
	}
}
