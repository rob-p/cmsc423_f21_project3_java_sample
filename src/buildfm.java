import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class buildfm {

	public static void main(String[] args) {
		String input = args[0];
		String output = args[1];

		StringBuffer genome_buf = new StringBuffer();

		try(BufferedReader br = new BufferedReader(new FileReader(input))) {
			for(String line; (line = br.readLine()) != null; ) {
				if (!line.startsWith(">")) {
					genome_buf.append(line.toUpperCase().strip());
				}
			}
			// line is not visible here.
		} catch (IOException e) {
			e.printStackTrace();
		}
		genome_buf.append("$");
		String genome = genome_buf.toString();
		int[] sa = SuffixArray.constructSuffixArray(genome);

		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(output));
			out.writeObject(sa);
			out.writeBytes(genome);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.err.println("Couldn't close " + output);
			}
		}

		// Below is how the serialized file would be
		// read in.
	 	/*	
		ObjectInputStream in = null;
		String genome_in = null;
		int[] sa_in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(output));
			sa_in = (int[]) in.readObject();
			if (!java.util.Arrays.equals(sa_in, sa)) {
				System.out.println("suffix arrays don't match");
			}
			byte[] genomeBytes = new byte[sa_in.length];
			in.readFully(genomeBytes);
			genome_in = new String(genomeBytes);
			if (!genome_in.equals(genome)) {
				System.out.println("genomes don't match");
			}
			System.out.println("all matched!");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				System.err.println("Couldn't close " + output);
			}
		}
		*/
		
	}
	
}
