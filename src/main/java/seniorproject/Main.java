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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;

public class Main {

	protected Shell shell;
	private Text txtThisIsWhere;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * 
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

		Composite CustomerComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(CustomerComposite);

		Composite NewAcctComp = new Composite(CustomerComposite, SWT.NONE);
		NewAcctComp.setBounds(41, 24, 359, 168);
		NewAcctComp.setVisible(false);

		txtThisIsWhere = new Text(NewAcctComp, SWT.BORDER);
		txtThisIsWhere.setText("This is where to create a new customer account");
		txtThisIsWhere.setBounds(33, 124, 281, 34);

		Label lblNewLabel = new Label(NewAcctComp, SWT.NONE);
		lblNewLabel.setBounds(35, 34, 55, 15);
		lblNewLabel.setText("Name");

		Label lblNewLabel_1 = new Label(NewAcctComp, SWT.NONE);
		lblNewLabel_1.setBounds(35, 55, 55, 15);
		lblNewLabel_1.setText("Phone #");

		text = new Text(NewAcctComp, SWT.BORDER);
		text.setBounds(119, 28, 76, 21);

		text_1 = new Text(NewAcctComp, SWT.BORDER);
		text_1.setBounds(119, 55, 76, 21);

		Button btnNewButton_2 = new Button(NewAcctComp, SWT.NONE);
		btnNewButton_2.setBounds(33, 92, 117, 25);
		btnNewButton_2.setText("Create Customer");

		Button btnClear = new Button(NewAcctComp, SWT.NONE);
		btnClear.setBounds(187, 93, 75, 25);
		btnClear.setText("Clear");

		Button btnX = new Button(NewAcctComp, SWT.NONE);

		btnX.setBounds(321, 10, 28, 25);
		btnX.setText("X");

		Composite Cust2ButtonComp = new Composite(CustomerComposite, SWT.NONE);
		Cust2ButtonComp.setBounds(0, 0, 426, 232);

		Button btnNewButton = new Button(Cust2ButtonComp, SWT.NONE);

		btnNewButton.setText("Search");
		btnNewButton.setBounds(57, 82, 113, 85);

		Button btnNewButton_1 = new Button(Cust2ButtonComp, SWT.NONE);

		btnNewButton_1.setText("New Account");
		btnNewButton_1.setBounds(232, 86, 150, 77);

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

		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : New Account");
				NewAcctComp.setVisible(true);
				Cust2ButtonComp.setEnabled(false);
			}
		});

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Search Customer");
			}
		});

		btnX.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X");
				NewAcctComp.setVisible(false);
				Cust2ButtonComp.setEnabled(true);
			}
		});
	}
}