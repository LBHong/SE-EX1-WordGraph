import java.io.BufferedWriter;
import java.io.FileWriter;

public class ShowGraph {
	static String newLine=System.getProperty("line.separator");
	public static void showDirectedGraph(Graph G) {
		StringBuilder dotText=new StringBuilder();
		dotText.append(String.format("digraph G{"+newLine));
		for(Node node:G.getNodeList()) {
			dotText.append(node.name);
			if(!node.color.equals("black"))dotText.append(String.format(" [style=filled, fillcolor=%s]",node.color));
			dotText.append(";"+newLine);
		}
		dotText.append(newLine);
		for(Node node:G.getNodeList()) {
			for(Edge edge:node.edges) {
				dotText.append(String.format("%s->%s[label=%d]", edge.from,edge.to,edge.weight));
				if(!edge.color.equals("black"))dotText.append(String.format("[style=bold, color=%s]",edge.color));
				dotText.append(";"+newLine);
			}
		}
		dotText.append("}"+newLine);
		
		String dotFilename=Config.tmpPath+"graph.gv";
		try {
			Runtime.getRuntime().exec("cmd /c if not exist \""+Config.tmpPath+"\" md \""+Config.tmpPath+"\"").waitFor();
			FileWriter fw=new FileWriter(dotFilename);
			BufferedWriter bufw = new BufferedWriter(fw);
			bufw.write(dotText.toString());
			bufw.close();
		}catch (Exception e) {
			throw new RuntimeException("Failed to open file");
		}
		
		generateImage(dotFilename);
	}

	private static void generateImage(String filename) {
		Runtime rt=Runtime.getRuntime();
		
		try {
			String[] args= {Config.dotForWindows,filename,"-Tpng","-o",Config.tmpPath+"img.png"};
			Process process = rt.exec(args);
			process.waitFor();
			
		}catch (Exception e) {
			throw new RuntimeException("Failed to generate image.");
		}
	}
	
}