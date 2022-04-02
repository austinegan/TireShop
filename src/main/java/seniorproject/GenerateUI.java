package seniorproject;

import java.util.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import javax.swing.JLabel;

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
//import net.proteanit.sql.DbUtils;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eclipse.swt.widgets.TableColumn;

public class GenerateUI {

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
	private Text txtInvSearchBar;
	private Table tableInv;
	private Text searchAddInvBrand;
	private Text searchAddInvBrandError;
	private Text searchAddInvModel;
	private Text searchAddInvModelError;
	private Text searchAddInvSize;
	private Text searchAddInvSizeError;
	private Text searchAddInvQuantity;
	private Text searchAddInvQuantityError;
	private Text searchAddInvPrice;
	private Text searchAddInvPriceError;
	private Text searchRmvInvBrand;
	private Text searchRmvInvBrandError;
	private Text searchRmvInvModel;
	private Text searchRmvInvModelError;
	private Text searchRmvInvSize;
	private Text searchRmvInvSizeError;
	private Text text1_1;
	private Text text1_2;
	private Text text1_3;
	private Text text1_4;
	private Text text1_5;
	private Text text_12;
	private Text text1;
	private Text text2;
	private Text text3;
	private Text text4;
	private Text text5;
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

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	
	
	public static void main(String[] args) {
		
		try {
			GenerateUI window = new GenerateUI();
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
		fd_SearchAcctComp.bottom = new FormAttachment(100, -28);
		fd_SearchAcctComp.left = new FormAttachment(0, 50);
		SearchAcctComp.setLayoutData(fd_SearchAcctComp);
		SearchAcctComp.setLayout(new GridLayout(3, false));

		Composite NewAcctComp = new Composite(CustomerComposite, SWT.NONE);
		fd_SearchAcctComp.right = new FormAttachment(NewAcctComp, -29);
		NewAcctComp.setLayout(new GridLayout(4, false));
		FormData fd_NewAcctComp = new FormData();
		fd_NewAcctComp.top = new FormAttachment(0, 267);
		fd_NewAcctComp.bottom = new FormAttachment(100, -46);
		fd_NewAcctComp.right = new FormAttachment(100, -47);
		fd_NewAcctComp.left = new FormAttachment(0, 496);
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
		fd_SearchAcctComp.top = new FormAttachment(Cust2ButtonComp, 53);
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
		
		Label lblTestingThatI = new Label(CustomerComposite, SWT.NONE);
		FormData fd_lblTestingThatI = new FormData();
		fd_lblTestingThatI.top = new FormAttachment(0, 91);
		fd_lblTestingThatI.left = new FormAttachment(Cust2ButtonComp, 152);
		lblTestingThatI.setLayoutData(fd_lblTestingThatI);
		lblTestingThatI.setText("Testing that I can still add stuff #1");
		
		Label lblTestingThatI_1 = new Label(CustomerComposite, SWT.NONE);
		FormData fd_lblTestingThatI_1 = new FormData();
		fd_lblTestingThatI_1.top = new FormAttachment(lblTestingThatI, 61);
		fd_lblTestingThatI_1.left = new FormAttachment(Cust2ButtonComp, 196);
		lblTestingThatI_1.setLayoutData(fd_lblTestingThatI_1);
		lblTestingThatI_1.setText("Testing that I can still add stuff #2");

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, 0);
		tbtmNewItem_1.setText("Products");
		
		Composite ProductsComposite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(ProductsComposite_1);
		
		Composite SearchMenuComp_1 = new Composite(ProductsComposite_1, SWT.NONE);
		SearchMenuComp_1.setBounds(0, 10, 473, 23);
		
		Combo BrandCombo_1 = new Combo(SearchMenuComp_1, SWT.NONE);
		BrandCombo_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		BrandCombo_1.setBounds(0, 0, 94, 23);
		BrandCombo_1.setText("            Brand");
		
		Combo WidthCombo_1 = new Combo(SearchMenuComp_1, SWT.NONE);
		WidthCombo_1.setBounds(93, 0, 87, 23);
		WidthCombo_1.setText(" Tire Width");
		
		Combo RatioCombo_1 = new Combo(SearchMenuComp_1, SWT.NONE);
		RatioCombo_1.setBounds(180, 0, 94, 23);
		RatioCombo_1.setText(" Aspect Ratio");

		
		Combo DiameterCombo_1 = new Combo(SearchMenuComp_1, SWT.NONE);
		DiameterCombo_1.setBounds(274, 0, 87, 23);
		DiameterCombo_1.setText(" Diameter");
		
		
		Button ProdBtnSearch_1 = new Button(SearchMenuComp_1, SWT.NONE);
		ProdBtnSearch_1.setText("Search");
		ProdBtnSearch_1.setBounds(388, 0, 75, 25);
		
		Composite SearchResultsComp = new Composite(ProductsComposite_1, SWT.NONE);
		SearchResultsComp.setBounds(0, 54, 838, 536);
		SearchResultsComp.setVisible(false);
		
		productsTable = new Table(SearchResultsComp, SWT.BORDER | SWT.FULL_SELECTION);
		productsTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		productsTable.setBounds(0, 0, 681, 403);
		productsTable.setHeaderVisible(true);
		productsTable.setLinesVisible(true);
		
		
		
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
		
		Composite RmvInvComp = new Composite(InventoryComposite, SWT.NONE);
		RmvInvComp.setBounds(436, 290, 328, 219);
		
		Label lblRmvInv = new Label(RmvInvComp, SWT.NONE);
		lblRmvInv.setBounds(113, 20, 102, 16);
		lblRmvInv.setText("Remove Inventory");
		
		Label lblRmvInvBrand = new Label(RmvInvComp, SWT.NONE);
		lblRmvInvBrand.setBounds(14, 51, 56, 16);
		lblRmvInvBrand.setText("Brand:");
		
		Label lblRmvInvModel = new Label(RmvInvComp, SWT.NONE);
		lblRmvInvModel.setBounds(10, 95, 56, 16);
		lblRmvInvModel.setText("Model:");
		
		Label lblRmvInvSize = new Label(RmvInvComp, SWT.NONE);
		lblRmvInvSize.setBounds(22, 138, 56, 16);
		lblRmvInvSize.setText("Size:");
		
		searchRmvInvBrand = new Text(RmvInvComp, SWT.BORDER);
		searchRmvInvBrand.setBounds(58, 51, 247, 19);
		
		searchRmvInvBrandError = new Text(RmvInvComp, SWT.BORDER);
		searchRmvInvBrandError.setBounds(58, 73, 247, 19);
		
		searchRmvInvModel = new Text(RmvInvComp, SWT.BORDER);
		searchRmvInvModel.setBounds(58, 95, 247, 19);
		
		searchRmvInvModelError = new Text(RmvInvComp, SWT.BORDER);
		searchRmvInvModelError.setBounds(58, 117, 247, 19);
		
		searchRmvInvSize = new Text(RmvInvComp, SWT.BORDER);
		searchRmvInvSize.setBounds(58, 139, 247, 19);
		
		searchRmvInvSizeError = new Text(RmvInvComp, SWT.BORDER);
		searchRmvInvSizeError.setBounds(58, 161, 247, 19);
		
		Button btnRmvInvSubmit = new Button(RmvInvComp, SWT.NONE);
		btnRmvInvSubmit.setBounds(68, 186, 70, 21);
		btnRmvInvSubmit.setText("Submit");
		
		Button btnRmvInvClear = new Button(RmvInvComp, SWT.NONE);
		btnRmvInvClear.setBounds(225, 186, 70, 21);
		btnRmvInvClear.setText("Clear");
		
		Button btnXRmvInv = new Button(RmvInvComp, SWT.NONE);
		btnXRmvInv.setBounds(297, 10, 21, 21);
		btnXRmvInv.setText("X");
		
		Composite Inv2ButtonComp = new Composite(InventoryComposite, SWT.NONE);
		Inv2ButtonComp.setBounds(24, 10, 328, 265);
		
		Button btnAddInv = new Button(Inv2ButtonComp, SWT.NONE);
		btnAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAddInv.setBounds(31, 24, 93, 51);
		btnAddInv.setText("Add Inventory");
		
		Button btnRmvInv = new Button(Inv2ButtonComp, SWT.NONE);
		btnRmvInv.setBounds(181, 24, 110, 51);
		btnRmvInv.setText("Remove Inventory");
		
		txtInvSearchBar = new Text(Inv2ButtonComp, SWT.BORDER);
		txtInvSearchBar.setText("Type tire brand, model, or size to search");
		txtInvSearchBar.setBounds(10, 98, 249, 19);
		
		Button btnInvSearch = new Button(Inv2ButtonComp, SWT.NONE);
		btnInvSearch.setBounds(265, 98, 53, 20);
		btnInvSearch.setText("Search");
		
		tableInv = new Table(Inv2ButtonComp, SWT.BORDER | SWT.FULL_SELECTION);
		tableInv.setBounds(36, 142, 255, 87);
		tableInv.setHeaderVisible(true);
		tableInv.setLinesVisible(true);
		
		Composite AddInvComp = new Composite(InventoryComposite, SWT.NONE);
		AddInvComp.setBounds(10, 290, 328, 308);
		
		Label lblAddInventory = new Label(AddInvComp, SWT.NONE);
		lblAddInventory.setBounds(124, 20, 82, 16);
		lblAddInventory.setText("Add Inventory");
		
		Label lblAddInvBrand = new Label(AddInvComp, SWT.NONE);
		lblAddInvBrand.setBounds(24, 51, 56, 16);
		lblAddInvBrand.setText("Brand:");
		
		Label lblAddInvModel = new Label(AddInvComp, SWT.NONE);
		lblAddInvModel.setBounds(20, 96, 56, 16);
		lblAddInvModel.setText("Model:");
		
		Label lblAddInvSize = new Label(AddInvComp, SWT.NONE);
		lblAddInvSize.setBounds(34, 140, 56, 16);
		lblAddInvSize.setText("Size:");
		
		Label lblAddInvQuantity = new Label(AddInvComp, SWT.NONE);
		lblAddInvQuantity.setBounds(10, 184, 56, 16);
		lblAddInvQuantity.setText("Quantity:");
		
		Label lblAddInvPrice = new Label(AddInvComp, SWT.NONE);
		lblAddInvPrice.setBounds(32, 227, 56, 16);
		lblAddInvPrice.setText("Price:");
		
		searchAddInvBrand = new Text(AddInvComp, SWT.BORDER);
		searchAddInvBrand.setBounds(66, 51, 240, 19);
		
		searchAddInvBrandError = new Text(AddInvComp, SWT.BORDER);
		searchAddInvBrandError.setBounds(66, 73, 240, 19);
		
		searchAddInvModel = new Text(AddInvComp, SWT.BORDER);
		searchAddInvModel.setBounds(66, 95, 240, 19);
		
		searchAddInvModelError = new Text(AddInvComp, SWT.BORDER);
		searchAddInvModelError.setBounds(66, 117, 240, 19);
		
		searchAddInvSize = new Text(AddInvComp, SWT.BORDER);
		searchAddInvSize.setBounds(66, 139, 240, 19);
		
		searchAddInvSizeError = new Text(AddInvComp, SWT.BORDER);
		searchAddInvSizeError.setBounds(66, 161, 240, 19);
		
		searchAddInvQuantity = new Text(AddInvComp, SWT.BORDER);
		searchAddInvQuantity.setBounds(66, 183, 240, 19);
		
		searchAddInvQuantityError = new Text(AddInvComp, SWT.BORDER);
		searchAddInvQuantityError.setBounds(66, 205, 240, 19);
		
		searchAddInvPrice = new Text(AddInvComp, SWT.BORDER);
		searchAddInvPrice.setBounds(66, 227, 240, 19);
		
		searchAddInvPriceError = new Text(AddInvComp, SWT.BORDER);
		searchAddInvPriceError.setBounds(66, 249, 240, 19);
		
		Button btnAddInvSubmit = new Button(AddInvComp, SWT.NONE);
		btnAddInvSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAddInvSubmit.setBounds(76, 277, 70, 21);
		btnAddInvSubmit.setText("Submit");
		
		Button btnClearAddInv = new Button(AddInvComp, SWT.NONE);
		btnClearAddInv.setBounds(226, 277, 70, 21);
		btnClearAddInv.setText("Clear");
		
		Button btnXAddInv = new Button(AddInvComp, SWT.NONE);
		btnXAddInv.setBounds(297, 10, 21, 21);
		btnXAddInv.setText("X");

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

		btnNewAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : New Account");
				NewAcctComp.setVisible(true);
				Cust2ButtonComp.setEnabled(false);
			}
		});

		
		btnXSearchAccount_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Close Cart Item");
				TireDescriptionText.setText(" ");
				ItemTotalCost.setText(" ");
				QuantityText.setText(" ");
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
		
		btnAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Add Inventory");
				AddInvComp.setVisible(true);
				Inv2ButtonComp.setEnabled(false);
			}
		});
		
		btnRmvInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Remove Inventory");
				RmvInvComp.setVisible(true);
				Cust2ButtonComp.setEnabled(false);
			}
		});
		
//		btnInvSearch.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("Button : Search Inventory");
//				try {
//					Connection connection = null;
//					database.connect(connection);
//					String query = "SELECT id, brand, model_number, sale_price, count FROM Inventory";
//					PreparedStatement pst = connection.prepareStatement(query);
//					ResultSet rs = pst.executeQuery();
//					tableInv.setModel(DbUtils.resultSetToTableModel(rs));
//					database.disconnect(connection);
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				} finally {	
//				}
//			}
//		});
		
		
//		
//		ProdBtnSearch_1.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				SearchResultsComp.setVisible(true);
//
//				int m =38;
//				for(int i=1;i<5;i++){
//					text[i][i]=new Text(SearchResultsComp, SWT.BORDER);
//					text[i][i].setBounds(10, m, 93, 21);
//					text[i][i+1]=new Text(SearchResultsComp, SWT.BORDER);
//					text[i][i+1].setBounds(99, m, 103, 21);
//					text[i][i+2]=new Text(SearchResultsComp, SWT.BORDER);
//					text[i][i+2].setBounds(198, m, 103, 21);
//					//text[i][i+2].setText("Im here");
//					text[i][i+3]=new Text(SearchResultsComp, SWT.BORDER);
//					text[i][i+3].setBounds(301, m, 69, 21);
//					text[i][i+4]=new Text(SearchResultsComp, SWT.BORDER);
//					text[i][i+4].setBounds(369, m, 69, 21);
//					text[i][i+5] = new Text(SearchResultsComp, SWT.BORDER);
//					text[i][i+5].setBounds(482, m, 20, 21);
//					button[i][i] = new Button(SearchResultsComp, SWT.NONE);
//					button[i][i].setBounds(452, m, 24, 21);
//					button[i][i].setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
//					button[i][i].setText("-");
//					//button[i][i].button
//					
//					button[i][i+1] = new Button(SearchResultsComp, SWT.NONE);
//					button[i][i+1].setBounds(508, m, 24, 23);
//					button[i][i+1].setText("+");
//					button[i][i+1].setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
//					m=m+22;
//				}
//				text[2][3].setText("Hello");
//				text[2][7].setText("25");
//			}});
//		
		
		
		
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
			}
		});
		
		btnClearAddInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Clear (Add Inventory)");
			}
		});
		
		btnXRmvInv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : X (Remove Inventory)");
				RmvInvComp.setVisible(false);
				Inv2ButtonComp.setEnabled(true);
			}
		});
		
		btnRmvInvSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Submit (Remove Inventory)");
			}
		});
		
		btnRmvInvClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Button : Clear (Remove Inventory)");
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
}