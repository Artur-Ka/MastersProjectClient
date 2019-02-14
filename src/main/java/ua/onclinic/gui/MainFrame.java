package ua.onclinic.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.onclinic.ServerThread;
import ua.onclinic.enums.AccessLevel;
import ua.onclinic.gui.model.EastBar;
import ua.onclinic.gui.model.MenuBar;
import ua.onclinic.gui.model.OptionPane;
import ua.onclinic.gui.model.SouthPanel;
import ua.onclinic.gui.model.central.CentralAdminWindow;
import ua.onclinic.gui.model.central.CentralWindow;
import ua.onclinic.gui.model.west.WestPanel;
import ua.onclinic.model.AbstractTable;
import ua.onclinic.network.clientpackets.EmployeeListRequest;
import ua.onclinic.network.clientpackets.PatientListRequest;
import ua.onclinic.network.clientpackets.UserListRequest;
import ua.onclinic.utils.DataLoader;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 15 квіт. 2017 р.
 */
public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 2108629664820165052L;

	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Вкладки в центральном окне
	public static final int USERS = 0x00;
	public static final int EMPLOYEES = 0x01;
	public static final int PATIENTS = 0x02;
	
	private final MenuBar _menuBar = new MenuBar();
	private final WestPanel _westPanel = new WestPanel();
	private final EastBar _eastBar = new EastBar();
	private final CentralWindow _centralWindow = new CentralWindow();
	private final SouthPanel _southPanel = new SouthPanel();
	
	private AccessLevel _accessLevel = AccessLevel.NONE;
	private boolean _isLocked = false;
	
	private MainFrame()
	{
		// Настройка главного окна
		//============================================================================
		setTitle("Onclinic client");
		
		setIconImage(DataLoader.ICON_IMAGE.getImage());
		setSize(SCREEN_SIZE);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// Установка слушателя закрытия окна
		addWindowListener(new CloseListener());
		//============================================================================
		
		// Нижнюю панель необходимо сделать шириной такой же, как окошко в центре,
		// а не на весь экран (весь главный фрейм)
		final JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		centralPanel.add(_centralWindow);
		centralPanel.add(_southPanel, BorderLayout.SOUTH);
		
		add(_menuBar, BorderLayout.NORTH);
		add(_westPanel, BorderLayout.WEST);
		add(_eastBar, BorderLayout.EAST);
		add(centralPanel, BorderLayout.CENTER);
	}
	
	public boolean isLocked()
	{
		return _isLocked;
	}
	
	public boolean isAuthorized()
	{
		return _accessLevel != AccessLevel.NONE;
	}
	
	public AccessLevel getAccessLevel()
	{
		return _accessLevel;
	}
	
	public void lock()
	{
		if (_isLocked)
			return;
		
		_isLocked = true;
		setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public void unlock()
	{
		if (!_isLocked)
			return;
		
		_isLocked = false;
		setEnabled(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void authorize(String login, AccessLevel accessLevel)
	{
		if (isAuthorized())
			return;
		
		_accessLevel = accessLevel;
		
		String completeLogin = login;
		
		// Есди уровень доступа больше "пользователя", отображаем возле логина
		// и включаем админ-панель
		if (accessLevel.ordinal() > AccessLevel.USER.ordinal())
		{
			completeLogin = completeLogin.concat(" (" + accessLevel.toString() + ")");
			_southPanel.enableAdminButtons();
		}
		
		_centralWindow.showUserWindow();
		
		_menuBar.login(completeLogin);
		_menuBar.hideAuthPanel();
		
		_westPanel.setEnabled(true);
	}
	
	public void logout()
	{
		if (!isAuthorized())
			return;
		
		_accessLevel = AccessLevel.NONE;
		
		_menuBar.logout();
		_centralWindow.hideAdminWindow();
		_centralWindow.hideUserWindow();
		_southPanel.disableAdminButtons();
		_westPanel.setEnabled(false);
	}
	
	public void showAdminWindow()
	{
		_centralWindow.hideUserWindow();
		_centralWindow.showAdminWindow();
		
		changeAdminWindowCondition();
	}
	
	public void showUserWindow()
	{
		_centralWindow.hideAdminWindow();
		_centralWindow.showUserWindow();
	}
	
	public void setAuthorizationInfo(String text)
	{
		_menuBar.setAuthInfo(text);
	}
	
	/**
	 * Возвращает индекс выбранной вкладки в центральном окне.<BR><BR>
	 * @return
	 */
	public int getSelectedAdminCenterTab()
	{
		if (_centralWindow.getActiveWindow() == CentralWindow.ADMIN_WINDOW)
			return _centralWindow.getAdminWindow().getSelectedIndex();
		
		return -1;
	}
	
	/**
	 * Возвращает активную таблицу из центрального окна.<BR><BR>
	 * @return
	 */
	public AbstractTable<?, ?> getSelectedAdminTable()
	{
		final int activeWindow = getSelectedAdminCenterTab();
		
		switch (activeWindow)
		{
			case USERS: return CentralAdminWindow.USER_TABLE;
			case EMPLOYEES: return CentralAdminWindow.EMPLOYEE_TABLE;
			case PATIENTS: return CentralAdminWindow.PATIENT_TABLE;
		}
		
		return null;
	}
	
	public void changeAdminWindowCondition()
	{
		final int activeTable = getSelectedAdminCenterTab();
		
		switch (activeTable)
		{
			case USERS:
				ServerThread.sendPacket(new UserListRequest());
				break;
			case EMPLOYEES:
				ServerThread.sendPacket(new EmployeeListRequest());
				break;
			case PATIENTS:
				ServerThread.sendPacket(new PatientListRequest());
				break;
		}
	}
	
	public static void exit()
	{
		final int n = OptionPane.showOptionDialog(MainFrame.getInstance());
		if (n == 0)
		{
			ServerThread.getInstance().disconnect();
			System.exit(0);
		}
	}
	
	private final class CloseListener implements WindowListener
	{
		@Override
		public void windowOpened(WindowEvent e)
		{}

		@Override
		public void windowClosing(WindowEvent e)
		{
			exit();
		}

		@Override
		public void windowClosed(WindowEvent e)
		{}

		@Override
		public void windowIconified(WindowEvent e)
		{}

		@Override
		public void windowDeiconified(WindowEvent e)
		{}

		@Override
		public void windowActivated(WindowEvent e)
		{}

		@Override
		public void windowDeactivated(WindowEvent e)
		{}
	}
	
	public static final MainFrame getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static final class SingletonHolder
	{
		private static final MainFrame _instance = new MainFrame();
	}
}