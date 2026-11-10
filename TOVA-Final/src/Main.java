import java.io.File;
import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		File file = new File(System.getProperty("user.home") + File.separator + 
				"Documents" + File.separator + "TOVA_data" + 
				File.separator + "temp.txt");
		
		System.out.println(file.getPath());
		
		if (file.getParentFile().mkdir()) {
		    try {
				file.createNewFile();
				file.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("TOVA_data Folder already made");
		    //throw new IOException("Failed to create directory " + file.getParent());
		}
		
		String x = file.getPath();
		
		System.out.println(x);
		
		x = x.substring(0, x.length()-9);
		
		System.out.println(x);
		
		MyFrame frame = new MyFrame(x);
		
		UIDriver driver = new UIDriver(frame);
		
		//Thread thread = new Thread(driver);
		frame.setDriver(driver);
		
		//thread.start();
    }
}

/*
2 seconds inbetween each target aperence 100 ms

36 targets and 126 non targets for block 1 out of 162, block 2 is flipped each block is split into 2 subsets

*/