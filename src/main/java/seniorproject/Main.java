package seniorproject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;

public class Main {

	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 0, 434, 260);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, 0);
		tbtmNewItem.setText("Customer");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(226, 38, 76, 21);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(226, 99, 76, 21);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Username");
		lblNewLabel.setBounds(101, 38, 55, 15);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("Password");
		lblNewLabel_1.setBounds(101, 99, 55, 15);
		
		Button btnLogin = new Button(composite, SWT.NONE);
		btnLogin.setText("Login");
		btnLogin.setBounds(158, 168, 75, 25);
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, 0);
		tbtmNewItem_1.setText("Products");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_1);
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, 0);
		tbtmNewItem_2.setText("Cart");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_2);
		
		TabItem tbtmNewItem_3 = new TabItem(tabFolder, 0);
		tbtmNewItem_3.setText("Inventory");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_3.setControl(composite_3);
		
		TabItem tbtmNewItem_4 = new TabItem(tabFolder, 0);
		tbtmNewItem_4.setText("Work Orders");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_4.setControl(composite_4);

	}
}