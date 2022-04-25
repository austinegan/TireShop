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
import seniorproject.dao.OrderProductDetailsDao;
import seniorproject.dao.WorkOrderDao;
import seniorproject.dao.CustomerDao;
import seniorproject.model.Customer;
import seniorproject.model.Inventory;
import seniorproject.model.OrderProductDetails;
import seniorproject.model.WorkOrder;
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
	private Text QuantityText;
	private Text ItemTotalCost;
	private Text SubtotalText;
	private Text TaxText;
	private Text CartTotalText;
	int plusCounter = 0;
	private Table orderDetailsTable;
	private Table productsTable;

	private static String[] allProductColumns;
	private static String[] someProductColumns;
	private static String[] customerColumns;
	private static String[] cartColumns;
	private static String[] workOrderColumns;
	private static String[] ordProdColumns;

	private Text addInvError;
	private Text NewCustomerError;
	private Table customerTable;

	/*
	 * \ Behind the scenes data for what the tables are currently showing
	 */

	private static List<Customer> customerPageList;
	private static List<Inventory> productsPageList;
	private static List<Inventory> cartPageList;
	private static List<Inventory> InvPageList;
	private static List<WorkOrder> workOrdersPageList;
	
	private static List<Customer> allCustomers;
	private static List<Inventory> allInventory;
	private static List<WorkOrder> allWorkOrders;

	private List<String> brandList;
	private List<String> modelList;
	private List<String> widthList;
	private List<String> ratioList;
	private List<String> diameterList;

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
	private Text editInvError;
	private Text textEditCustName;
	private Text textEditCustPhone;
	private Text textEditCustAddress;
	private Text textEditCustEmail;
	private Text textEditCustError;

	private Combo BrandCombo;
	private Combo ModelCombo;
	private Combo WidthCombo;
	private Combo RatioCombo;
	private Combo DiameterCombo;

	private Combo BrandComboInventory;
	private Combo ModelComboInventory;
	private Combo WidthComboInventory;
	private Combo RatioComboInventory;
	private Combo DiameterComboInventory;
	private Text txtBrand;
	private Text txtModel;
	private Text txtSize;
	private Text txtPurchasePrice;
	private Composite cartItemsComp;
	private Table tablePending;
	private Table tableCompleted;
	private Text textOrderNote;

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
		cartColumns = new String[] { "Brand", "Model", "Size", "Sale Price" };
		workOrderColumns = new String[] { "Order Num.", "Customer Name", "Time Created", "Time Updated", "Note" };
		ordProdColumns = new String[] { "Brand", "Model Number", "Size", "Count"};
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		
//		Customer mycust = CustomerDao.getCustomer(1);
//		for(WorkOrder wo : mycust.getOrders()) {
//			System.out.println(wo.getNumber());
//		}
		fillTableProductsSimple(productsTable, InventoryDao.getAllInventory());
		fillTableProductsExtensive(tableInv, InventoryDao.getAllInventory());
		fillOrderTable(tablePending, WorkOrderDao.getAllOrders(" ORDER BY time_create ASC"));
		fillOrderTable(tableCompleted, WorkOrderDao.getAllOrders(" ORDER BY time_update_status DESC"));
		fillAllCombos();
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

		/* --------------------------------------- Customer Composite (TAB) ---------------------------------- */

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
				// Cust2ButtonComp.setEnabled(true);
			}
		});

		Composite SearchCustomerEverythingElseComposite = new Composite(SearchAcctComp, SWT.NONE);
		SearchCustomerEverythingElseComposite.setLayout(new GridLayout(4, true));
		GridData gd_SearchCustomerEverythingElseComposite = new GridData(
				GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd_SearchCustomerEverythingElseComposite.grabExcessVerticalSpace = false;
		gd_SearchCustomerEverythingElseComposite.grabExcessHorizontalSpace = false;
		gd_SearchCustomerEverythingElseComposite.heightHint = 328;
		SearchCustomerEverythingElseComposite.setLayoutData(gd_SearchCustomerEverythingElseComposite);

		Label lblNewLabel_3 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("Name");

		searchName = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		// gd_searchName.widthHint = 300;
		searchName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label lblNewLabel_4 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("Phone #");

		searchPhone = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		// gd_searchPhone.widthHint = 300;
		searchPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label lblNewLabel_5 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("Address");

		searchAddress = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		// gd_searchAddress.widthHint = 300;
		searchAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label lblNewLabel_6 = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_6.setText("Email");

		searchEmail = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER);
		// gd_searchEmail.widthHint = 300;
		searchEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE);

		searchCustomerError = new Text(SearchCustomerEverythingElseComposite, SWT.BORDER | SWT.CENTER);
		// gd_searchCustomerError.widthHint = 300;
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
				// fillTableCustomer(customerTable, CustomerDao.getAllCustomer());
				// fillTableCustomer(customerTable, CustomerDao.getCustomerTestNamedParametersNative(searchName.getText(), searchPhone.getText(), searchAddress.getText(), searchEmail.getText(), "AND"));
				customerPageList = CustomerDao.generateQueryCustomer(searchName.getText(), searchAddress.getText(),
						searchPhone.getText(), searchEmail.getText(), true);
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

		/* -------------------------------------- Customer New Account Composite ---------------------------------- */

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
				// Cust2ButtonComp.setEnabled(true);
			}
		});

		Label lblNewCustomer = new Label(NewAcctComp, SWT.NONE);
		lblNewCustomer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewCustomer.setText("Name");

		newName = new Text(NewAcctComp, SWT.BORDER);
		newName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		/* This following 2 lines are a temporary solution to allow the creators to edit the composites as they work :) */
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
				// newNameError.setText("");
				// newPhoneError.setText("");
				// newAddressError.setText("");
				// newEmailError.setText("");
			}
		});
		new Label(NewAcctComp, SWT.NONE);

		Composite EditCustComp = new Composite(CustomerComposite, SWT.NONE);
		EditCustComp.setVisible(true);
		EditCustComp.setLayout(new GridLayout(4, true));
		new Label(EditCustComp, SWT.NONE);

		CLabel lblEditCust = new CLabel(EditCustComp, SWT.NONE);
		lblEditCust.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		lblEditCust.setText("Edit an Existing Customer Account");
		lblEditCust.setAlignment(SWT.RIGHT);
		Label label_1 = new Label(EditCustComp, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		Label lblEditCustName = new Label(EditCustComp, SWT.NONE);
		lblEditCustName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditCustName.setText("Name");

		textEditCustName = new Text(EditCustComp, SWT.BORDER);
		textEditCustName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditCustPhone = new Label(EditCustComp, SWT.NONE);
		lblEditCustPhone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditCustPhone.setText("Phone #");

		textEditCustPhone = new Text(EditCustComp, SWT.BORDER);
		textEditCustPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditCustAddress = new Label(EditCustComp, SWT.NONE);
		lblEditCustAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditCustAddress.setText("Address");

		textEditCustAddress = new Text(EditCustComp, SWT.BORDER);
		textEditCustAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditCustEmail = new Label(EditCustComp, SWT.NONE);
		lblEditCustEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditCustEmail.setText("Email");

		textEditCustEmail = new Text(EditCustComp, SWT.BORDER);
		GridData gd_textEditCustEmail = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_textEditCustEmail.widthHint = 325;
		textEditCustEmail.setLayoutData(gd_textEditCustEmail);
		new Label(EditCustComp, SWT.NONE);

		textEditCustError = new Text(EditCustComp, SWT.BORDER | SWT.READ_ONLY);
		textEditCustError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(EditCustComp, SWT.NONE);

		Button btnEditCustDeleteCustomer = new Button(EditCustComp, SWT.NONE);
		btnEditCustDeleteCustomer.setText("Delete Customer");

		Button btnEditCustSaveChanges = new Button(EditCustComp, SWT.NONE);
		btnEditCustSaveChanges.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		btnEditCustSaveChanges.setText("Save Changes");

		Button btnClear_1 = new Button(EditCustComp, SWT.NONE);
		btnClear_1.setText("Clear");

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

		BrandCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		// BrandCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		BrandCombo.setText("Brand");

		ModelCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		ModelCombo.setText("Model");

		WidthCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		WidthCombo.setText("Tire Width");

		RatioCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		RatioCombo.setText("Aspect Ratio");

		DiameterCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		DiameterCombo.setText("Diameter");
		/*
		 * fillBrandCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY brand"), BrandCombo); fillModelCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY model_number"), ModelCombo);
		 * fillWidthCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY width"), WidthCombo); fillAspectRatioCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY aspectratio"), RatioCombo);
		 * fillDiameterCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY diameter"), DiameterCombo);
		 */
		Button ProdBtnSearch = new Button(SearchMenuComp_1, SWT.NONE);
		ProdBtnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				productsTable.clearAll();
				fillTableProductsSimple(productsTable,
						InventoryDao.generateQueryInventoryCombos(BrandCombo.getText(), ModelCombo.getText(),
								WidthCombo.getText(), RatioCombo.getText(), DiameterCombo.getText(), true));
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
		CartComposite.setLayout(new GridLayout(3, false));

		Composite CheckoutComp = new Composite(CartComposite, SWT.NONE);

		txtCart = new Text(CheckoutComp, SWT.BORDER);
		txtCart.setText("Current Customer:");
		txtCart.setBounds(39, 10, 110, 26);

		currentCustomerText = new Text(CheckoutComp, SWT.BORDER);
		currentCustomerText.setEnabled(false);
		currentCustomerText.setBounds(145, 10, 184, 26);

		Label lblNewLabel_9 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_9.setBounds(10, 72, 55, 20);
		lblNewLabel_9.setText("Cart:");

		Label lblNewLabel_10 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_10.setBounds(256, 168, 22, 15);
		lblNewLabel_10.setText("Qty.");

		Composite composite_1 = new Composite(CheckoutComp, SWT.NONE);
		composite_1.setBounds(63, 136, 387, 165);
		composite_1.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(CartComposite, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));

		Label lblNewLabel_11 = new Label(composite, SWT.NONE);
		lblNewLabel_11.setText("Subtotal:");

		SubtotalText = new Text(composite, SWT.BORDER);

		Label lblNewLabel_11_1 = new Label(composite, SWT.NONE);
		lblNewLabel_11_1.setText("Tax(7.5%)");

		TaxText = new Text(composite, SWT.BORDER);

		Label lblNewLabel_11_1_1 = new Label(composite, SWT.NONE);
		lblNewLabel_11_1_1.setText("Total:");

		CartTotalText = new Text(composite, SWT.BORDER);

		Button btnClearCart = new Button(composite, SWT.NONE);
		btnClearCart.setText("Clear Cart");

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("Checkout");

		cartItemsComp = new Composite(CartComposite, SWT.NONE);
		cartItemsComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		cartItemsComp.setLayout(new GridLayout(9, false));
		new Label(cartItemsComp, SWT.NONE);

		txtBrand = new Text(cartItemsComp, SWT.BORDER);
		txtBrand.setText("Brand");
		txtBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		txtModel = new Text(cartItemsComp, SWT.BORDER);
		txtModel.setText("Model");
		txtModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		txtSize = new Text(cartItemsComp, SWT.BORDER);
		txtSize.setText("Size");
		txtSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		txtPurchasePrice = new Text(cartItemsComp, SWT.BORDER);
		txtPurchasePrice.setText("Purchase Price");
		txtPurchasePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);

		Button btnXSearchAccount_1 = new Button(cartItemsComp, SWT.NONE);
		btnXSearchAccount_1.setText("X");

		btnXSearchAccount_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Close Cart Item");
				// TireDescriptionText.setText(" ");
				ItemTotalCost.setText(" ");
				QuantityText.setText(" ");
			}
		});
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);

		Button button_1_2 = new Button(cartItemsComp, SWT.NONE);
		button_1_2.setText("-");
		button_1_2.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));

		QuantityText = new Text(cartItemsComp, SWT.BORDER);

		Button button_1_1_1 = new Button(cartItemsComp, SWT.NONE);
		button_1_1_1.setText("+");
		button_1_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));

		ItemTotalCost = new Text(cartItemsComp, SWT.BORDER);
		// TODO remove below line
		cartPageList = InventoryDao.generateQueryInventory("Michelin", "", "", "", "", true);
		fillCartItemsGridLayout();

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

		BrandComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		// BrandComboInventory.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		BrandComboInventory.setText("Brand");

		ModelComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		ModelComboInventory.setText("Model");

		WidthComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		WidthComboInventory.setText("Tire Width");

		RatioComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		RatioComboInventory.setText("Aspect Ratio");

		DiameterComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		DiameterComboInventory.setText("Diameter");
		/*
		 * fillBrandCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY brand"), BrandComboInventory); fillModelCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY model_number"),
		 * ModelComboInventory); fillWidthCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY width"), WidthComboInventory);
		 * fillAspectRatioCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY aspectratio"), RatioComboInventory); fillDiameterCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY diameter"),
		 * DiameterComboInventory);
		 */
		Button ProdBtnSearchInventory = new Button(SearchMenuComp_1_1, SWT.NONE);
		ProdBtnSearchInventory.setText("Search");
		ProdBtnSearchInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableInv.clearAll();
				fillTableProductsSimple(tableInv,
						InventoryDao.generateQueryInventoryCombos(BrandCombo.getText(), ModelCombo.getText(),
								WidthCombo.getText(), RatioCombo.getText(), DiameterCombo.getText(), true));
			}
		});

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
				// AddInvComp.setVisible(true);
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

		editInvError = new Text(AddInvComp_1, SWT.BORDER);
		editInvError.setEditable(false);
		editInvError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(AddInvComp_1, SWT.NONE);

		Button btnClearEditInv = new Button(AddInvComp_1, SWT.NONE);
		btnClearEditInv.setText("Clear");

		btnClearEditInv.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Clear (Edit Inventory)");
				editInvBrand.setText("");
				editInvModel.setText("");
				editInvSize.setText("");
				editInvQuantity.setText("");
				editInvPurchasePrice.setText("");
				editInvSalePrice.setText("");
				editInvError.setText("");
			}
		});

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

		btnClearAddInv.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Clear (Add Inventory)");
				addInvBrand.setText("");
				addInvModel.setText("");
				addInvSize.setText("");
				addInvQuantity.setText("");
				addInvPurchasePrice.setText("");
				addInvSalePrice.setText("");
				addInvError.setText("");
			}
		});

		new Label(AddInvComp, SWT.NONE);

		TabItem tbtmNewItem_4 = new TabItem(tabFolder, 0);
		tbtmNewItem_4.setText("Work Orders");

		Composite WorkOrderComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_4.setControl(WorkOrderComposite);
		WorkOrderComposite.setLayout(new GridLayout(1, false));

		TabFolder tabFolder_1 = new TabFolder(WorkOrderComposite, SWT.BOTTOM);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem tbtmPending = new TabItem(tabFolder_1, SWT.NONE);
		tbtmPending.setText("Pending");

		Composite composite_2 = new Composite(tabFolder_1, SWT.NONE);
		tbtmPending.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));

		tablePending = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		tablePending.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tablePending.setHeaderVisible(true);
		tablePending.setLinesVisible(true);

		TabItem tbtmCompleted = new TabItem(tabFolder_1, SWT.NONE);
		tbtmCompleted.setText("Completed");

		Composite composite_3 = new Composite(tabFolder_1, SWT.NONE);
		tbtmCompleted.setControl(composite_3);
		composite_3.setLayout(new GridLayout(1, false));

		tableCompleted = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		tableCompleted.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableCompleted.setHeaderVisible(true);
		tableCompleted.setLinesVisible(true);

		createAndNameColumns(tablePending, workOrderColumns);
		createAndNameColumns(tableCompleted, workOrderColumns);
		
		Composite composite_4 = new Composite(WorkOrderComposite, SWT.NONE);
		composite_4.setLayout(new GridLayout(3, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblCustomerName = new Label(composite_4, SWT.NONE);
		lblCustomerName.setText("Customer Name:");
		
		Label customerNameValue = new Label(composite_4, SWT.NONE);
		customerNameValue.setText("name");
		
		Button btnMarkForReview = new Button(composite_4, SWT.NONE);
		btnMarkForReview.setText("Mark for Review");
		
		Label lblCustomerPhone = new Label(composite_4, SWT.NONE);
		lblCustomerPhone.setText("Customer Phone:");
		
		Label customerPhoneValue = new Label(composite_4, SWT.NONE);
		customerPhoneValue.setText("phone #");
		
		Button btnMarkAsComplete = new Button(composite_4, SWT.NONE);
		btnMarkAsComplete.setText("Mark as Complete");
						new Label(composite_4, SWT.NONE);
				
						orderDetailsTable = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
						orderDetailsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
						orderDetailsTable.setHeaderVisible(true);
						orderDetailsTable.setLinesVisible(true);
				new Label(composite_4, SWT.NONE);
				
				Label lblOrderNotes = new Label(composite_4, SWT.NONE);
				lblOrderNotes.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblOrderNotes.setText("Order Notes: ");
				
				textOrderNote = new Text(composite_4, SWT.BORDER);
				textOrderNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
				
				createAndNameColumns(orderDetailsTable, ordProdColumns);
				//TEST
				fillOrderDetailsTable(orderDetailsTable, OrderProductDetailsDao.getDetailsFromOrderNum(2));
				Button btnSaveNote = new Button(composite_4, SWT.NONE);
				btnSaveNote.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});
				btnSaveNote.setText("Save Notes");
				
				Button btnBackOutOfOrder = new Button(composite_4, SWT.NONE);
				btnBackOutOfOrder.setText("<- Back");
				new Label(composite_4, SWT.NONE);
				new Label(composite_4, SWT.NONE);

//		btnXSpecificWorkOrders.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("Button : X (Specific Work Orders)");
//				specificWorkOrders.setVisible(false);
//				allWorkOrders.setEnabled(true);
//			}
//		});
	}

	public void createAndNameColumns(Table table, String[] titles) {
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			// TODO: make table columns resize their width automatically instead of being 100 px wide :)
			column.setWidth(125);
		}
	}

	public static void fillTableCustomer(Table table, List<Customer> myCustomers) {
		table.removeAll();
		for (Customer cust : myCustomers) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { cust.getName(), cust.getPhone(), cust.getAddress(), cust.getEmail() });
		}
	}

	public void fillTableProductsSimple(Table table, List<Inventory> myInventory) {
		table.removeAll();
		for (Inventory inv : myInventory) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { inv.getBrand(), inv.getModel_number(), inv.getSize(),
					String.valueOf(inv.getCount()), String.valueOf(inv.getSale_price()) });
		}
	}

	public void fillTableProductsExtensive(Table table, List<Inventory> myInventory) {
		table.removeAll();
		for (Inventory inv : myInventory) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { String.valueOf(inv.getId()), inv.getBrand(), inv.getModel_number(),
					String.valueOf(inv.getSale_price()), String.valueOf(inv.getPurchase_price()),
					String.valueOf(inv.getCount()), String.valueOf(inv.getWidth()), inv.getSize(),
					String.valueOf(inv.getAspectratio()), String.valueOf(inv.getDiameter()) });
		}
	}

	public void fillOrderTable(Table table, List<WorkOrder> myOrders) {
		table.removeAll();
		for (WorkOrder ord : myOrders) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { String.valueOf(ord.getNumber()), ord.getCust().getName(), ord.getTime_create().toString(), ord.getTime_update().toString(), ord.getNote() });
		}
	}
	
	public void fillOrderDetailsTable(Table table, List<OrderProductDetails> details) {
		table.removeAll();
		for(OrderProductDetails detail : details) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { detail.getBrand(), detail.getModel_number(), detail.getSize(), String.valueOf(detail.getCount())});
		}
	}

	public void fillCombo(List<String> dropdownStrings, Combo combo, String comboName) {
		combo.add(comboName);
		for (String option : dropdownStrings) {
			combo.add(option);
		}
	}

	public void fillProductCombos() {
		fillCombo(brandList = InventoryDao.comboQuery("brand"), BrandCombo, "Brand");
		fillCombo(modelList = InventoryDao.comboQuery("model_number"), ModelCombo, "Model");
		fillCombo(widthList = InventoryDao.comboQuery("width"), WidthCombo, "Tire Width");
		fillCombo(ratioList = InventoryDao.comboQuery("aspectratio"), RatioCombo, "Aspect Ratio");
		fillCombo(diameterList = InventoryDao.comboQuery("diameter"), DiameterCombo, "Diameter");
	}

	public void fillInventoryCombos() {
		fillCombo(brandList = InventoryDao.comboQuery("brand"), BrandComboInventory, "Brand");
		fillCombo(modelList = InventoryDao.comboQuery("model_number"), ModelComboInventory, "Model");
		fillCombo(widthList = InventoryDao.comboQuery("width"), WidthComboInventory, "Tire Width");
		fillCombo(ratioList = InventoryDao.comboQuery("aspectratio"), RatioComboInventory, "Aspect Ratio");
		fillCombo(diameterList = InventoryDao.comboQuery("diameter"), DiameterComboInventory, "Diameter");
	}

	public void fillAllCombos() {
		fillProductCombos();
		fillInventoryCombos();
	}

	public void fillCartItemsGridLayout() {
		// cartItemsComp = null;
		// myLabel = new Text();
		for (Control con : cartItemsComp.getChildren()) {
			con.dispose();
		}
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE).setText("Brand");
		new Label(cartItemsComp, SWT.NONE).setText("Model");
		new Label(cartItemsComp, SWT.NONE).setText("Size");
		new Label(cartItemsComp, SWT.NONE).setText("Purchase Price");
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		for (Inventory inv : cartPageList) {
			// x button
			Button btnXSearchAccount_1 = new Button(cartItemsComp, SWT.NONE);
			btnXSearchAccount_1.setText("X");
			btnXSearchAccount_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO confirmation dialogue
					cartPageList.remove(inv);
					fillCartItemsGridLayout();
				}
			});

			new Label(cartItemsComp, SWT.NONE).setText(inv.getBrand());
			new Label(cartItemsComp, SWT.NONE).setText(inv.getModel_number());
			new Label(cartItemsComp, SWT.NONE).setText(inv.getSize());
			new Label(cartItemsComp, SWT.NONE).setText(String.valueOf(inv.getSale_price()));

			Button cartItemsMinus = new Button(cartItemsComp, SWT.NONE);
			cartItemsMinus.setText("-");
			Text QuantityText = new Text(cartItemsComp, SWT.BORDER);
			QuantityText.setText(String.valueOf(inv.getCount()));
			Button cartItemsPlus = new Button(cartItemsComp, SWT.NONE);
			cartItemsPlus.setText("+");
			Text ItemTotalCost = new Text(cartItemsComp, SWT.BORDER);
			ItemTotalCost.setText(String.valueOf(inv.getCount() * inv.getSale_price()));

			cartItemsMinus.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO check that we can actually reduce, , possibly use logic structure from ProductList
					inv.setCount(inv.getCount() - 1);
					QuantityText.setText(String.valueOf(inv.getCount()));
					ItemTotalCost.setText(String.valueOf(inv.getCount() * inv.getSale_price()));
				}
			});
			cartItemsPlus.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO check that we can actually increase, possibly use logic structure from ProductList
					inv.setCount(inv.getCount() + 1);
					QuantityText.setText(String.valueOf(inv.getCount()));
					ItemTotalCost.setText(String.valueOf(inv.getCount() * inv.getSale_price()));
				}
			});
		} // end for loop
		cartItemsComp.layout();
	}
	
	public void getOrderProdFromListVar() {
		
	}
	
	public void getAllOrdersProdByQuery() {	//and store in the global variable
		
	} 
	
	public void getOrderProdByQuery(int orderID) {
		
	}
}