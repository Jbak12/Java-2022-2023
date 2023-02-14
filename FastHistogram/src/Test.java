import java.util.Map;
import java.util.Random;

public class Test
{
	public static void main(String[] args)
	{
		Random rd = new Random();
		rd.setSeed(134314341);
		int a = 10000000;
		CustomVector b = new CustomVector(a);
		Histogram c = new FastHistogram();
		
		for(int i =0; i < a; i++)
		{
			b.examplevector[i] = rd.nextInt(500);
		}
		
		c.setup(5, 500);
		c.setVector(b);
		
		try {
			while(true) {
				if(!(c.isReady()))
					System.out.println("czekam.");
				Thread.sleep(400);
				if(c.isReady())
					break;
			}
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<Integer, Integer> d = c.histogram();
		int f = 0;
		for (Map.Entry<Integer, Integer> e : d.entrySet())
		{
			System.out.println(e.getKey() + " " + e.getValue());
			f+= e.getValue(); 
		}
		System.out.println(f);
	}
}