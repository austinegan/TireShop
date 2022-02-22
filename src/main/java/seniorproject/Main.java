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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;

public class Main {

	protected Shell shell;
	private Text txtThisIsWhere;
	private Text searchName;
	private Text searchPhone;
	private Text newNameTextbox;
	private Text newPhoneNumber;
	private Text newAddress;
	private Text newEmail;
	private Text newNameErrorTextbox;
	private Text newPhoneNumberError;
	private Text newAddressError;
	private Text newEmailError;
	private Text text_2;
	private Text searchAddress;
	private Text searchEmail;
	private Text searchNameError;
	private Text searchPhoneError;
	private Text searchAddressError;
	private Text searchEmailError;
	private Text txtCurrentCustomer;

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
		shell.setSize(967, 695);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		TabItem tbtmNewItem = new TabItem(tabFolder, 0);
		tbtmNewItem.setText("Customer");

		Composite CustomerComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(CustomerComposite);
		CustomerComposite.setLayout(new FormLayout());
		
		Composite SearchAcctComp = new Composite(CustomerComposite, SWT.NONE);
		FormData fd_SearchAcctComp = new FormData();
		fd_SearchAcctComp.left = new FormAttachment(0, 51);
		fd_SearchAcctComp.bottom = new FormAttachment(100, -46);
		fd_SearchAcctComp.right = new FormAttachment(0, 468);
		SearchAcctComp.setLayoutData(fd_SearchAcctComp);
		SearchAcctComp.setLayout(new GridLayout(3, false));

		Composite NewAcctComp = new Composite(CustomerComposite, SWT.NONE);
		NewAcctComp.setLayout(new GridLayout(4, false));
		FormData fd_NewAcctComp = new FormData();
		fd_NewAcctComp.bottom = new FormAttachment(SearchAcctComp, 0, SWT.BOTTOM);
		fd_NewAcctComp.top = new FormAttachment(SearchAcctComp, 0, SWT.TOP);
		fd_NewAcctComp.left = new FormAttachment(SearchAcctComp, 28);
		fd_NewAcctComp.right = new FormAttachment(0, 896);
		NewAcctComp.setLayoutData(fd_NewAcctComp);
																new Label(NewAcctComp, SWT.NONE);
																
																CLabel lblCreateANew = new CLabel(NewAcctComp, SWT.NONE);
																lblCreateANew.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
																lblCreateANew.setText("Create a New Customer Account");
																
																		Button btnXCreateAccount = new Button(NewAcctComp, SWT.NONE);
																		btnXCreateAccount.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
																		btnXCreateAccount.setText("X");
																		
																				
														
																Label lblNewLabel = new Label(NewAcctComp, SWT.NONE);
																lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
																lblNewLabel.setText("Name");
												
														searchName = new Text(NewAcctComp, SWT.BORDER);
														searchName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
										new Label(NewAcctComp, SWT.NONE);
										new Label(NewAcctComp, SWT.NONE);
										
										searchNameError = new Text(NewAcctComp, SWT.BORDER);
										searchNameError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
										new Label(NewAcctComp, SWT.NONE);
								
										Label lblNewLabel_1 = new Label(NewAcctComp, SWT.NONE);
										lblNewLabel_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
										lblNewLabel_1.setText("Phone #");
						
								searchPhone = new Text(NewAcctComp, SWT.BORDER);
								searchPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
						new Label(NewAcctComp, SWT.NONE);
						new Label(NewAcctComp, SWT.NONE);
						
						searchPhoneError = new Text(NewAcctComp, SWT.BORDER);
						searchPhoneError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
						new Label(NewAcctComp, SWT.NONE);
						
						Label lblNewLabel_7 = new Label(NewAcctComp, SWT.NONE);
						lblNewLabel_7.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
						lblNewLabel_7.setText("Address");
						
						searchAddress = new Text(NewAcctComp, SWT.BORDER);
						searchAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
						new Label(NewAcctComp, SWT.NONE);
						new Label(NewAcctComp, SWT.NONE);
						
						searchAddressError = new Text(NewAcctComp, SWT.BORDER);
						searchAddressError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
						new Label(NewAcctComp, SWT.NONE);
						
						Label lblNewLabel_8 = new Label(NewAcctComp, SWT.NONE);
						lblNewLabel_8.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
						lblNewLabel_8.setText("Email");
						
						searchEmail = new Text(NewAcctComp, SWT.BORDER);
						GridData gd_searchEmail = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
						gd_searchEmail.widthHint = 325;
						searchEmail.setLayoutData(gd_searchEmail);
						new Label(NewAcctComp, SWT.NONE);
						new Label(NewAcctComp, SWT.NONE);
						
						searchEmailError = new Text(NewAcctComp, SWT.BORDER);
						searchEmailError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
						new Label(NewAcctComp, SWT.NONE);
				
						Button btnNewButton_2 = new Button(NewAcctComp, SWT.NONE);
						btnNewButton_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
						btnNewButton_2.setText("Create Customer");
		
				Button btnClear = new Button(NewAcctComp, SWT.NONE);
				btnClear.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
				btnClear.setText("Clear");
		new Label(NewAcctComp, SWT.NONE);
		
		
		
		
		
		
		/*          This following 2 lines are a temporary solution to allow the creators to edit the composites as they work :)             */
		NewAcctComp.setVisible(true);
		SearchAcctComp.setVisible(true);

		
		
		
		
		
		txtThisIsWhere = new Text(NewAcctComp, SWT.BORDER);
		txtThisIsWhere.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1));
		txtThisIsWhere.setText("This is where to create a new customer account");
		new Label(NewAcctComp, SWT.NONE);

		Composite Cust2ButtonComp = new Composite(CustomerComposite, SWT.NONE);
		fd_SearchAcctComp.top = new FormAttachment(Cust2ButtonComp, 35);
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		
		CLabel lblNewLabel_2 = new CLabel(SearchAcctComp, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("New Label");
		
		Button btnXSearchAccount = new Button(SearchAcctComp, SWT.NONE);
		
		btnXSearchAccount.setText("X");
		
		Label lblNewLabel_3 = new Label(SearchAcctComp, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("Name");
		
		newNameTextbox = new Text(SearchAcctComp, SWT.BORDER);
		newNameTextbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		
		newNameErrorTextbox = new Text(SearchAcctComp, SWT.BORDER);
		newNameErrorTextbox.setEditable(false);
		newNameErrorTextbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		
		Label lblNewLabel_4 = new Label(SearchAcctComp, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("Phone #");
		
		newPhoneNumber = new Text(SearchAcctComp, SWT.BORDER);
		newPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		
		newPhoneNumberError = new Text(SearchAcctComp, SWT.BORDER);
		newPhoneNumberError.setEditable(false);
		newPhoneNumberError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		
		Label lblNewLabel_5 = new Label(SearchAcctComp, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("Address");
		
		newAddress = new Text(SearchAcctComp, SWT.BORDER);
		newAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		
		newAddressError = new Text(SearchAcctComp, SWT.BORDER);
		newAddressError.setEditable(false);
		newAddressError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		
		Label lblNewLabel_6 = new Label(SearchAcctComp, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("Email");
		
		newEmail = new Text(SearchAcctComp, SWT.BORDER);
		newEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		
		newEmailError = new Text(SearchAcctComp, SWT.BORDER);
		newEmailError.setEditable(false);
		newEmailError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(SearchAcctComp, SWT.NONE);
		new Label(SearchAcctComp, SWT.NONE);
		
		Button btnNewButton_3 = new Button(SearchAcctComp, SWT.NONE);
		btnNewButton_3.setText("Search");
		new Label(SearchAcctComp, SWT.NONE);
		FormData fd_Cust2ButtonComp = new FormData();
		fd_Cust2ButtonComp.bottom = new FormAttachment(0, 232);
		fd_Cust2ButtonComp.right = new FormAttachment(0, 426);
		fd_Cust2ButtonComp.top = new FormAttachment(0);
		fd_Cust2ButtonComp.left = new FormAttachment(0);
		Cust2ButtonComp.setLayoutData(fd_Cust2ButtonComp);

		Button btnSearchAccount = new Button(Cust2ButtonComp, SWT.NONE);

		btnSearchAccount.setText("Search");
		btnSearchAccount.setBounds(57, 82, 113, 85);

		Button btnNewAccount = new Button(Cust2ButtonComp, SWT.NONE);

		btnNewAccount.setText("New Account");
		btnNewAccount.setBounds(232, 86, 150, 77);
		
		txtCurrentCustomer = new Text(Cust2ButtonComp, SWT.BORDER);
		txtCurrentCustomer.setText("Current Customer : ");
		txtCurrentCustomer.setBounds(154, 186, 129, 21);
		
		text_2 = new Text(CustomerComposite, SWT.BORDER);
		text_2.setLayoutData(new FormData());

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, 0);
		tbtmNewItem_1.setText("Products");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_1);

		TabItem tbtmNewItem_2 = new TabItem(tabFolder, 0);
		tbtmNewItem_2.setText("Cart");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_2);
		composite_2.setLayout(null);

		TabItem tbtmNewItem_3 = new TabItem(tabFolder, 0);
		tbtmNewItem_3.setText("Inventory");

		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_3.setControl(composite_3);

		TabItem tbtmNewItem_4 = new TabItem(tabFolder, 0);
		tbtmNewItem_4.setText("Work Orders");

		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_4.setControl(composite_4);

		btnNewAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : New Account");
				NewAcctComp.setVisible(true);
				Cust2ButtonComp.setEnabled(false);
			}
		});

		btnSearchAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Search Customer");
				Cust2ButtonComp.setEnabled(false);
				SearchAcctComp.setVisible(true);
			}
		});
		
		btnXCreateAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Create Account)");
				NewAcctComp.setVisible(false);
				Cust2ButtonComp.setEnabled(true);
			}
		});
		
		btnXSearchAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Search Account");
				SearchAcctComp.setVisible(false);
				Cust2ButtonComp.setEnabled(true);
			}
		});
	}
}