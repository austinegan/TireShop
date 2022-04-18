package seniorproject;

import java.util.*;
import java.util.List;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import javax.swing.JLabel;

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
import seniorproject.dao.CustomerDao;
import seniorproject.model.Customer;
import seniorproject.model.Inventory;
import seniorproject.util.HibernateUtil;

//import net.proteanit.sql.DbUtils;
import org.eclipse.swt.custom.ScrolledComposite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.eclipse.swt.widgets.TableColumn;
import swing2swt.layout.BoxLayout;

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
	private Text txtInvSearchBar;
	private Table tableInv;
	private Text addInvBrand;
	private Text addInvBrandError;
	private Text addInvModel;
	private Text addInvModelError;
	private Text addInvSize;
	private Text addInvSizeError;
	private Text addInvQuantity;
	private Text addInvQuantityError;
	private Text addInvPurchasePrice;
	private Text addInvPurchasePriceError;
	private Text txtCart;
	private Text currentCustomerText;
	private Text TireDescriptionText;
	private Text QuantityText;
	private Text ItemTotalCost;
	private Text SubtotalText;
	private Text TaxText;
	private Text CartTotalText;
	int plusCounter = 0;

	private Text searchCust;
	private Table table;
	private Text txtCustomer;
	private Text txtPhoneNumber;
	private Table table_1;
	private Table productsTable;

	private static String[] allProductColumns;
	private static String[] someProductColumns;
	private static String[] customerColumns;

	private Text addInvSalePrice;
	private Text addInvSalePriceError;
	private Text NewCustomerError;
	private Table tableCustomer;

	/*
	 * \ Behind the scenes data for what the tables are currently showing
	 * 
	 * 
	 * List<customer> List<Inventory> products List<Inventory> cart List <Inventory>
	 * InvPage List<Orders> workOrdersPage
	 * 
	 * 
	 * *
	 */

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
		allProductColumns = new String[] { "ID", "Brand", "Model Number", "Sale Price", "Purchase Price", "Count",
				"Width", "Size", "Aspect Ration", "Diameter" };
		someProductColumns = new String[] { "Brand", "Model", "Size", "Quantity", "Sale Price" };
		customerColumns = new String[] { "Name", "Phone", "Address", "Email" };
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		fillTableProductsSimple(productsTable);
		fillTableProductsExtensive(tableInv);
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

		/*
		 * --------------------------------------- Customer Composite (TAB)
		 * ----------------------------------
		 */

		TabItem tbtmNewItem = new TabItem(tabFolder, 0);
		tbtmNewItem.setText("Customer");

		/*
		 * -------------------------------------- Customer New Account Composite
		 * ----------------------------------
		 */


		TabItem tbtmNewItem_1 = new TabItem(tabFolder, 0);
		tbtmNewItem_1.setText("Products");

		Composite ProductsComposite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(ProductsComposite_1);

		Composite SearchResultsComp = new Composite(ProductsComposite_1, SWT.NONE);
		SearchResultsComp.setBounds(0, 54, 1076, 519);
		SearchResultsComp.setVisible(true);

		productsTable = new Table(SearchResultsComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		productsTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		productsTable.setBounds(10, 0, 960, 403);
		productsTable.setHeaderVisible(true);
		productsTable.setLinesVisible(true);

		createAndNameColumns(productsTable, someProductColumns);

		Composite SearchMenuComp_1 = new Composite(ProductsComposite_1, SWT.NONE);
		SearchMenuComp_1.setBounds(0, 10, 473, 23);

		Combo BrandCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		BrandCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		BrandCombo.setBounds(0, 0, 94, 23);
		BrandCombo.setText("Brand");

		Combo WidthCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		WidthCombo.setBounds(93, 0, 87, 23);
		WidthCombo.setText("Tire Width");

		Combo RatioCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		RatioCombo.setBounds(180, 0, 94, 23);
		RatioCombo.setText("Aspect Ratio");

		Combo DiameterCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		DiameterCombo.setBounds(274, 0, 87, 23);
		DiameterCombo.setText("Diameter");

		Button ProdBtnSearch = new Button(SearchMenuComp_1, SWT.NONE);
		ProdBtnSearch.setText("Search");
		ProdBtnSearch.setBounds(388, 0, 75, 25);

		Text[][] text = new Text[50][50];
		Button[][] button = new Button[50][50];
		int[][] plusInt = new int[50][50];
		// button_1_1.setBounds(508, 36, 24, 23);
		// button_1_1.setText("+");
		// button_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, 0);
		tbtmNewItem_2.setText("Cart");

		Composite CartComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(CartComposite);
		CartComposite.setLayout(null);

		Composite CheckoutComp = new Composite(CartComposite, SWT.NONE);
		CheckoutComp.setBounds(0, 0, 619, 416);

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

		/*
		 * ---------------------------- Inventory Composite
		 * --------------------------------------------------------
		 */

		Composite Inv2ButtonComp = new Composite(InventoryComposite, SWT.NONE);
		Inv2ButtonComp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));

		Button btnAddInv = new Button(Inv2ButtonComp, SWT.NONE);
		btnAddInv.setBounds(31, 24, 93, 51);
		btnAddInv.setText("Add Inventory");

		txtInvSearchBar = new Text(Inv2ButtonComp, SWT.BORDER);
		txtInvSearchBar.setText("Type tire brand, model, or size to search");
		txtInvSearchBar.setBounds(10, 98, 249, 19);

		Button btnInvSearch = new Button(Inv2ButtonComp, SWT.NONE);
		btnInvSearch.setBounds(265, 98, 53, 20);
		btnInvSearch.setText("Search");

		tableInv = new Table(Inv2ButtonComp, SWT.BORDER | SWT.FULL_SELECTION);
		tableInv.setBounds(15, 142, 303, 113);
		tableInv.setHeaderVisible(true);
		tableInv.setLinesVisible(true);

		createAndNameColumns(tableInv, allProductColumns);

		/* ---------------------- Add Inventory Composite ----------------------- */

		Composite AddInvComp = new Composite(InventoryComposite, SWT.NONE);
		GridData gd_AddInvComp = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_AddInvComp.heightHint = 350;
		AddInvComp.setLayoutData(gd_AddInvComp);

		Label lblAddInventory = new Label(AddInvComp, SWT.NONE);
		lblAddInventory.setBounds(124, 20, 82, 16);
		lblAddInventory.setText("Add Inventory");

		Label lblAddInvBrand = new Label(AddInvComp, SWT.NONE);
		lblAddInvBrand.setBounds(46, 51, 56, 16);
		lblAddInvBrand.setText("Brand:");

		Label lblAddInvModel = new Label(AddInvComp, SWT.NONE);
		lblAddInvModel.setBounds(42, 96, 56, 16);
		lblAddInvModel.setText("Model:");

		Label lblAddInvSize = new Label(AddInvComp, SWT.NONE);
		lblAddInvSize.setBounds(56, 140, 56, 16);
		lblAddInvSize.setText("Size:");

		Label lblAddInvQuantity = new Label(AddInvComp, SWT.NONE);
		lblAddInvQuantity.setBounds(32, 184, 56, 16);
		lblAddInvQuantity.setText("Quantity:");

		Label lblAddInvPurchasePrice = new Label(AddInvComp, SWT.NONE);
		lblAddInvPurchasePrice.setBounds(2, 229, 88, 16);
		lblAddInvPurchasePrice.setText("Purchase Price:");

		addInvBrand = new Text(AddInvComp, SWT.BORDER);
		addInvBrand.setBounds(87, 51, 219, 19);

		addInvBrandError = new Text(AddInvComp, SWT.BORDER);
		addInvBrandError.setBounds(87, 73, 219, 19);
		addInvBrandError.setEditable(false);

		addInvModel = new Text(AddInvComp, SWT.BORDER);
		addInvModel.setBounds(87, 95, 219, 19);

		addInvModelError = new Text(AddInvComp, SWT.BORDER);
		addInvModelError.setBounds(87, 117, 219, 19);
		addInvModelError.setEditable(false);

		addInvSize = new Text(AddInvComp, SWT.BORDER);
		addInvSize.setBounds(87, 139, 219, 19);

		addInvSizeError = new Text(AddInvComp, SWT.BORDER);
		addInvSizeError.setBounds(87, 161, 219, 19);
		addInvSizeError.setEditable(false);

		addInvQuantity = new Text(AddInvComp, SWT.BORDER);
		addInvQuantity.setBounds(87, 183, 219, 19);

		addInvQuantityError = new Text(AddInvComp, SWT.BORDER);
		addInvQuantityError.setBounds(87, 205, 219, 19);
		addInvQuantityError.setEditable(false);

		addInvPurchasePrice = new Text(AddInvComp, SWT.BORDER);
		addInvPurchasePrice.setBounds(87, 227, 219, 19);

		addInvPurchasePriceError = new Text(AddInvComp, SWT.BORDER);
		addInvPurchasePriceError.setBounds(87, 249, 219, 19);
		addInvPurchasePriceError.setEditable(false);

		Button btnAddInvSubmit = new Button(AddInvComp, SWT.NONE);
		btnAddInvSubmit.setBounds(97, 319, 70, 21);
		btnAddInvSubmit.setText("Submit");

		Button btnClearAddInv = new Button(AddInvComp, SWT.NONE);
		btnClearAddInv.setBounds(224, 319, 70, 21);
		btnClearAddInv.setText("Clear");

		Button btnXAddInv = new Button(AddInvComp, SWT.NONE);
		btnXAddInv.setBounds(297, 10, 21, 21);
		btnXAddInv.setText("X");

		Label lblAddInvSalePrice = new Label(AddInvComp, SWT.NONE);
		lblAddInvSalePrice.setText("Sale Price:");
		lblAddInvSalePrice.setBounds(28, 272, 64, 16);

		addInvSalePrice = new Text(AddInvComp, SWT.BORDER);
		addInvSalePrice.setBounds(87, 271, 219, 19);

		addInvSalePriceError = new Text(AddInvComp, SWT.BORDER);
		addInvSalePriceError.setEditable(false);
		addInvSalePriceError.setBounds(87, 293, 219, 19);
		new Label(InventoryComposite, SWT.NONE);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");

		Composite CustomerComposite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(CustomerComposite);
		GridLayout gl_CustomerComposite = new GridLayout(1, true);
		CustomerComposite.setLayout(gl_CustomerComposite);

		Composite SearchAcctComp = new Composite(CustomerComposite, SWT.NONE);
		GridData gd_SearchAcctComp = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_SearchAcctComp.heightHint = 254;
		gd_SearchAcctComp.widthHint = 354;
		SearchAcctComp.setLayoutData(gd_SearchAcctComp);
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
		new Label(SearchCustomerTitleComposite, SWT.NONE).setText("Labelmaker 8");
		new Label(SearchCustomerTitleComposite, SWT.NONE).setText("Labelmaker 7");

		btnXSearchAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Search Account");
				SearchAcctComp.setVisible(false);
				// Cust2ButtonComp.setEnabled(true);
			}
		});

		Composite SearchCustomerEverythingElseComposite = new Composite(SearchAcctComp, SWT.NONE);
		SearchCustomerEverythingElseComposite.setLayout(new GridLayout(4, true));
		GridData gd_SearchCustomerEverythingElseComposite = new GridData(
				GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd_SearchCustomerEverythingElseComposite.grabExcessVerticalSpace = false;
		gd_SearchCustomerEverythingElseComposite.heightHint = 328;
		SearchCustomerEverythingElseComposite.setLayoutData(gd_SearchCustomerEverythingElseComposite);

		Label lblNewLabel_3 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel_3.setText("Name");

		searchName = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		GridData gd_searchName = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_searchName.widthHint = 300;
		searchName.setLayoutData(gd_searchName);

		Label lblNewLabel_4 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel_4.setText("Phone #");

		searchPhone = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		GridData gd_searchPhone = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
		gd_searchPhone.widthHint = 300;
		searchPhone.setLayoutData(gd_searchPhone);

		Label lblNewLabel_5 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel_5.setText("Address");

		searchAddress = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		GridData gd_searchAddress = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
		gd_searchAddress.widthHint = 300;
		searchAddress.setLayoutData(gd_searchAddress);

		Label lblNewLabel_6 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNewLabel_6.setText("Email");

		searchEmail = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		GridData gd_searchEmail = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
		gd_searchEmail.widthHint = 300;
		searchEmail.setLayoutData(gd_searchEmail);
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		// new Label(SearchCustomerEverythingElseComposite,
		// SWT.NONE).setText("Labelmaker 4");

		searchCustomerError = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER | SWT.RIGHT);
		GridData gd_searchCustomerError = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_searchCustomerError.widthHint = 300;
		searchCustomerError.setLayoutData(gd_searchCustomerError);
		searchCustomerError.setEditable(false);

		Button btnCustomerSearch = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
		btnCustomerSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fillTableCustomer(tableCustomer, CustomerDao.generateQueryCustomer(searchName.getText(), searchAddress.getText(), searchPhone.getText(), searchEmail.getText()));
			}
		});
		btnCustomerSearch.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		btnCustomerSearch.setText("Search");

		Button btnNewCustomerPageOpen = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
		btnNewCustomerPageOpen.setText("Create New Customer Account");
		btnNewCustomerPageOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewCustomerPageOpen.setText("New Button");
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE).setText("Labelmaker 5?");
		Label label = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		label.setText("Labelmaker 3");
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE);

		Composite compositeCustomerTable = new Composite(CustomerComposite, SWT.NONE);
		compositeCustomerTable.setLayout(new GridLayout(1, false));
		compositeCustomerTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

/*	----------------------------------- Customer Table --------------------------------------------------*/		
		
		tableCustomer = new Table(compositeCustomerTable, SWT.BORDER | SWT.FULL_SELECTION);
		tableCustomer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableCustomer.setHeaderVisible(true);
		tableCustomer.setLinesVisible(true);
		new Label(CustomerComposite, SWT.NONE);
		createAndNameColumns(tableCustomer, customerColumns);
		//insert input validation here please ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//move this to an onclick as well
		fillTableCustomer(tableCustomer, CustomerDao.generateQueryCustomer("","","",""));
		
		Composite NewAcctComp = new Composite(CustomerComposite, SWT.NONE);
		GridData gd_NewAcctComp = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_NewAcctComp.heightHint = 250;
		NewAcctComp.setLayoutData(gd_NewAcctComp);
		NewAcctComp.setLayout(new GridLayout(4, true));
		new Label(NewAcctComp, SWT.NONE).setText("Labelmaker 1");

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
				// Cust2ButtonComp.setEnabled(true);
			}
		});

		Label lblNewCustomer = new Label(NewAcctComp, SWT.NONE);
		lblNewCustomer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewCustomer.setText("Name");

		newName = new Text(NewAcctComp, SWT.BORDER);
		newName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
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
		new Label(NewAcctComp, SWT.NONE).setText("Labelmaker 9");

		NewCustomerError = new Text(NewAcctComp, SWT.BORDER | SWT.READ_ONLY);
		NewCustomerError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(NewAcctComp, SWT.NONE).setText("Labelmaker 10");

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
				session.save(cus);
				session.getTransaction().commit();
			}
		});
		new Label(NewAcctComp, SWT.NONE).setText("Labelmaker 6?");

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

		btnAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Add Inventory");
				AddInvComp.setVisible(true);
				Inv2ButtonComp.setEnabled(false);
			}
		});

		btnXAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Add Inventory)");
				AddInvComp.setVisible(false);
				Inv2ButtonComp.setEnabled(true);
			}
		});

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
				int intQuantity = Integer.parseInt(addInvQuantity.getText());
				inv.setCount(intQuantity);
				Double purchasePrice = Double.parseDouble(addInvPurchasePrice.getText());
				inv.setPurchase_price(purchasePrice);
				Double salePrice = Double.parseDouble(addInvSalePrice.getText());
				inv.setSale_price(salePrice);
				int width = Integer.parseInt(addInvSize.getText().substring(0, 3));
				inv.setWidth(width);
				int aspectRatio = Integer.parseInt(addInvSize.getText().substring(4, 6));
				inv.setAspectratio(aspectRatio);
				int diameter = Integer.parseInt(addInvSize.getText().substring(7, 9));
				inv.setDiameter(diameter);
				session.save(inv);
				session.getTransaction().commit();
			}
		});

		btnClearAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Clear (Add Inventory)");
				addInvBrand.setText("");
				addInvBrandError.setText("");
				addInvModel.setText("");
				addInvModelError.setText("");
				addInvSize.setText("");
				addInvSizeError.setText("");
				addInvQuantity.setText("");
				addInvQuantityError.setText("");
				addInvPurchasePrice.setText("");
				addInvPurchasePriceError.setText("");
				addInvSalePrice.setText("");
				addInvSalePriceError.setText("");
			}
		});
	}

	public void createAndNameColumns(Table table, String[] titles) {
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setWidth(100);
		}
	}
	// make this do subsets, not just spit everything out, possibly dao as parameter
//	public void fillTableCustomer(Table table){
//		CustomerDao customerDao = new CustomerDao();
//		for (Customer cust : customerDao.getAllCustomer()) {
//			TableItem tableItem = new TableItem(table, SWT.NONE);
//			tableItem.setText(new String[] {cust.get});
//		}
//	}
	
	public static void fillTableCustomer(Table table, List<Customer> myCustomers) {
		for (Customer cust : myCustomers) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { cust.getName(), cust.getPhone(), cust.getAddress(), cust.getEmail()});
		}
	}

	public void fillTableProductsSimple(Table table) {
		InventoryDao inventoryDao = new InventoryDao();
		for (Inventory inv : inventoryDao.getAllInventory()) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { inv.getBrand(), inv.getModel_number(), inv.getSize(),
					String.valueOf(inv.getCount()), String.valueOf(inv.getSale_price()) });
		}
	}

	public void fillTableProductsExtensive(Table table) {
		InventoryDao inventoryDao = new InventoryDao();
		for (Inventory inv : inventoryDao.getAllInventory()) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { String.valueOf(inv.getId()), inv.getBrand(), inv.getModel_number(),
					String.valueOf(inv.getSale_price()), String.valueOf(inv.getPurchase_price()),
					String.valueOf(inv.getCount()), String.valueOf(inv.getWidth()), inv.getSize(),
					String.valueOf(inv.getAspectratio()), String.valueOf(inv.getDiameter()) });
		}
	}
}

//public void fillProductsTable(Thing myThing){ //not the final method. just proof of concept
//TableItem item = new TableItem(productsTable, SWT.NONE);
//item.setText(Integer.toString(myThing.id));
//}

/*
 * "Remove Inventory Composite. Deprecated for clicking on inventory item in
 * table and interacting with popup
 */

//Composite RmvInvComp = new Composite(InventoryComposite, SWT.NONE);
//
//Label lblRmvInv = new Label(RmvInvComp, SWT.NONE);
//lblRmvInv.setBounds(113, 20, 102, 16);
//lblRmvInv.setText("Remove Inventory");
//
//Label lblRmvInvBrand = new Label(RmvInvComp, SWT.NONE);
//lblRmvInvBrand.setBounds(14, 51, 56, 16);
//lblRmvInvBrand.setText("Brand:");
//
//Label lblRmvInvModel = new Label(RmvInvComp, SWT.NONE);
//lblRmvInvModel.setBounds(10, 95, 56, 16);
//lblRmvInvModel.setText("Model:");
//
//Label lblRmvInvSize = new Label(RmvInvComp, SWT.NONE);
//lblRmvInvSize.setBounds(22, 138, 56, 16);
//lblRmvInvSize.setText("Size:");
//
//rmvInvBrand = new Text(RmvInvComp, SWT.BORDER);
//rmvInvBrand.setBounds(58, 51, 247, 19);
//
//rmvInvBrandError = new Text(RmvInvComp, SWT.BORDER);
//rmvInvBrandError.setBounds(58, 73, 247, 19);
//rmvInvBrandError.setEditable(false);
//
//rmvInvModel = new Text(RmvInvComp, SWT.BORDER);
//rmvInvModel.setBounds(58, 95, 247, 19);
//
//rmvInvModelError = new Text(RmvInvComp, SWT.BORDER);
//rmvInvModelError.setBounds(58, 117, 247, 19);
//rmvInvModelError.setEditable(false);
//
//rmvInvSize = new Text(RmvInvComp, SWT.BORDER);
//rmvInvSize.setBounds(58, 139, 247, 19);
//
//rmvInvSizeError = new Text(RmvInvComp, SWT.BORDER);
//rmvInvSizeError.setBounds(58, 161, 247, 19);
//rmvInvSizeError.setEditable(false);
//
//Button btnRmvInvSubmit = new Button(RmvInvComp, SWT.NONE);
//btnRmvInvSubmit.setBounds(68, 186, 70, 21);
//btnRmvInvSubmit.setText("Submit");
//
//Button btnRmvInvClear = new Button(RmvInvComp, SWT.NONE);
//btnRmvInvClear.setBounds(225, 186, 70, 21);
//btnRmvInvClear.setText("Clear");
//
//Button btnXRmvInv = new Button(RmvInvComp, SWT.NONE);
//btnXRmvInv.setBounds(297, 10, 21, 21);
//btnXRmvInv.setText("X");

/*
 * 
 * btnRmvInv.addSelectionListener(new SelectionAdapter() {
 * 
 * @Override public void widgetSelected(SelectionEvent e) {
 * System.out.println("Button : Remove Inventory"); RmvInvComp.setVisible(true);
 * Cust2ButtonComp.setEnabled(false); } });
 * 
 * btnXRmvInv.addSelectionListener(new SelectionAdapter() {
 * 
 * @Override public void widgetSelected(SelectionEvent e) {
 * System.out.println("Button : X (Remove Inventory)");
 * RmvInvComp.setVisible(false); Inv2ButtonComp.setEnabled(true); } });
 * 
 * btnRmvInvClear.addSelectionListener(new SelectionAdapter() {
 * 
 * @Override public void widgetSelected(SelectionEvent e) {
 * System.out.println("Button : Clear (Remove Inventory)");
 * rmvInvBrand.setText(""); rmvInvBrandError.setText("");
 * rmvInvModel.setText(""); rmvInvModelError.setText("");
 * rmvInvSize.setText(""); rmvInvSizeError.setText(""); } });
 * 
 * 
 * btnRmvInvSubmit.addSelectionListener(new SelectionAdapter() {
 * 
 * @Override public void widgetSelected(SelectionEvent e) {
 * System.out.println("Button : Submit (Remove Inventory)"); } });
 */