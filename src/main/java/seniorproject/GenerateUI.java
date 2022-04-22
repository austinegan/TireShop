package seniorproject;

import java.util.*;
import java.util.List;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


import org.eclipse.swt.widgets.*;

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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.hibernate.Session;

import seniorproject.dao.InventoryDao;
import seniorproject.dao.OrderDao;
import seniorproject.dao.CustomerDao;
import seniorproject.model.Customer;
import seniorproject.model.Inventory;
import seniorproject.model.Order;
import seniorproject.util.HibernateUtil;

//import net.proteanit.sql.DbUtils;
import org.eclipse.swt.custom.ScrolledComposite;
//import org.eclipse.swt.widgets.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.eclipse.swt.widgets.TableColumn;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.layout.RowData;

public class GenerateUI {

	protected Shell shell;
	private Text newName;
	private Text newPhone;
	private Text searchName;
	private Text searchPhone;
	private Text searchAddress;
	private Text searchEmail;
	private Text searchCustomerError;
	private Text newAddress;
	private Text newEmail;
	private Table tableInv;
	private Text txtCart;
	private Text currentCustomerText;
	private Text TireDescriptionText;
	private Text QuantityText;
	private Text ItemTotalCost;
	private Text SubtotalText;
	private Text TaxText;
	private Text CartTotalText;
	int plusCounter=0;

	private Text searchCust;
	private Table table;
	private Text txtCustomer;
	private Text txtPhoneNumber;
	private Table table_1;
	private Table productsTable;

	private static String[] allProductColumns;
	private static String[] someProductColumns;
	private static String[] customerColumns;
	private Text addInvError;
	private Text NewCustomerError;
	private Table customerTable;
	
	
	/*
	 * \
	 * Behind the scenes data for what the tables are currently showing
	 */
	 
	 private List<Customer> customerPageList;
	 private List<Inventory> productsPageList;
	 private List<Inventory> cartPageList;
	 private List <Inventory> InvPageList;
	 private List<Order> workOrdersPageList;
	 private Text addInvBrand;
	 private Text addInvModel;
	 private Text addInvSize;
	 private Text addInvQuantity;
	 private Text addInvSalePrice;
	 private Text addInvPurchasePrice;
	 private Text editInvBrand;
	 private Text editInvModel;
	 private Text editInvSize;
	 private Text editInvQuantity;
	 private Text editInvPurchasePrice;
	 private Text editInvSalePrice;
	 private Text editInvErrorField;
	 
	 
	 
	 


	/**
	 * Launch the application.
	 * 
	 * @param args
	 */


	public static void main(String[] args) {
		initialize();
		try {
			GenerateUI window = new GenerateUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void initialize() {
		allProductColumns = new String[] {"ID", "Brand", "Model Number", "Sale Price", "Purchase Price", "Count", "Width", "Size", "Aspect Ration", "Diameter"};
		someProductColumns = new String[] {"Brand", "Model", "Size", "Quantity", "Sale Price"};
		customerColumns = new String[] {"Name", "Phone", "Address", "Email"};
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		fillTableProductsSimple(productsTable, InventoryDao.getAllInventory());;
		fillTableProductsExtensive(tableInv, InventoryDao.getAllInventory());
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		HibernateUtil.closeFactory();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1496, 829);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		
/*	--------------------------------------- Customer Composite (TAB) ----------------------------------		*/
		
		TabItem tbtmNewItem = new TabItem(tabFolder, 0);
		tbtmNewItem.setText("Customer");

		Composite CustomerComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(CustomerComposite);
		RowLayout rl_CustomerComposite = new RowLayout(SWT.HORIZONTAL);
		rl_CustomerComposite.fill = true;
		rl_CustomerComposite.justify = true;
		rl_CustomerComposite.center = true;
		CustomerComposite.setLayout(rl_CustomerComposite);

		Composite SearchAcctComp = new Composite(CustomerComposite, SWT.NONE);
		SearchAcctComp.setLayoutData(new RowData(491, 737));
		SearchAcctComp.setVisible(true);
		SearchAcctComp.setLayout(new GridLayout(1, false));

		Composite SearchCustomerTitleComposite = new Composite(SearchAcctComp, SWT.NONE);
		SearchCustomerTitleComposite.setLayout(new GridLayout(2, false));
		SearchCustomerTitleComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CLabel lblNewLabel_2 = new CLabel(SearchCustomerTitleComposite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setText("Search Customer");

		Button btnXSearchAccount = new Button(SearchCustomerTitleComposite, SWT.NONE);
		btnXSearchAccount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		btnXSearchAccount.setText("X");
		new Label(SearchCustomerTitleComposite, SWT.NONE);
		new Label(SearchCustomerTitleComposite, SWT.NONE);

		btnXSearchAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Search Account");
				SearchAcctComp.setVisible(false);
				//Cust2ButtonComp.setEnabled(true);
			}
		});
								
								
										Composite SearchCustomerEverythingElseComposite = new Composite(SearchAcctComp, SWT.NONE);
										SearchCustomerEverythingElseComposite.setLayout(new GridLayout(4, true));
										GridData gd_SearchCustomerEverythingElseComposite = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
										gd_SearchCustomerEverythingElseComposite.grabExcessVerticalSpace = false;
										gd_SearchCustomerEverythingElseComposite.grabExcessHorizontalSpace = false;
										gd_SearchCustomerEverythingElseComposite.heightHint = 328;
										SearchCustomerEverythingElseComposite.setLayoutData(gd_SearchCustomerEverythingElseComposite);
										
												Label lblNewLabel_3 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
												lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
												lblNewLabel_3.setText("Name");
												
														searchName = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
														//gd_searchName.widthHint = 300;
														searchName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
														
																Label lblNewLabel_4 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
																lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
																lblNewLabel_4.setText("Phone #");
																
																		searchPhone = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
																		//gd_searchPhone.widthHint = 300;
																		searchPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
																		
																				Label lblNewLabel_5 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
																				lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
																				lblNewLabel_5.setText("Address");
																				
																						searchAddress = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
																						//gd_searchAddress.widthHint = 300;
																						searchAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
																						
																								Label lblNewLabel_6 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
																								lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
																								lblNewLabel_6.setText("Email");
																								
																										searchEmail = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
																										//gd_searchEmail.widthHint = 300;
																										searchEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
																										new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
																										
																																		
																																				searchCustomerError = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER | SWT.CENTER);
																																				//gd_searchCustomerError.widthHint = 300;
																																				searchCustomerError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
																																				searchCustomerError.setEditable(false);
																																				
																																				
																																								
																																										Button searchCustomerButton = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
																																										
																																										searchCustomerButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
																																										searchCustomerButton.setText("Search");
																																										
																																										Button btnNewButton_1 = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
																																										btnNewButton_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
																																										btnNewButton_1.setText("New Customer");
																																										
																																										Button btnEditCustomer = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
																																										btnEditCustomer.setText("Edit Customer");
																																										
																																										Button btnSelectCustomer = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
																																										btnSelectCustomer.setText("Select Customer");
																																										
																																												searchCustomerButton.addSelectionListener(new SelectionAdapter() {
																																													@Override
																																													public void widgetSelected(SelectionEvent e) {
																																														customerTable.clearAll();
																																														//fillTableCustomer(customerTable, CustomerDao.getAllCustomer());
																																										//fillTableCustomer(customerTable, CustomerDao.getCustomerTestNamedParametersNative(searchName.getText(), searchPhone.getText(), searchAddress.getText(), searchEmail.getText(), "AND"));
																																														customerPageList =  CustomerDao.generateQueryCustomer(searchName.getText(), searchPhone.getText(), searchAddress.getText(), searchEmail.getText(), true);
																																														fillTableCustomer(customerTable, customerPageList);
																																													}
																																												});
						Composite customerTableComposite = new Composite(SearchAcctComp, SWT.NONE);
						customerTableComposite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
						customerTableComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
						
						customerTable = new Table(customerTableComposite, SWT.BORDER | SWT.FULL_SELECTION);
						customerTable.setHeaderVisible(true);
						customerTable.setLinesVisible(true);
				
						createAndNameColumns(customerTable, customerColumns);
						fillTableCustomer(customerTable, CustomerDao.getAllCustomer());
				
				
				
				
/*	-------------------------------------- Customer New Account Composite ----------------------------------		*/				
		

				
		Composite NewAcctComp = new Composite(CustomerComposite, SWT.NONE);
		NewAcctComp.setLayout(new GridLayout(4, true));
																new Label(NewAcctComp, SWT.NONE);

														
																CLabel lblCreateANew = new CLabel(NewAcctComp, SWT.NONE);
																lblCreateANew.setAlignment(SWT.RIGHT);
																lblCreateANew.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
																lblCreateANew.setText("Create a New Customer Account");
				
						Button btnXCreateAccount = new Button(NewAcctComp, SWT.NONE);
						btnXCreateAccount.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
						btnXCreateAccount.setText("X");
						
								btnXCreateAccount.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										System.out.println("Button : X (Create Account)");
										NewAcctComp.setVisible(false);
										//Cust2ButtonComp.setEnabled(true);
									}
								});
						
								Label lblNewCustomer = new Label(NewAcctComp, SWT.NONE);
								lblNewCustomer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
								lblNewCustomer.setText("Name");
						
								newName = new Text(NewAcctComp, SWT.BORDER);
								newName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));






		/*          This following 2 lines are a temporary solution to allow the creators to edit the composites as they work :)             */
		NewAcctComp.setVisible(true);
				
						Label lblNewPhone = new Label(NewAcctComp, SWT.NONE);
						lblNewPhone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
						lblNewPhone.setText("Phone #");
				
						newPhone = new Text(NewAcctComp, SWT.BORDER);
						newPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
								
										Label lblNewAddress = new Label(NewAcctComp, SWT.NONE);
										lblNewAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
										lblNewAddress.setText("Address");
						
								newAddress = new Text(NewAcctComp, SWT.BORDER);
								newAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
								
										Label lblNewEmail = new Label(NewAcctComp, SWT.NONE);
										lblNewEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
										lblNewEmail.setText("Email");
						
								newEmail = new Text(NewAcctComp, SWT.BORDER);
								GridData gd_newEmail = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
								gd_newEmail.widthHint = 325;
								newEmail.setLayoutData(gd_newEmail);
										new Label(NewAcctComp, SWT.NONE);

										
										NewCustomerError = new Text(NewAcctComp, SWT.BORDER | SWT.READ_ONLY);
										NewCustomerError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
										new Label(NewAcctComp, SWT.NONE);
										
												Button btnClear = new Button(NewAcctComp, SWT.NONE);
												btnClear.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
												btnClear.setText("Clear");
												
												
														
														btnClear.addSelectionListener(new SelectionAdapter() {
															@Override
															public void widgetSelected(SelectionEvent e) {
																System.out.println("Button : Clear (Add Customer)");
																newName.setText("");
																newPhone.setText("");
																newAddress.setText("");
																newEmail.setText("");
//								newNameError.setText("");
//								newPhoneError.setText("");
//								newAddressError.setText("");
//								newEmailError.setText("");
															}
														});
												
														Button btnCreateCustomer = new Button(NewAcctComp, SWT.NONE);
														btnCreateCustomer.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
														btnCreateCustomer.setText("Create Customer");
														
														btnCreateCustomer.addSelectionListener(new SelectionAdapter() {
															@Override
															public void widgetSelected(SelectionEvent e) {
																System.out.println("Button : Create Customer");
																Session session = HibernateUtil.getSessionFactory().openSession();
															    session.beginTransaction();
															    Customer cus = new Customer();
															    cus.setName(newName.getText());
															    cus.setPhone(newPhone.getText());
															    cus.setEmail(newEmail.getText());
															    cus.setAddress(newAddress.getText());
															    Long datetime = System.currentTimeMillis();
															    Timestamp ts = new Timestamp(datetime);
															    cus.setCreate_time(ts);
															    cus.setLast_update(ts);
															    if (Validation.customerIsValid(cus, NewCustomerError)) {
															    	session.save(cus);
																    session.getTransaction().commit();
															    } else {
															    	session.close();
															    }    
															}
														});
										new Label(NewAcctComp, SWT.NONE);



		TabItem tbtmNewItem_1 = new TabItem(tabFolder, 0);
		tbtmNewItem_1.setText("Products");

		Composite ProductsComposite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(ProductsComposite_1);
		ProductsComposite_1.setLayout(new GridLayout(1, false));
		


		Composite SearchMenuComp_1 = new Composite(ProductsComposite_1, SWT.NONE);
		SearchMenuComp_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				RowLayout rl_SearchMenuComp_1 = new RowLayout(SWT.HORIZONTAL);
				rl_SearchMenuComp_1.justify = true;
				rl_SearchMenuComp_1.fill = true;
				SearchMenuComp_1.setLayout(rl_SearchMenuComp_1);
		
				Combo BrandCombo = new Combo(SearchMenuComp_1, SWT.NONE);
				BrandCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				BrandCombo.setText("Brand");
				
						Combo WidthCombo = new Combo(SearchMenuComp_1, SWT.NONE);
						WidthCombo.setText("Tire Width");
						
								Combo RatioCombo = new Combo(SearchMenuComp_1, SWT.NONE);
								RatioCombo.setText("Aspect Ratio");
								
										Combo DiameterCombo = new Combo(SearchMenuComp_1, SWT.NONE);
										DiameterCombo.setText("Diameter");
										
												Button ProdBtnSearch = new Button(SearchMenuComp_1, SWT.NONE);
												ProdBtnSearch.addSelectionListener(new SelectionAdapter() {
													@Override
													public void widgetSelected(SelectionEvent e) {
														fillTableProductsSimple(productsTable, InventoryDao.generateQueryInventory("onti", "", "", "", true));
													}
												});
												ProdBtnSearch.setText("Search");

		Composite SearchResultsComp = new Composite(ProductsComposite_1, SWT.NONE);
		SearchResultsComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		SearchResultsComp.setVisible(true);

		productsTable = new Table(SearchResultsComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		productsTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		productsTable.setBounds(10, 0, 960, 403);
		productsTable.setHeaderVisible(true);
		productsTable.setLinesVisible(true);

		createAndNameColumns(productsTable, someProductColumns);

		Text[][] text= new Text[50][50];
		Button[][] button = new Button[50][50];
		int[][] plusInt = new int [50][50];
		//button_1_1.setBounds(508, 36, 24, 23);
		// button_1_1.setText("+");
		//button_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, 0);
		tbtmNewItem_2.setText("Cart");

		Composite CartComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(CartComposite);
		CartComposite.setLayout(null);

		Composite CheckoutComp = new Composite(CartComposite, SWT.NONE);
		CheckoutComp.setBounds(0, 0, 1472, 752);

		txtCart = new Text(CheckoutComp, SWT.BORDER);
		txtCart.setText("Current Customer:");
		txtCart.setBounds(39, 10, 110, 26);

		currentCustomerText = new Text(CheckoutComp, SWT.BORDER);
		currentCustomerText.setEnabled(false);
		currentCustomerText.setBounds(145, 10, 184, 26);

		Label lblNewLabel_9 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_9.setBounds(10, 72, 55, 20);
		lblNewLabel_9.setText("Cart:");

		TireDescriptionText = new Text(CheckoutComp, SWT.BORDER);
		TireDescriptionText.setBounds(30, 107, 288, 25);

		Button btnXSearchAccount_1 = new Button(CheckoutComp, SWT.NONE);
		btnXSearchAccount_1.setText("X");
		btnXSearchAccount_1.setBounds(10, 107, 19, 25);

		Button button_1_2 = new Button(CheckoutComp, SWT.NONE);
		button_1_2.setText("-");
		button_1_2.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		button_1_2.setBounds(324, 107, 24, 21);

		QuantityText = new Text(CheckoutComp, SWT.BORDER);
		QuantityText.setBounds(379, 107, 20, 21);

		Button button_1_1_1 = new Button(CheckoutComp, SWT.NONE);
		button_1_1_1.setText("+");
		button_1_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button_1_1_1.setBounds(405, 107, 24, 23);

		Label lblNewLabel_10 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_10.setBounds(349, 110, 24, 15);
		lblNewLabel_10.setText("Qty.");

		ItemTotalCost = new Text(CheckoutComp, SWT.BORDER);
		ItemTotalCost.setBounds(451, 107, 60, 21);

		SubtotalText = new Text(CheckoutComp, SWT.BORDER);
		SubtotalText.setBounds(451, 218, 60, 21);

		TaxText = new Text(CheckoutComp, SWT.BORDER);
		TaxText.setBounds(451, 245, 60, 21);

		Label lblNewLabel_11 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_11.setBounds(390, 224, 55, 15);
		lblNewLabel_11.setText("Subtotal:");

		Label lblNewLabel_11_1 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_11_1.setText("Tax(7.5%)");
		lblNewLabel_11_1.setBounds(390, 251, 55, 15);

		Label lblNewLabel_11_1_1 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_11_1_1.setText("Total:");
		lblNewLabel_11_1_1.setBounds(390, 276, 55, 15);

		CartTotalText = new Text(CheckoutComp, SWT.BORDER);
		CartTotalText.setBounds(451, 272, 60, 21);

		Button btnNewButton = new Button(CheckoutComp, SWT.NONE);
		btnNewButton.setBounds(436, 318, 75, 25);
		btnNewButton.setText("Checkout");

		Button btnClearCart = new Button(CheckoutComp, SWT.NONE);
		btnClearCart.setText("Clear Cart");
		btnClearCart.setBounds(354, 318, 75, 25);

		TabItem tbtmNewItem_3 = new TabItem(tabFolder, 0);
		tbtmNewItem_3.setText("Inventory");

		Composite InventoryComposite = new Composite(tabFolder, SWT.NONE);
		InventoryComposite.setLocation(-48, -329);
		tbtmNewItem_3.setControl(InventoryComposite);
		InventoryComposite.setLayout(new GridLayout(2, false));
		
		
		Composite Inv2ButtonComp = new Composite(InventoryComposite, SWT.NONE);
		Inv2ButtonComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		Inv2ButtonComp.setLayout(new GridLayout(2, false));
				
				Composite SearchMenuComp_1_1 = new Composite(Inv2ButtonComp, SWT.NONE);
				RowLayout rl_SearchMenuComp_1_1 = new RowLayout(SWT.HORIZONTAL);
				rl_SearchMenuComp_1_1.justify = true;
				rl_SearchMenuComp_1_1.fill = true;
				SearchMenuComp_1_1.setLayout(rl_SearchMenuComp_1_1);
				
				Combo BrandComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
				BrandComboInventory.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				BrandComboInventory.setText("Brand");
				
				Combo WidthComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
				WidthComboInventory.setText("Tire Width");
				
				Combo RatioComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
				RatioComboInventory.setText("Aspect Ratio");
				
				Combo DiameterComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
				DiameterComboInventory.setText("Diameter");
				
				Button ProdBtnSearchInventory = new Button(SearchMenuComp_1_1, SWT.NONE);
				ProdBtnSearchInventory.setText("Search");
						new Label(Inv2ButtonComp, SWT.NONE);
				
						tableInv = new Table(Inv2ButtonComp, SWT.BORDER | SWT.FULL_SELECTION);
						tableInv.setHeaderVisible(true);
						tableInv.setLinesVisible(true);
						
								
								createAndNameColumns(tableInv, allProductColumns);
						new Label(Inv2ButtonComp, SWT.NONE);
				
						Button btnAddInv = new Button(Inv2ButtonComp, SWT.NONE);
						btnAddInv.setText("Add Inventory");
						
						Button btnEditInventory = new Button(Inv2ButtonComp, SWT.NONE);
						btnEditInventory.setText("Edit Inventory");
						
								btnAddInv.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										System.out.println("Button : Add Inventory");
										//AddInvComp.setVisible(true);
										Inv2ButtonComp.setEnabled(false);
									}
								});
		new Label(InventoryComposite, SWT.NONE);
		
		Composite AddInvComp_1 = new Composite(InventoryComposite, SWT.NONE);
		AddInvComp_1.setVisible(true);
		AddInvComp_1.setLayout(new GridLayout(4, true));
		new Label(AddInvComp_1, SWT.NONE);
		
		Label lblAddInventory_1 = new Label(AddInvComp_1, SWT.NONE);
		
		lblAddInventory_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblAddInventory_1.setText("Edit Inventory");
		Label label_2 = new Label(AddInvComp_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Label lblEditInvBrand = new Label(AddInvComp_1, SWT.NONE);
		lblEditInvBrand.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvBrand.setText("Brand:");
		
		editInvBrand = new Text(AddInvComp_1, SWT.BORDER);
		editInvBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblEditInvModel = new Label(AddInvComp_1, SWT.NONE);
		lblEditInvModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvModel.setText("Model:");
		
		editInvModel = new Text(AddInvComp_1, SWT.BORDER);
		editInvModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblEditInvSize = new Label(AddInvComp_1, SWT.NONE);
		lblEditInvSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvSize.setText("Size:");
		
		editInvSize = new Text(AddInvComp_1, SWT.BORDER);
		editInvSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblEditInvQuantity = new Label(AddInvComp_1, SWT.NONE);
		lblEditInvQuantity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvQuantity.setText("Quantity:");
		
		editInvQuantity = new Text(AddInvComp_1, SWT.BORDER);
		GridData gd_editInvQuantity = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_editInvQuantity.widthHint = 325;
		editInvQuantity.setLayoutData(gd_editInvQuantity);
		
		Label lblEditInvPurchasePrice = new Label(AddInvComp_1, SWT.NONE);
		lblEditInvPurchasePrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvPurchasePrice.setText("Purchase Price:");
		
		editInvPurchasePrice = new Text(AddInvComp_1, SWT.BORDER);
		editInvPurchasePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblEditInvSalePrice = new Label(AddInvComp_1, SWT.NONE);
		lblEditInvSalePrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvSalePrice.setText("Sale Price:");
		
		editInvSalePrice = new Text(AddInvComp_1, SWT.BORDER);
		editInvSalePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(AddInvComp_1, SWT.NONE);
		
		editInvErrorField = new Text(AddInvComp_1, SWT.BORDER);
		editInvErrorField.setEditable(false);
		editInvErrorField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(AddInvComp_1, SWT.NONE);
		
		Button btnClearEditInv = new Button(AddInvComp_1, SWT.NONE);
		btnClearEditInv.setText("Clear");
		
		Button btnEditInvSubmit = new Button(AddInvComp_1, SWT.NONE);
		btnEditInvSubmit.setText("Submit");
		new Label(AddInvComp_1, SWT.NONE);
		
		Composite AddInvComp = new Composite(InventoryComposite, SWT.NONE);
		AddInvComp.setVisible(true);
		AddInvComp.setLayout(new GridLayout(4, true));
		new Label(AddInvComp, SWT.NONE);
		
				Label lblAddInventory = new Label(AddInvComp, SWT.NONE);
				lblAddInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
				lblAddInventory.setText("Add Inventory");
		Label label = new Label(AddInvComp, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
				Label lblAddInvBrand = new Label(AddInvComp, SWT.NONE);
				lblAddInvBrand.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblAddInvBrand.setText("Brand:");
		
		addInvBrand = new Text(AddInvComp, SWT.BORDER);
		addInvBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
				Label lblAddInvModel = new Label(AddInvComp, SWT.NONE);
				lblAddInvModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblAddInvModel.setText("Model:");
		
		addInvModel = new Text(AddInvComp, SWT.BORDER);
		addInvModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
				Label lblAddInvSize = new Label(AddInvComp, SWT.NONE);
				lblAddInvSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblAddInvSize.setText("Size:");
		
		addInvSize = new Text(AddInvComp, SWT.BORDER);
		addInvSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
				Label lblAddInvQuantity = new Label(AddInvComp, SWT.NONE);
				lblAddInvQuantity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblAddInvQuantity.setText("Quantity:");
		
		addInvQuantity = new Text(AddInvComp, SWT.BORDER);
		GridData gd_addInvQuantity = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_addInvQuantity.widthHint = 325;
		addInvQuantity.setLayoutData(gd_addInvQuantity);
		
				Label lblAddInvPurchasePrice = new Label(AddInvComp, SWT.NONE);
				lblAddInvPurchasePrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblAddInvPurchasePrice.setText("Purchase Price:");
		
		addInvPurchasePrice = new Text(AddInvComp, SWT.BORDER);
		addInvPurchasePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Label lblAddInvSalePrice = new Label(AddInvComp, SWT.NONE);
		lblAddInvSalePrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAddInvSalePrice.setText("Sale Price:");
		
		addInvSalePrice = new Text(AddInvComp, SWT.BORDER);
		addInvSalePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(AddInvComp, SWT.NONE);
		
		addInvError = new Text(AddInvComp, SWT.BORDER);
		addInvError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		addInvError.setEditable(false);
				new Label(AddInvComp, SWT.NONE);
				
						Button btnAddInvSubmit = new Button(AddInvComp, SWT.NONE);
						btnAddInvSubmit.setText("Submit");
						
								
								btnAddInvSubmit.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										System.out.println("Button : Submit (Add Inventory)");
										Session session = HibernateUtil.getSessionFactory().openSession();
									    session.beginTransaction();
									    Inventory inv = new Inventory();
									    inv.setBrand(addInvBrand.getText());
									    inv.setModel_number(addInvModel.getText());
									    inv.setSize(addInvSize.getText());
									    String msg = "";
									    try {
									    	int intQuantity = Integer.parseInt(addInvQuantity.getText());
										    inv.setCount(intQuantity);
									    } catch (NumberFormatException nfe) {
									    	msg += "Quantity must be an integer. ";
									    }
									    try {
									    	Double purchasePrice = Double.parseDouble(addInvPurchasePrice.getText());
									    	inv.setPurchase_price(purchasePrice);
									    } catch (NumberFormatException nfe) {
									    	msg += "Purchase price must be a double. ";
									    }
									    try {
									    	Double salePrice = Double.parseDouble(addInvSalePrice.getText());
										    inv.setSale_price(salePrice);
									    } catch (NumberFormatException nfe) {
									    	msg += "Sale price must be a double. ";
									    }
									    if (Validation.inventoryIsValid(inv, addInvError, msg)) {
									    	int width = Integer.parseInt(addInvSize.getText().substring(0, 3));   
										    inv.setWidth(width);
										    int aspectRatio = Integer.parseInt(addInvSize.getText().substring(4, 6));   
										    inv.setAspectratio(aspectRatio);
										    int diameter = Integer.parseInt(addInvSize.getText().substring(7, 9));   
										    inv.setDiameter(diameter);
									    	session.save(inv);
										    session.getTransaction().commit();
									    } else {
									    	session.close();
									    }    
									}
								});
				
						Button btnClearAddInv = new Button(AddInvComp, SWT.NONE);
						btnClearAddInv.setText("Clear");
						


//						btnClearAddInv.addSelectionListener(new SelectionAdapter() {
//							@Override
//							public void widgetSelected(SelectionEvent e) {
//								System.out.println("Button : Clear (Add Inventory)");
//								addInvBrand.setText("");
//								addInvBrandError.setText("");
//								addInvModel.setText("");
//								addInvModelError.setText("");
//								addInvSize.setText("");
//								addInvSizeError.setText("");
//								addInvQuantity.setText("");
//								addInvQuantityError.setText("");
//								addInvPurchasePrice.setText("");
//								addInvPurchasePriceError.setText("");
//								addInvSalePrice.setText("");
//								addInvError.setText("");
//							}
//						});
				new Label(AddInvComp, SWT.NONE);
		
		
		
		
		
		
		



		TabItem tbtmNewItem_4 = new TabItem(tabFolder, 0);
		tbtmNewItem_4.setText("Work Orders");

		Composite WorkOrderComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_4.setControl(WorkOrderComposite);

		Composite allWorkOrders = new Composite(WorkOrderComposite, SWT.NONE);
		allWorkOrders.setBounds(10, 10, 413, 279);

		searchCust = new Text(allWorkOrders, SWT.BORDER);
		searchCust.setText("Search by customer name, phone number, or address");
		searchCust.setBounds(15, 25, 304, 19);

		Button btnSearchCust = new Button(allWorkOrders, SWT.NONE);
		btnSearchCust.setBounds(325, 24, 70, 21);
		btnSearchCust.setText("Search");

		table = new Table(allWorkOrders, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(15, 102, 380, 155);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		Label lblStatus = new Label(allWorkOrders, SWT.NONE);
		lblStatus.setBounds(23, 72, 56, 16);
		lblStatus.setText("Status");

		Label lblCustomer = new Label(allWorkOrders, SWT.NONE);
		lblCustomer.setBounds(107, 72, 56, 16);
		lblCustomer.setText("Customer");

		Label lblPhone = new Label(allWorkOrders, SWT.NONE);
		lblPhone.setBounds(232, 72, 56, 16);
		lblPhone.setText("Phone ");

		Label lblService = new Label(allWorkOrders, SWT.NONE);
		lblService.setBounds(339, 72, 56, 16);
		lblService.setText("Service");

		Composite specificWorkOrders = new Composite(WorkOrderComposite, SWT.NONE);
		specificWorkOrders.setBounds(10, 314, 413, 270);

		txtCustomer = new Text(specificWorkOrders, SWT.BORDER);
		txtCustomer.setText("Customer: ");
		txtCustomer.setBounds(10, 25, 219, 19);

		txtPhoneNumber = new Text(specificWorkOrders, SWT.BORDER);
		txtPhoneNumber.setText("Phone Number:");
		txtPhoneNumber.setBounds(10, 50, 219, 19);

		Label lblDescription = new Label(specificWorkOrders, SWT.NONE);
		lblDescription.setBounds(10, 91, 72, 16);
		lblDescription.setText("Description");

		Label lblQuantity = new Label(specificWorkOrders, SWT.NONE);
		lblQuantity.setBounds(173, 91, 56, 16);
		lblQuantity.setText("Quantity");

		Label lblPartNumber = new Label(specificWorkOrders, SWT.NONE);
		lblPartNumber.setBounds(304, 91, 79, 16);
		lblPartNumber.setText("Part Number");

		table_1 = new Table(specificWorkOrders, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(10, 113, 373, 95);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		Button btnCompleted = new Button(specificWorkOrders, SWT.NONE);
		btnCompleted.setBounds(32, 227, 70, 21);
		btnCompleted.setText("Completed");

		Button btnUnableToComplete = new Button(specificWorkOrders, SWT.NONE);
		btnUnableToComplete.setBounds(246, 227, 119, 21);
		btnUnableToComplete.setText("Unable to Complete");

		Button btnXSpecificWorkOrders = new Button(specificWorkOrders, SWT.NONE);
		btnXSpecificWorkOrders.setBounds(382, 10, 21, 21);
		btnXSpecificWorkOrders.setText("X");

		btnXSearchAccount_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Close Cart Item");
				TireDescriptionText.setText(" ");
				ItemTotalCost.setText(" ");
				QuantityText.setText(" ");
			}
		});

	

		btnSearchCust.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Search (All Work Orders)");
			}
		});

		btnCompleted.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Complete (Specific Work Orders)");
			}
		});

		btnUnableToComplete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Unable to Complete (Specific Work Orders)");
			}
		});

		btnXSpecificWorkOrders.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Specific Work Orders)");
				specificWorkOrders.setVisible(false);
				allWorkOrders.setEnabled(true);
			}
		});
	}

	public void createAndNameColumns(Table table, String[] titles) {
		for (int i = 0; i< titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setWidth(100);
		}
	}
	
	public static void fillTableCustomer(Table table, List<Customer> myCustomers) {
		for (Customer cust : myCustomers) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { cust.getName(), cust.getPhone(), cust.getAddress(), cust.getEmail()});
		}
	}
	
	public void fillTableProductsSimple(Table table, List<Inventory> myInventory){
		for (Inventory inv : myInventory) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] {inv.getBrand(), inv.getModel_number(), 
					inv.getSize(), String.valueOf(inv.getCount()), String.valueOf(inv.getSale_price())});
		}
	}

	public void fillTableProductsExtensive(Table table, List<Inventory> myInventory){
		for (Inventory inv : myInventory) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] {String.valueOf(inv.getId()), inv.getBrand(), inv.getModel_number(), 
					String.valueOf(inv.getSale_price()), String.valueOf(inv.getPurchase_price()), String.valueOf(inv.getCount()), 
					String.valueOf(inv.getWidth()), inv.getSize(), String.valueOf(inv.getAspectratio()), 
					String.valueOf(inv.getDiameter())});
		}
	}
}
