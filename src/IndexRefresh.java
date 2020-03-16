import javax.swing.JFrame;

public class IndexRefresh {

	JFrame index;

	public IndexRefresh(JFrame index) {
		this.index = index;
		
		Index newIndex = new Index();
		newIndex.frmKeptang.setLocationRelativeTo(index);
		index.dispose();
		newIndex.frmKeptang.setVisible(true);
		
	};
	
	
	
}
