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
import seniorproject.dao.OrdCustDao;
import seniorproject.dao.OrderProductDetailsDao;
import seniorproject.dao.OrderProductRelationDao;
import seniorproject.dao.WorkOrderDao;
import seniorproject.dao.CustomerDao;
import seniorproject.model.Customer;
import seniorproject.model.Inventory;
import seniorproject.model.OrdCust;
import seniorproject.model.OrderProductDetails;
import seniorproject.model.OrderProductRelation;
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
import org.eclipse.swt.custom.StackLayout;

//TODO INVENTORY TAB IS NOT POPULATING WHEN USING DROPDOWNS

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
	private Button customerBroadSearch;
	private Button btnProductsBroadSearch;
	private Button btnInventoryBroadSearch;
	private Inventory selectedProduct;

	private static String[] allProductColumns;
	private static String[] someProductColumns;
	private static String[] customerColumns;
	private static String[] cartColumns;
	private static String[] workOrderColumns;
	private static String[] ordProdColumns;

	private Text addInvError;
	private Text NewCustomerError;
	private Table customerTable;

	private OrdCust activeDetails;

	/*
	 * \ Behind the scenes data for what the tables are currently showing
	 */

	private static List<Customer> customerPageList;
	private static List<Inventory> productsPageList;
	private static List<Inventory> cartPageList;
	private static List<Inventory> invPageList;
	private static List<WorkOrder> workOrdersPageList;

	private static List<Customer> allCustomers;
	private static List<Inventory> allInventory;
	private static List<WorkOrder> allWorkOrders;
	private static List<OrdCust> pendingOrders;
	private static List<OrdCust> completedOrders;
	private static Map<Integer, Inventory> mapInventory;
	private static Map<Integer, Inventory> mapCart;

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
	private int cartTotal;
	private long checkoutAmount;
	private Customer activeCustomer;
	private Inventory invPageSelected;

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
				"Width", "Size", "Aspect Ratio", "Diameter" };
		someProductColumns = new String[] { "Brand", "Model", "Size", "Quantity", "Sale Price" };
		customerColumns = new String[] { "Name", "Phone", "Address", "Email" };
		cartColumns = new String[] { "Brand", "Model", "Size", "Sale Price" };
		workOrderColumns = new String[] { "Order Num.", "Customer Name", "Time Created", "Time Updated", "Note" };
		ordProdColumns = new String[] { "Brand", "Model Number", "Size", "Count" };
		mapCart = new HashMap<>();
		cartPageList = new ArrayList<Inventory>();
		Txt.setup();
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
		updateAllInventory();
		productsPageList = allInventory;
		fillTableProductsSimple(productsTable, productsPageList);
		invPageList = allInventory;
		fillTableProductsExtensive(tableInv, invPageList);
		updateWorkOrderTables();
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
		StackLayout customerStack = new StackLayout();
		CustomerComposite.setLayout(customerStack);

		Composite SearchCustComp = new Composite(CustomerComposite, SWT.NONE);
		SearchCustComp.setVisible(true);
		SearchCustComp.setLayout(new GridLayout(1, false));

		Composite SearchCustomerTitleComposite = new Composite(SearchCustComp, SWT.NONE);
		SearchCustomerTitleComposite.setLayout(new GridLayout(1, false));
		SearchCustomerTitleComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		CLabel lblNewLabel_2 = new CLabel(SearchCustomerTitleComposite, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setText("Search Customer");

		Composite SearchCustomerEverythingElseComposite = new Composite(SearchCustComp, SWT.NONE);
		GridLayout gl_SearchCustomerEverythingElseComposite = new GridLayout(4, true);
		gl_SearchCustomerEverythingElseComposite.marginBottom = 50;
		SearchCustomerEverythingElseComposite.setLayout(gl_SearchCustomerEverythingElseComposite);
		GridData gd_SearchCustomerEverythingElseComposite = new GridData(
				GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd_SearchCustomerEverythingElseComposite.grabExcessVerticalSpace = false;
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

		Button newCustomerButton = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);

		newCustomerButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		newCustomerButton.setText("New Customer");

		Button editCustomerBtn = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
		editCustomerBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		editCustomerBtn.setText("Edit Customer");

		Button selectCustomerBtn = new Button(SearchCustomerEverythingElseComposite, SWT.NONE);
		selectCustomerBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		selectCustomerBtn.setText("Select Customer");

		customerBroadSearch = new Button(SearchCustomerEverythingElseComposite, SWT.CHECK);
		customerBroadSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		customerBroadSearch.setText("Enable Broad Search");
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		new Label(SearchCustomerEverythingElseComposite, SWT.NONE);

		Label custCompButtonsErrorField = new Label(SearchCustomerEverythingElseComposite, SWT.NONE);
		custCompButtonsErrorField.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 4, 1));
		custCompButtonsErrorField.setAlignment(SWT.CENTER);

		Composite customerTableComposite = new Composite(SearchCustComp, SWT.NONE);
		customerTableComposite.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true, 1, 1));
		customerTableComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		customerTable = new Table(customerTableComposite, SWT.BORDER | SWT.FULL_SELECTION);
		customerTable.setHeaderVisible(true);
		customerTable.setLinesVisible(true);

		createAndNameColumns(customerTable, customerColumns);
		customerPageList = CustomerDao.getAllCustomer();
		fillTableCustomer(customerTable, customerPageList);

		/* -------------------------------------- Customer New Account Composite ---------------------------------- */

		Composite NewCustComp = new Composite(CustomerComposite, SWT.NONE);
		NewCustComp.setLayout(new GridLayout(4, true));
		new Label(NewCustComp, SWT.NONE);

		CLabel lblCreateANew = new CLabel(NewCustComp, SWT.NONE);
		lblCreateANew.setAlignment(SWT.RIGHT);
		lblCreateANew.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		lblCreateANew.setText("Create a New Customer Account");

		Button btnXCreateAccount = new Button(NewCustComp, SWT.NONE);
		btnXCreateAccount.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnXCreateAccount.setText("X");

		Label lblNewCustomer = new Label(NewCustComp, SWT.NONE);
		lblNewCustomer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewCustomer.setText("Name");

		newName = new Text(NewCustComp, SWT.BORDER);
		newName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblNewPhone = new Label(NewCustComp, SWT.NONE);
		lblNewPhone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewPhone.setText("Phone #");

		newPhone = new Text(NewCustComp, SWT.BORDER);
		newPhone.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblNewAddress = new Label(NewCustComp, SWT.NONE);
		lblNewAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewAddress.setText("Address");

		newAddress = new Text(NewCustComp, SWT.BORDER);
		newAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblNewEmail = new Label(NewCustComp, SWT.NONE);
		lblNewEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewEmail.setText("Email");

		newEmail = new Text(NewCustComp, SWT.BORDER);
		GridData gd_newEmail = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_newEmail.widthHint = 325;
		newEmail.setLayoutData(gd_newEmail);
		new Label(NewCustComp, SWT.NONE);

		NewCustomerError = new Text(NewCustComp, SWT.BORDER | SWT.READ_ONLY);
		NewCustomerError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(NewCustComp, SWT.NONE);

		Button btnCreateCustomer = new Button(NewCustComp, SWT.NONE);
		btnCreateCustomer.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		btnCreateCustomer.setText("Create Customer");

		Button btnClear = new Button(NewCustComp, SWT.NONE);
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

		Composite EditCustComp = new Composite(CustomerComposite, SWT.NONE);
		EditCustComp.setVisible(true);
		EditCustComp.setLayout(new GridLayout(4, true));
		new Label(EditCustComp, SWT.NONE);

		CLabel lblEditCust = new CLabel(EditCustComp, SWT.NONE);
		lblEditCust.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		lblEditCust.setText("Edit an Existing Customer Account");
		lblEditCust.setAlignment(SWT.RIGHT);
//		Label label_1 = new Label(EditCustComp, SWT.NONE);
		// label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		Button btnXEditAccount = new Button(EditCustComp, SWT.NONE);

		btnXEditAccount.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnXEditAccount.setText("X");
		new Label(NewCustComp, SWT.NONE);

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

		// btnClear_1.setText("Clear");
		new Label(EditCustComp, SWT.NONE);
		new Label(EditCustComp, SWT.NONE);
		new Label(EditCustComp, SWT.NONE);
		new Label(EditCustComp, SWT.NONE);
		new Label(EditCustComp, SWT.NONE);

		customerStack.topControl = SearchCustComp;

		searchCustomerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				customerTable.clearAll();
				// fillTableCustomer(customerTable, CustomerDao.getAllCustomer());
				// fillTableCustomer(customerTable, CustomerDao.getCustomerTestNamedParametersNative(searchName.getText(), searchPhone.getText(), searchAddress.getText(), searchEmail.getText(), "AND"));
				customerPageList = CustomerDao.generateQueryCustomer(searchName.getText(), searchPhone.getText(),
						searchAddress.getText(), searchEmail.getText(), customerBroadSearch.getSelection());
				fillTableCustomer(customerTable, customerPageList);
			}
		});

		newCustomerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : New Customer");
				switchStackLayoutToShowArgument(NewCustComp);
			}
		});

		editCustomerBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Edit Customer");
				if (customerTable.getSelectionCount() < 1) {
					System.out.println("no customer selected");
					custCompButtonsErrorField.setText(
							"You must select a customer from the table before attempting to edit a customer account.");
				} else if (customerTable.getSelectionCount() > 1) {
					System.out.println("too many customers selected");
					custCompButtonsErrorField.setText(
							"Too many customers selected in the table. Please select only one customer before attempting to edit a customer account.");
				} else {
					int row = customerTable.getSelectionIndex();
					Customer editingCustomer = customerPageList.get(row);
					System.out.println("Editing customer in row " + String.valueOf(row) + " (zero indexed)");
					System.out.println("customer id " + String.valueOf(editingCustomer.getId()) + " and Name is "
							+ String.valueOf(customerPageList.get(row).getName()));
					textEditCustName.setText(editingCustomer.getName());
					textEditCustPhone.setText(editingCustomer.getPhone());
					textEditCustAddress.setText(editingCustomer.getAddress());
					textEditCustEmail.setText(editingCustomer.getEmail());
					// TODO populate
					switchStackLayoutToShowArgument(EditCustComp);
				}
			}
		});

		selectCustomerBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Select Customer");
				// TODO set active Customer
				if (customerTable.getSelectionCount() < 1) {
					System.out.println("no customer selected");
					custCompButtonsErrorField.setText(
							"You must select a customer from the table before attempting to edit a customer account.");
				} else if (customerTable.getSelectionCount() > 1) {
					System.out.println("too many customers selected");
					custCompButtonsErrorField.setText(
							"Too many customers selected in the table. Please select only one customer before attempting to edit a customer account.");
				} else {
					int row = customerTable.getSelectionIndex();
					activeCustomer = customerPageList.get(row);
					System.out.println("Selected customer in row " + String.valueOf(row) + " (zero indexed)");
					System.out.println("customer id " + String.valueOf(activeCustomer.getId()) + " and Name is "
							+ String.valueOf(customerPageList.get(row).getName()));
				}
			}
		});

		btnXCreateAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Create Account)");
				switchStackLayoutToShowArgument(SearchCustComp);
			}
		});

		btnXEditAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Edit Account)");
				switchStackLayoutToShowArgument(SearchCustComp);
			}
		});

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

		btnEditCustDeleteCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Delete Customer");
				// TODO remove from DB. possibly ask for confirmation
			}
		});

		btnEditCustSaveChanges.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Save Changes (Customer)");
				// TODO edit DB entry. possibly ask for confirmation
			}
		});

		/* -------------------------------------------------- Products Tab ------------------------------------------------- */

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, 0);
		tbtmNewItem_1.setText("Products");

		Composite ProductsComposite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(ProductsComposite_1);
		ProductsComposite_1.setLayout(new GridLayout(2, false));

		Composite composite_5 = new Composite(ProductsComposite_1, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_5.setLayout(new GridLayout(1, false));

		Composite SearchMenuComp_1 = new Composite(composite_5, SWT.NONE);
		SearchMenuComp_1.setLayout(new GridLayout(7, false));
		SearchMenuComp_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Label lblBrand_1 = new Label(SearchMenuComp_1, SWT.NONE);
		lblBrand_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblBrand_1.setText("Brand");

		Label lblModel_1 = new Label(SearchMenuComp_1, SWT.NONE);
		lblModel_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblModel_1.setText("Model");

		Label lblTireWidth = new Label(SearchMenuComp_1, SWT.NONE);
		lblTireWidth.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblTireWidth.setText("Tire Width");

		Label lblAspectRatio = new Label(SearchMenuComp_1, SWT.NONE);
		lblAspectRatio.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblAspectRatio.setText("Aspect Ratio");

		Label lblDiameter = new Label(SearchMenuComp_1, SWT.NONE);
		lblDiameter.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblDiameter.setText("Diameter");
		new Label(SearchMenuComp_1, SWT.NONE);
		new Label(SearchMenuComp_1, SWT.NONE);

		BrandCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		BrandCombo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		// BrandCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));

		ModelCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		ModelCombo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		WidthCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		WidthCombo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		RatioCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		RatioCombo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		DiameterCombo = new Combo(SearchMenuComp_1, SWT.NONE);
		DiameterCombo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		/*
		 * fillBrandCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY brand"), BrandCombo); fillModelCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY model_number"), ModelCombo);
		 * fillWidthCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY width"), WidthCombo); fillAspectRatioCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY aspectratio"), RatioCombo);
		 * fillDiameterCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY diameter"), DiameterCombo);
		 */
		Button ProdBtnSearch = new Button(SearchMenuComp_1, SWT.NONE);
		ProdBtnSearch.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		ProdBtnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				productsTable.clearAll();
				productsPageList = InventoryDao.generateQueryInventory(BrandCombo.getText(), ModelCombo.getText(),
						WidthCombo.getText(), RatioCombo.getText(), DiameterCombo.getText(),
						btnProductsBroadSearch.getSelection(), " ORDER BY id");
				fillTableProductsSimple(productsTable, productsPageList);
			}
		});
		ProdBtnSearch.setText("Search");

		btnProductsBroadSearch = new Button(SearchMenuComp_1, SWT.CHECK);
		btnProductsBroadSearch.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnProductsBroadSearch.setText("Enable Broad Search");

		Composite SearchResultsComp = new Composite(composite_5, SWT.NONE);
		SearchResultsComp.setLayout(new FillLayout(SWT.HORIZONTAL));
		SearchResultsComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		SearchResultsComp.setVisible(true);

		productsTable = new Table(SearchResultsComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		productsTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		productsTable.setHeaderVisible(true);
		productsTable.setLinesVisible(true);

		createAndNameColumns(productsTable, someProductColumns);

		Button btnProductsPageEditCartCount = new Button(composite_5, SWT.NONE);
		btnProductsPageEditCartCount.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnProductsPageEditCartCount.setText("Edit Cart Count for Selected Product");

		Composite CartPopupComp = new Composite(ProductsComposite_1, SWT.NONE);
		CartPopupComp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1));
		GridLayout gl_CartPopupComp = new GridLayout(3, false);
		gl_CartPopupComp.marginWidth = 50;
		CartPopupComp.setLayout(gl_CartPopupComp);

		Label CartPopupTitle = new Label(CartPopupComp, SWT.NONE);
		CartPopupTitle.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		CartPopupTitle.setText("Add / Remove From Cart");

		Label lblBrand = new Label(CartPopupComp, SWT.NONE);
		lblBrand.setText("Brand");
		new Label(CartPopupComp, SWT.NONE);

		Label lblModel = new Label(CartPopupComp, SWT.NONE);
		lblModel.setText("Model");

		Label lblBrandHere = new Label(CartPopupComp, SWT.NONE);
		lblBrandHere.setText("brand here");
		new Label(CartPopupComp, SWT.NONE);

		Label lblModelHere = new Label(CartPopupComp, SWT.NONE);
		lblModelHere.setText("model here");
		new Label(CartPopupComp, SWT.NONE);

		Label lblCountInCart = new Label(CartPopupComp, SWT.NONE);
		lblCountInCart.setText("Count in Cart");
		new Label(CartPopupComp, SWT.NONE);

		Button decreaseBtn = new Button(CartPopupComp, SWT.NONE);

		decreaseBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		decreaseBtn.setText("-");

		Label cartCountLbl = new Label(CartPopupComp, SWT.BORDER);
		cartCountLbl.setText("a number");

		Button increaseBtn = new Button(CartPopupComp, SWT.NONE);

		increaseBtn.setText("+");

		Label addRemoveCartError = new Label(CartPopupComp, SWT.NONE);
		addRemoveCartError.setText("error field");
		new Label(CartPopupComp, SWT.NONE);
		new Label(CartPopupComp, SWT.NONE);

		btnProductsPageEditCartCount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Edit Cart Count");
				if (productsTable.getSelectionCount() < 1) {
					System.out.println("No item selected");
					addRemoveCartError.setText(
							"You must select an item from the table before attempting to edit an inventory item.");
				} else if (productsTable.getSelectionCount() > 1) {
					System.out.println("Too many items selected");
					addRemoveCartError.setText(
							"Too many items selected in the table. Please select only one item before attempting to edit an inventory item.");
				} else {
					int row = productsTable.getSelectionIndex();
					selectedProduct = productsPageList.get(row);
					System.out.println("Adding to cart, item in row " + String.valueOf(row) + " (zero indexed)");
					System.out.println("Inventory id " + String.valueOf(selectedProduct.getId()) + " and Brand is "
							+ String.valueOf(productsPageList.get(row).getBrand()) + " and Model is "
							+ String.valueOf(productsPageList.get(row).getModel_number()));
					lblBrandHere.setText(selectedProduct.getBrand());
					lblModelHere.setText(selectedProduct.getModel_number());
					cartCountLbl.setText(Integer.toString(getCartCountFromID(selectedProduct.getId())));
				}
			}
		});

		decreaseBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedProduct != null) {
					Inventory cartReference = getCartObjectFromID(selectedProduct.getId());
					double subtotal = 0;
					double tax = 0;
					double total = 0;
					if (cartReference != null && cartReference.getCount() > 0) {
						cartReference.setCount(cartReference.getCount() - 1);
						cartCountLbl.setText(String.valueOf(cartReference.getCount()));

						for (Inventory inv : cartPageList) {
							subtotal += (inv.getSale_price() * inv.getCount());
							tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
							total += ((inv.getSale_price() * inv.getCount())
									+ ((inv.getSale_price() * inv.getCount()) * 0.075));
						}
						total = Math.round(total * 100.0) / 100.0;
						SubtotalText.setText(Double.toString(subtotal));
						TaxText.setText(Double.toString(tax));
						CartTotalText.setText(Double.toString(total));
						if (cartReference.getCount() == 0) {
							// remove from map and from List

							mapCart.remove(cartReference.getId());
							cartPageList.remove(cartReference);

							for (Inventory inv : cartPageList) {
								subtotal += (inv.getSale_price() * inv.getCount());
								tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
								total += ((inv.getSale_price() * inv.getCount())
										+ ((inv.getSale_price() * inv.getCount()) * 0.075));
							}
						}

					} else {
						System.out.println("cannot decrement. not enought items");
						// TODO put in error field
					}
				}
			}
		});

		increaseBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedProduct != null) {
					Inventory cartReference = getCartObjectFromID(selectedProduct.getId());
					double subtotal = 0;
					double tax = 0;
					double total = 0;
					// were there 0? add to list and map

					if (cartReference == null) {
						if (selectedProduct.getCount() > 0) {	// create map and List indices for this cart item w/ count of 1
							cartReference = new Inventory(selectedProduct.getBrand(), selectedProduct.getModel_number(),
									selectedProduct.getSale_price(), selectedProduct.getPurchase_price(), 1,
									selectedProduct.getSize(), selectedProduct.getWidth(),
									selectedProduct.getAspectratio(), selectedProduct.getDiameter());
							cartPageList.add(cartReference);
							cartReference.setId(selectedProduct.getId());
							mapCart.put(cartReference.getId(), cartReference);
							cartCountLbl.setText(String.valueOf(cartReference.getCount()));
							for (Inventory inv : cartPageList) {
								subtotal += (inv.getSale_price() * inv.getCount());
								tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
								total += ((inv.getSale_price() * inv.getCount())
										+ ((inv.getSale_price() * inv.getCount()) * 0.075));
							}
						}
					} else if (cartReference.getCount() == selectedProduct.getCount()) {
						System.out.println("Cannot increment item " + cartReference.getBrand() + " "
								+ cartReference.getModel_number()
								+ "\tTotal in cart is equal to total in inventory :)");
						// TODO error field
					} else { // not null and not == inv.count. So increment cart
						cartReference.setCount(cartReference.getCount() + 1);
						cartCountLbl.setText(String.valueOf(cartReference.getCount()));
						for (Inventory inv : cartPageList) {
							subtotal += (inv.getSale_price() * inv.getCount());
							tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
							total += ((inv.getSale_price() * inv.getCount())
									+ ((inv.getSale_price() * inv.getCount()) * 0.075));
						}
						total = Math.round(total * 100.0) / 100.0;
						SubtotalText.setText(Double.toString(subtotal));
						TaxText.setText(Double.toString(tax));
						CartTotalText.setText(Double.toString(total));
					}
				}
			}
		});

		// button_1_1.setBounds(508, 36, 24, 23);
		// button_1_1.setText("+");
		// button_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, 0);
		tbtmNewItem_2.setText("Cart");

		Composite CartComposite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(CartComposite);
		CartComposite.setLayout(new GridLayout(1, false));

		Composite CheckoutComp = new Composite(CartComposite, SWT.NONE);
		CheckoutComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		CheckoutComp.setLayout(new GridLayout(3, false));

		txtCart = new Text(CheckoutComp, SWT.BORDER);
		txtCart.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		txtCart.setText("Current Customer:");

		currentCustomerText = new Text(CheckoutComp, SWT.BORDER);
		currentCustomerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		currentCustomerText.setEnabled(false);
		new Label(CheckoutComp, SWT.NONE);
		new Label(CheckoutComp, SWT.NONE);

		Label lblNewLabel_9 = new Label(CheckoutComp, SWT.NONE);
		lblNewLabel_9.setText("Cart:");
		new Label(CheckoutComp, SWT.NONE);
		new Label(CheckoutComp, SWT.NONE);

		cartItemsComp = new Composite(CheckoutComp, SWT.NONE);
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
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE).setText("Brand");
		new Label(cartItemsComp, SWT.NONE).setText("Model");
		new Label(cartItemsComp, SWT.NONE).setText("Size");
		new Label(cartItemsComp, SWT.NONE).setText("Purchase Price");
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(cartItemsComp, SWT.NONE);
		new Label(CheckoutComp, SWT.NONE);
		new Label(CheckoutComp, SWT.NONE);

		Composite composite = new Composite(CheckoutComp, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
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
		btnClearCart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Clear Cart");
				cartPageList.clear();
				SubtotalText.setText("");
				TaxText.setText("");
				CartTotalText.setText("");
			}
		});
		btnClearCart.setText("Clear Cart");

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("Checkout");
		new Label(CheckoutComp, SWT.NONE);
		new Label(CheckoutComp, SWT.NONE);
		new Label(CheckoutComp, SWT.NONE);

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkoutAmount = (long) (Double.parseDouble(CartTotalText.getText()) * 100);
				System.out.println("Amount in cents is " + checkoutAmount);
				SpendMoney.initialize();
				SpendMoney.payWithTerminal(checkoutAmount);
				WorkOrder myWorkOrder = new WorkOrder(CartTotalText.getText(), "pending", activeCustomer.getId(), "", new Timestamp(System.currentTimeMillis()),
						new Timestamp(System.currentTimeMillis()));
				WorkOrderDao.addWorkOrder(myWorkOrder);
				int thisOrderNum = myWorkOrder.getNumber();
				for(Inventory i : cartPageList){
					OrderProductRelationDao.addOrderProductRelation(new OrderProductRelation(thisOrderNum, i.getId(), i.getCount()));
					int oldCount = mapInventory.get(i.getId()).getCount();
					mapInventory.get(i.getId()).setCount(oldCount - i.getCount());
					InventoryDao.updateInventory(mapInventory.get(i.getId()));
				}
				//Txt.sendMessage(activeCustomer.getPhone(), "Thank you for your purchase!");
				//check to database for last millisecond accuracy would be implemented if our presentation was on May 2
				
				
			}
		});
		// TODO remove below line
		// cartPageList = InventoryDao.generateQueryInventory("Michelin", "", "", "", "", true);
		fillCartItemsGridLayout();

		TabItem tbtmNewItem_3 = new TabItem(tabFolder, 0);
		tbtmNewItem_3.setText("Inventory");

		Composite InventoryComposite = new Composite(tabFolder, SWT.NONE);
		// InventoryComposite.setLocation(-48, -329);
		tbtmNewItem_3.setControl(InventoryComposite);
		InventoryComposite.setLayout(new GridLayout(2, false));

		Composite Inv2ButtonComp = new Composite(InventoryComposite, SWT.NONE);
		Inv2ButtonComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		Inv2ButtonComp.setLayout(new GridLayout(2, false));

		Composite SearchMenuComp_1_1 = new Composite(Inv2ButtonComp, SWT.NONE);
		SearchMenuComp_1_1.setLayout(new GridLayout(7, false));

		Label lblBrand_2 = new Label(SearchMenuComp_1_1, SWT.NONE);
		lblBrand_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblBrand_2.setText("Brand");

		Label lblModel_2 = new Label(SearchMenuComp_1_1, SWT.NONE);
		lblModel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblModel_2.setText("Model");

		Label lblTireWidth_1 = new Label(SearchMenuComp_1_1, SWT.NONE);
		lblTireWidth_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblTireWidth_1.setText("Tire Width");

		Label lblNewLabel = new Label(SearchMenuComp_1_1, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Aspect Ratio");

		Label lblDiameter_1 = new Label(SearchMenuComp_1_1, SWT.NONE);
		lblDiameter_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblDiameter_1.setText("Diameter");
		new Label(SearchMenuComp_1_1, SWT.NONE);
		new Label(SearchMenuComp_1_1, SWT.NONE);

		BrandComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		BrandComboInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		// BrandComboInventory.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));

		ModelComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		ModelComboInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		WidthComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		WidthComboInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		RatioComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		RatioComboInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		DiameterComboInventory = new Combo(SearchMenuComp_1_1, SWT.NONE);
		DiameterComboInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		/*
		 * fillBrandCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY brand"), BrandComboInventory); fillModelCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY model_number"),
		 * ModelComboInventory); fillWidthCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY width"), WidthComboInventory);
		 * fillAspectRatioCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY aspectratio"), RatioComboInventory); fillDiameterCombo(InventoryDao.getInventory("SELECT * FROM inventory ORDER BY diameter"),
		 * DiameterComboInventory);
		 */
		Button ProdBtnSearchInventory = new Button(SearchMenuComp_1_1, SWT.NONE);
		ProdBtnSearchInventory.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		ProdBtnSearchInventory.setText("Search");

		btnInventoryBroadSearch = new Button(SearchMenuComp_1_1, SWT.CHECK);
		btnInventoryBroadSearch.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnInventoryBroadSearch.setText("Enable Broad Search");
		ProdBtnSearchInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableInv.clearAll();
				fillTableProductsExtensive(tableInv, InventoryDao.generateQueryInventory(BrandComboInventory.getText(),
						ModelComboInventory.getText(), WidthComboInventory.getText(), RatioComboInventory.getText(),
						DiameterComboInventory.getText(), btnInventoryBroadSearch.getSelection()));
			}
		});

		new Label(Inv2ButtonComp, SWT.NONE);

		Composite SearchResultsComp_1 = new Composite(Inv2ButtonComp, SWT.NONE);
		SearchResultsComp_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		SearchResultsComp_1.setVisible(true);
		SearchResultsComp_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		tableInv = new Table(SearchResultsComp_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		tableInv.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		tableInv.setHeaderVisible(true);
		tableInv.setLinesVisible(true);

		createAndNameColumns(tableInv, allProductColumns);
		new Label(Inv2ButtonComp, SWT.NONE);

		Button btnAddInv = new Button(Inv2ButtonComp, SWT.NONE);
		btnAddInv.setText("Add Inventory");

		Button btnEditInventory = new Button(Inv2ButtonComp, SWT.NONE);
		btnEditInventory.setText("Edit Inventory");

		Composite composite_6 = new Composite(InventoryComposite, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		StackLayout inventorySideLayout = new StackLayout();
		composite_6.setLayout(inventorySideLayout);

		Composite EditInvComp = new Composite(composite_6, SWT.NONE);
		EditInvComp.setVisible(true);
		EditInvComp.setLayout(new GridLayout(4, true));
		new Label(EditInvComp, SWT.NONE);

		Label lblAddInventory_1 = new Label(EditInvComp, SWT.NONE);

		lblAddInventory_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblAddInventory_1.setText("Edit Inventory");
		Label label_2 = new Label(EditInvComp, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		Label lblEditInvBrand = new Label(EditInvComp, SWT.NONE);
		lblEditInvBrand.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvBrand.setText("Brand:");

		editInvBrand = new Text(EditInvComp, SWT.BORDER);
		editInvBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditInvModel = new Label(EditInvComp, SWT.NONE);
		lblEditInvModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvModel.setText("Model:");

		editInvModel = new Text(EditInvComp, SWT.BORDER);
		editInvModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditInvSize = new Label(EditInvComp, SWT.NONE);
		lblEditInvSize.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvSize.setText("Size:");

		editInvSize = new Text(EditInvComp, SWT.BORDER);
		editInvSize.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditInvQuantity = new Label(EditInvComp, SWT.NONE);
		lblEditInvQuantity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvQuantity.setText("Quantity:");

		editInvQuantity = new Text(EditInvComp, SWT.BORDER);
		GridData gd_editInvQuantity = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_editInvQuantity.widthHint = 325;
		editInvQuantity.setLayoutData(gd_editInvQuantity);

		Label lblEditInvPurchasePrice = new Label(EditInvComp, SWT.NONE);
		lblEditInvPurchasePrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvPurchasePrice.setText("Purchase Price:");

		editInvPurchasePrice = new Text(EditInvComp, SWT.BORDER);
		editInvPurchasePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblEditInvSalePrice = new Label(EditInvComp, SWT.NONE);
		lblEditInvSalePrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEditInvSalePrice.setText("Sale Price:");

		editInvSalePrice = new Text(EditInvComp, SWT.BORDER);
		editInvSalePrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(EditInvComp, SWT.NONE);

		editInvError = new Text(EditInvComp, SWT.BORDER);
		editInvError.setEditable(false);
		editInvError.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(EditInvComp, SWT.NONE);

		Button btnClearEditInv = new Button(EditInvComp, SWT.NONE);
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

		Button btnEditInvSubmit = new Button(EditInvComp, SWT.NONE);

		btnEditInvSubmit.setText("Submit");
		new Label(EditInvComp, SWT.NONE);

		Composite AddInvComp = new Composite(composite_6, SWT.NONE);
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
		composite_2.setLayout(new GridLayout(3, false));

		tablePending = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		tablePending.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tablePending.setHeaderVisible(true);
		tablePending.setLinesVisible(true);

		TabItem tbtmCompleted = new TabItem(tabFolder_1, SWT.NONE);
		tbtmCompleted.setText("Completed");

		Composite composite_3 = new Composite(tabFolder_1, SWT.NONE);
		tbtmCompleted.setControl(composite_3);
		composite_3.setLayout(new GridLayout(2, false));

		tableCompleted = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		tableCompleted.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableCompleted.setHeaderVisible(true);
		tableCompleted.setLinesVisible(true);

		createAndNameColumns(tablePending, workOrderColumns);

		Button btnViewPendingOrderDetail = new Button(composite_2, SWT.NONE);

		btnViewPendingOrderDetail.setText("View selected Order Details");
		new Label(composite_2, SWT.NONE);
		createAndNameColumns(tableCompleted, workOrderColumns);

		Button btnViewCompletedOrderDetail = new Button(composite_3, SWT.NONE);
		btnViewCompletedOrderDetail.setText("View selected Order Details");

		Composite composite_4 = new Composite(WorkOrderComposite, SWT.NONE);
		composite_4.setLayout(new GridLayout(3, false));
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblCustomerName = new Label(composite_4, SWT.NONE);
		lblCustomerName.setText("Customer Name:");

		Label customerNameValue = new Label(composite_4, SWT.NONE);
		customerNameValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		customerNameValue.setText("name");

		Button btnSwitchStatus = new Button(composite_4, SWT.NONE);

		btnSwitchStatus.setText("Change Status (Pending / Completed)");

		Label lblCustomerPhone = new Label(composite_4, SWT.NONE);
		lblCustomerPhone.setText("Customer Phone:");

		Label customerPhoneValue = new Label(composite_4, SWT.NONE);
		customerPhoneValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		customerPhoneValue.setText("phone #");
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);

		orderDetailsTable = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		orderDetailsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		orderDetailsTable.setHeaderVisible(true);
		orderDetailsTable.setLinesVisible(true);

		Label statusLabel = new Label(composite_4, SWT.NONE);
		statusLabel.setAlignment(SWT.CENTER);
		statusLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		statusLabel.setText("Ipsum dolor sit amet consectutor");
		statusLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblOrderNotes = new Label(composite_4, SWT.NONE);
		lblOrderNotes.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOrderNotes.setText("Order Notes: ");

		textOrderNote = new Text(composite_4, SWT.BORDER);
		textOrderNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		createAndNameColumns(orderDetailsTable, ordProdColumns);
		// TEST
		fillOrderDetailsTable(orderDetailsTable, 2);
		Button btnSaveNote = new Button(composite_4, SWT.NONE);

		btnSaveNote.setText("Save Notes");

		inventorySideLayout.topControl = EditInvComp;

		btnAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Add Inventory");
				switchStackLayoutToShowArgument(AddInvComp);
			}
		});

		btnEditInventory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Edit Inventory");
				// populate
				if (tableInv.getSelectionCount() < 1) {
					System.out.println("no customer selected");
					custCompButtonsErrorField
							.setText("You must select an item from the table before attempting to edit that item.");
				} else if (tableInv.getSelectionCount() > 1) {
					System.out.println("too many items selected");
					custCompButtonsErrorField.setText(
							"Too many items selected in the table. Please select only one item before attempting to edit.");
				} else {
					int row = tableInv.getSelectionIndex();
					invPageSelected = invPageList.get(row);
					/*
					 * // System.out.println("Editing customer in row " + String.valueOf(row) + " (zero indexed)"); // System.out.println("customer id " + String.valueOf(invPageSelecteed.getId()) + " and Name is " +
					 * String.valueOf(customerPageList.get(row).getName()));
					 */
					editInvBrand.setText(invPageSelected.getBrand());
					editInvModel.setText(invPageSelected.getModel_number());
					editInvSize.setText(invPageSelected.getSize());
					editInvQuantity.setText(String.valueOf(invPageSelected.getCount()));
					editInvPurchasePrice.setText(String.valueOf(invPageSelected.getPurchase_price()));
					editInvSalePrice.setText(String.valueOf(invPageSelected.getSale_price()));
					// TODO populate
					switchStackLayoutToShowArgument(EditCustComp);
				}
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

		btnEditInvSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Edit Inventory");
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				int row = tableInv.getSelectionIndex();
				Inventory inv = invPageList.get(row);
				inv.setBrand(editInvBrand.getText());
				inv.setModel_number(editInvModel.getText());
				inv.setSize(editInvSize.getText());
				String msg = "";
				try {
					int intQuantity = Integer.parseInt(editInvQuantity.getText());
					inv.setCount(intQuantity);
				} catch (NumberFormatException nfe) {
					msg += "Quantity must be an integer. ";
				}
				try {
					Double purchasePrice = Double.parseDouble(editInvPurchasePrice.getText());
					inv.setPurchase_price(purchasePrice);
				} catch (NumberFormatException nfe) {
					msg += "Purchase price must be a double. ";
				}
				try {
					Double salePrice = Double.parseDouble(editInvSalePrice.getText());
					inv.setSale_price(salePrice);
				} catch (NumberFormatException nfe) {
					msg += "Sale price must be a double. ";
				}
				if (Validation.inventoryIsValid(inv, editInvError, msg)) {
					int width = Integer.parseInt(editInvSize.getText().substring(0, 3));
					inv.setWidth(width);
					int aspectRatio = Integer.parseInt(editInvSize.getText().substring(4, 6));
					inv.setAspectratio(aspectRatio);
					int diameter = Integer.parseInt(editInvSize.getText().substring(7, 9));
					inv.setDiameter(diameter);
					session.update(inv);
					session.getTransaction().commit();
				} else {
					session.close();
				}
			}
		});

		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tabFolder.getSelection()[0].getText().equals("Cart")) {
					fillCartItemsGridLayout();
					if (activeCustomer != null) {
						currentCustomerText.setText(activeCustomer.getName());
					}
					if(cartPageList != null){
					double subtotal = 0;
					double tax = 0;
					double total = 0;
					for (Inventory inv : cartPageList) {
						subtotal += (inv.getSale_price() * inv.getCount());
						tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
						total += ((inv.getSale_price() * inv.getCount())
								+ ((inv.getSale_price() * inv.getCount()) * 0.075));
					}
					total = Math.round(total * 100.0) / 100.0;
					SubtotalText.setText(Double.toString(subtotal));
					TaxText.setText(Double.toString(tax));
					CartTotalText.setText(Double.toString(total));
				}
				}
				if (tabFolder.getSelection()[0].getText().equals("Work Orders")) {
					updateWorkOrderTables();
				}
			}
		});

		btnViewPendingOrderDetail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// populate the details section based on the pending table row selected (using List thing)
				activeDetails = pendingOrders.get(tablePending.getSelectionIndex());
				fillOrderDetailsTable(orderDetailsTable, activeDetails.getOrdNumber());
				customerNameValue.setText(activeDetails.getCust_name());
				customerPhoneValue.setText(activeDetails.getCust_phone());
				if (activeDetails.getNote() == null) {
					textOrderNote.setText("");
				} else {
					textOrderNote.setText(activeDetails.getNote());
				}
				statusLabel.setText(activeDetails.getStatus());
			}
		});

		btnViewCompletedOrderDetail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// populate the details section based on the completed table row selected (using List thing)
				activeDetails = completedOrders.get(tableCompleted.getSelectionIndex());
				fillOrderDetailsTable(orderDetailsTable, activeDetails.getOrdNumber());
				customerNameValue.setText(activeDetails.getCust_name());
				customerPhoneValue.setText(activeDetails.getCust_phone());
				textOrderNote.setText(activeDetails.getNote());
				statusLabel.setText(activeDetails.getStatus());
			}
		});

		btnSaveNote.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WorkOrder myOrder = WorkOrderDao.getOrder(activeDetails.getOrdNumber());
				myOrder.setNote(textOrderNote.getText());
				WorkOrderDao.updateOrder(myOrder);
			}
		});

		btnSwitchStatus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (activeDetails.getStatus().equals("completed")) {
					WorkOrder myOrder = WorkOrderDao.getOrder(activeDetails.getOrdNumber());
					myOrder.setStatus("pending");
					WorkOrderDao.updateOrder(myOrder);
					statusLabel.setText("Pending...");
				} else {
					WorkOrder myOrder = WorkOrderDao.getOrder(activeDetails.getOrdNumber());
					myOrder.setStatus("completed");
					WorkOrderDao.updateOrder(myOrder);
					statusLabel.setText("Completed!");
					//Txt.sendMessage(activeDetails.getCust_phone(), "Your order is ready :)");
				}
			}
		});

	}

	public void switchStackLayoutToShowArgument(Composite showThis) {
		((StackLayout) showThis.getParent().getLayout()).topControl = showThis;
		showThis.getParent().layout();
	}

	public void createAndNameColumns(Table table, String[] titles) {
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("column  " + column.getText());
					if (table == customerTable) {
						System.out.println("Sorting Customer table by " + column.getText());
						sortCustomerTable(column.getText());
					} else if (table == productsTable) {
						System.out.println("Sorting products table by " + column.getText());
						sortProductsTable(column.getText());
					} else if (table == tableInv) {
						System.out.println("Sorting Inventory table by " + column.getText());
						sortInventoryTable(column.getText());
					}
					/*
					 * else if(table == tablePending) { System.out.println("Sorting pending orders table by " + column.getText()); sortOrderPendingTable(column.getText()); }else if(table == tableCompleted) {
					 * System.out.println("Sorting completed orders table by " + column.getText()); sortOrderCompletedTable(column.getText()); }else if(table == orderDetailsTable) {
					 * System.out.println("Sorting order details table by " + column.getText()); sortOrderDetailsTable(column.getText()); }
					 */
					// sortTable(table, column.getText());
				}
			});

			// TODO: make table columns resize their width automatically instead of being 100 px wide :)
			column.setWidth(125);
		}
	}

	private void sortCustomerTable(String columnHeader) {
		String orderBy = " ORDER BY ";
		switch (columnHeader) {
		case "Name":
			orderBy += "name";
			break;
		case "Phone":
			orderBy += "phone";
			break;
		case "Address":
			orderBy += "address";
			break;
		case "Email":
			orderBy += "email";
			break;
		default:
			System.out.println("There has been an error in the column name switch");
			return;
		}
		customerPageList = CustomerDao.generateQueryCustomer(searchName.getText(), searchPhone.getText(),
				searchAddress.getText(), searchEmail.getText(), customerBroadSearch.getSelection(), orderBy);
		fillTableCustomer(customerTable, customerPageList);
	}

	private String prodInvSwitch(String columnHeader) {
		String orderBy = " ORDER BY ";
		switch (columnHeader) {
		case "Brand":
			orderBy += "brand";
			break;
		case "Model Number":
		case "Model":
			orderBy += "model_number";
			break;
		case "Size":
			orderBy += "size";
			break;
		case "Count":
		case "Quantity":
			orderBy += "count";
			break;
		case "Sale Price":
			orderBy += "sale_price";
			break;
		case "ID":
			orderBy += "id";
			break;
		case "Purchase Price":
			orderBy += "purchase_price";
			break;
		case "Width":
			orderBy += "width";
			break;
		case "Aspect Ratio":
			orderBy += "aspectratio";
			break;
		case "Diameter":
			orderBy += "diameter";
			break;
		default:
			System.out.println("There has been an error in the column name switch for " + columnHeader);
			return " AHHHHHHHHHHHHHHH";
		}
		return orderBy;
	}

	private void sortProductsTable(String columnHeader) {
		String orderBy = prodInvSwitch(columnHeader);
		productsTable.clearAll();
		productsPageList = InventoryDao.generateQueryInventory(BrandCombo.getText(), ModelCombo.getText(),
				WidthCombo.getText(), RatioCombo.getText(), DiameterCombo.getText(),
				btnProductsBroadSearch.getSelection(), orderBy);
		fillTableProductsSimple(productsTable, productsPageList);
	}

	private void sortInventoryTable(String columnHeader) {
		String orderBy = prodInvSwitch(columnHeader);
		tableInv.clearAll();
		invPageList = InventoryDao.generateQueryInventory(BrandComboInventory.getText(), ModelComboInventory.getText(),
				WidthComboInventory.getText(), RatioComboInventory.getText(), DiameterComboInventory.getText(),
				btnInventoryBroadSearch.getSelection(), orderBy);
		fillTableProductsExtensive(tableInv, invPageList);
	}

	/*
	 * private void sortOrderPendingTable(String columnHeader) { String orderBy = " ORDER BY "; switch(columnHeader) { case "Name": orderBy += "name"; break; case "Phone": orderBy += "phone"; break; case "Address":
	 * orderBy += "address"; break; case "Email": orderBy += "email"; break; default: System.out.println("There has been an error in the column name switch"); return; } } private void sortOrderCompletedTable(String
	 * columnHeader) { String orderBy = " ORDER BY "; switch(columnHeader) { case "Name": orderBy += "name"; break; case "Phone": orderBy += "phone"; break; case "Address": orderBy += "address"; break; case
	 * "Email": orderBy += "email"; break; default: System.out.println("There has been an error in the column name switch"); return; } } private void sortOrderDetailsTable(String columnHeader) { String orderBy =
	 * " ORDER BY "; switch(columnHeader) { case "Name": orderBy += "name"; break; case "Phone": orderBy += "phone"; break; case "Address": orderBy += "address"; break; case "Email": orderBy += "email"; break;
	 * default: System.out.println("There has been an error in the column name switch"); return; } }
	 */
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

	private void updateWorkOrderTables() {
		pendingOrders = OrdCustDao.getPendingOrdCust();
		completedOrders = OrdCustDao.getCompletedOrdCust();
		fillOrderTable(tablePending, pendingOrders);
		fillOrderTable(tableCompleted, completedOrders);
	}

	public void fillOrderTable(Table table, List<OrdCust> myOrders) {
		table.removeAll();
		for (OrdCust ord : myOrders) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { String.valueOf(ord.getOrdNumber()), String.valueOf(ord.getCust_name()),
					ord.getTime_create().toString(), ord.getTime_update_status().toString(), ord.getNote() });
		}
	}

	public void fillOrderDetailsTable(Table table, int orderNum) {
		List<OrderProductDetails> details = OrderProductDetailsDao.getDetailsFromOrderNum(orderNum);
		table.removeAll();
		System.out.println(details);
		for (OrderProductDetails detail : details) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { detail.getBrand(), detail.getModel_number(), detail.getSize(),
					String.valueOf(detail.getCount()) });
		}
	}

	public void fillCombo(List<String> dropdownStrings, Combo combo, String comboName) {
		combo.add(comboName);
		for (String option : dropdownStrings) {
			combo.add(option);
		}
	}

	public void fillProductCombos() {
		fillCombo(brandList = InventoryDao.comboQuery("brand"), BrandCombo, "");
		fillCombo(modelList = InventoryDao.comboQuery("model_number"), ModelCombo, "");
		fillCombo(widthList = InventoryDao.comboQuery("width"), WidthCombo, "");
		fillCombo(ratioList = InventoryDao.comboQuery("aspectratio"), RatioCombo, "");
		fillCombo(diameterList = InventoryDao.comboQuery("diameter"), DiameterCombo, "");
	}

	public void fillInventoryCombos() {
		fillCombo(brandList = InventoryDao.comboQuery("brand"), BrandComboInventory, "");
		fillCombo(modelList = InventoryDao.comboQuery("model_number"), ModelComboInventory, "");
		fillCombo(widthList = InventoryDao.comboQuery("width"), WidthComboInventory, "");
		fillCombo(ratioList = InventoryDao.comboQuery("aspectratio"), RatioComboInventory, "");
		fillCombo(diameterList = InventoryDao.comboQuery("diameter"), DiameterComboInventory, "");
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
		for (Inventory inv : cartPageList) {
			// x button
			Button btnXSearchAccount_1 = new Button(cartItemsComp, SWT.NONE);
			btnXSearchAccount_1.setText("X");
			btnXSearchAccount_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO confirmation dialogue
					cartPageList.remove(inv);
					double subtotal = 0;
					double tax = 0;
					double total = 0;
					for (Inventory inv : cartPageList) {
						subtotal += (inv.getSale_price() * inv.getCount());
						tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
						total += ((inv.getSale_price() * inv.getCount())
								+ ((inv.getSale_price() * inv.getCount()) * 0.075));
					}
					total = Math.round(total * 100.0) / 100.0;
					SubtotalText.setText(Double.toString(subtotal));
					TaxText.setText(Double.toString(tax));
					CartTotalText.setText(Double.toString(total));
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
					if (inv.getCount() > 0) {
						inv.setCount(inv.getCount() - 1);
						QuantityText.setText(String.valueOf(inv.getCount()));
						ItemTotalCost.setText(String.valueOf(inv.getCount() * inv.getSale_price()));
						double subtotal = 0;
						double tax = 0;
						double total = 0;
						for (Inventory inv : cartPageList) {
							subtotal += (inv.getSale_price() * inv.getCount());
							tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
							total += ((inv.getSale_price() * inv.getCount())
									+ ((inv.getSale_price() * inv.getCount()) * 0.075));
						}
						total = Math.round(total * 100.0) / 100.0;
						SubtotalText.setText(Double.toString(subtotal));
						TaxText.setText(Double.toString(tax));
						CartTotalText.setText(Double.toString(total));

					} else {
						System.out.println("cannot decrement. not enought items");
						// TODO put in error field
					}
				}
			});
			cartItemsPlus.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (inv.getCount() < getInventoryCountFromID(inv.getId())) {
						inv.setCount(inv.getCount() + 1);
						QuantityText.setText(String.valueOf(inv.getCount()));
						ItemTotalCost.setText(String.valueOf(inv.getCount() * inv.getSale_price()));
						double subtotal = 0;
						double tax = 0;
						double total = 0;
						for (Inventory inv : cartPageList) {
							subtotal += (inv.getSale_price() * inv.getCount());
							tax += ((inv.getSale_price() * inv.getCount()) * 0.075);
							total += ((inv.getSale_price() * inv.getCount())
									+ ((inv.getSale_price() * inv.getCount()) * 0.075));
						}
						total = Math.round(total * 100.0) / 100.0;
						SubtotalText.setText(Double.toString(subtotal));
						TaxText.setText(Double.toString(tax));
						CartTotalText.setText(Double.toString(total));
					} else {
						System.out.println("cannot increment. we're up against the inventory limit");
						// TODO put in error field
					}
				}
			});
		} // end for loop
		cartItemsComp.layout();
	}

	private int getCartCountFromID(int id) {
		if (mapCart.get(id) == null) {
			return 0;
		}
		return mapCart.get(id).getCount();
	}

	private Inventory getCartObjectFromID(int id) {
		return mapCart.get(id);
	}

	private int getInventoryCountFromID(int id) {
		if (mapInventory.get(id) == null) {
			return 0;
		}
		return mapInventory.get(id).getCount();
	}

	public void getOrderProdFromListVar() {

	}

	public void getAllOrdersProdByQuery() {	// and store in the global variable

	}

	public void getOrderProdByQuery(int orderID) {

	}

	private void updateAllInventory() {
		allInventory = InventoryDao.getAllInventory();
		mapInventory = new HashMap<>();
		for (Inventory inv : allInventory) {
			mapInventory.put(inv.getId(), inv);
		}
	}
}