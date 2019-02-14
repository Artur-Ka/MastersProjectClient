package ua.onclinic.gui.model;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;
import javax.swing.table.TableModel;

import ua.onclinic.ServerThread;
import ua.onclinic.enums.AccessLevel;
import ua.onclinic.gui.MainFrame;
import ua.onclinic.model.AbstractTable;
import ua.onclinic.model.table.EmployeeTableModel;
import ua.onclinic.model.table.PatientTableModel;
import ua.onclinic.model.table.UserTableModel;
import ua.onclinic.network.clientpackets.EmployeeListSave;
import ua.onclinic.network.clientpackets.PatientListSave;
import ua.onclinic.network.clientpackets.UserListSave;
import ua.onclinic.utils.DataLoader;

/**
 * 
 * @author Карпенко А. В.
 *
 * @date 7 трав. 2017 р.
 */
public class SouthPanel extends JPanel
{
	private static final long serialVersionUID = 3672045564578865429L;
	
	private final Timer _timer = new Timer(500, new Clock());
	
	private final ButtonPanel _buttons = new ButtonPanel();
	private final JPanel _middleButtonsPanel = new JPanel();
	
	private final JButton _frame = new JButton();
	private final JButton _data = new JButton();
	
	private final JLabel _date = new JLabel();
	private final JLabel _time = new JLabel();
	
	public SouthPanel()
	{
		setPreferredSize(new Dimension(getWidth(), 28));
		setLayout(new BorderLayout());
		
		_timer.start();
		
		final JPanel timePanel = new JPanel();
		timePanel.add(_date);
		timePanel.add(_time);
		
		final Dimension buttonSize = new Dimension(48,24);
		
		_frame.setIcon(DataLoader.FRAME_IMAGE);
		_frame.setPreferredSize(buttonSize);
		_frame.setToolTipText("Режим користувача");
		_frame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_buttons.setEnabled(false);
				MainFrame.getInstance().showUserWindow();
			}
		});
		
		_data.setIcon(DataLoader.DATA_IMAGE);
		_data.setPreferredSize(buttonSize);
		_data.setToolTipText("База даних");
		_data.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_buttons.setEnabled(true);
				MainFrame.getInstance().showAdminWindow();
			}
		});
		
		_middleButtonsPanel.add(_frame);
		_middleButtonsPanel.add(_data);
		
		_buttons.setEnabled(false);
		_buttons.setVisible(false);
		_middleButtonsPanel.setVisible(false);
		
		add(_buttons, BorderLayout.WEST);
		add(_middleButtonsPanel, BorderLayout.CENTER);
		add(timePanel, BorderLayout.EAST);
	}
	
	public void enableAdminButtons()
	{
		_buttons.setVisible(true);
		_middleButtonsPanel.setVisible(true);
	}
	
	public void disableAdminButtons()
	{
		_buttons.setVisible(false);
		_middleButtonsPanel.setVisible(false);
	}
	
	private class ButtonPanel extends JToolBar
	{
		private static final long serialVersionUID = 1855307235101536927L;
		
		private final JButton _plus = new JButton();
		private final JButton _minus = new JButton();
		private final JButton _trunc = new JButton();
		private final JButton _apply = new JButton();
		private final JButton _update = new JButton();
		
		private ButtonPanel()
		{
			setFloatable(false);
			
			final Dimension buttonSize = new Dimension(24,24);
			
			if (DataLoader.PLUS_IMAGE != null)
				_plus.setIcon(DataLoader.PLUS_IMAGE);
			_plus.setPreferredSize(buttonSize);
			_plus.setToolTipText("Додати рядок");
			_plus.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (MainFrame.getInstance().getAccessLevel() != AccessLevel.ADMIN)
						return;
					
					MainFrame.getInstance().getSelectedAdminTable().addRow();
				}
			});
			
			if (DataLoader.MINUS_IMAGE != null)
				_minus.setIcon(DataLoader.MINUS_IMAGE);
			_minus.setPreferredSize(buttonSize);
			_minus.setToolTipText("Видалити рядок");
			_minus.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (MainFrame.getInstance().getAccessLevel() != AccessLevel.ADMIN)
						return;
					
					MainFrame.getInstance().getSelectedAdminTable().removeRow();
				}
			});
			
			if (DataLoader.TRUNC_IMAGE != null)
				_trunc.setIcon(DataLoader.TRUNC_IMAGE);
			_trunc.setPreferredSize(buttonSize);
			_trunc.setToolTipText("Очистити таблицю");
			_trunc.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (MainFrame.getInstance().getAccessLevel() != AccessLevel.ADMIN)
						return;
					
					MainFrame.getInstance().getSelectedAdminTable().clearTable();
				}
			});
			
			if (DataLoader.APPLY_IMAGE != null)
				_apply.setIcon(DataLoader.APPLY_IMAGE);
			_apply.setPreferredSize(buttonSize);
			_apply.setToolTipText("Зберегти");
			_apply.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (MainFrame.getInstance().getAccessLevel() != AccessLevel.ADMIN)
						return;
					
					final AbstractTable<?,?> selectedTable = MainFrame.getInstance().getSelectedAdminTable();
					final TableModel selectedTableModel = selectedTable.getModel();
					
					// Проверка заполненности таблицы
					for (int i = 1; i < selectedTableModel.getRowCount(); i++)
					{
						for (int j = 1; j < selectedTableModel.getColumnCount(); j++)
						{
							if (selectedTableModel.getValueAt(i, j) == null)
							{
								Toolkit.getDefaultToolkit().beep();
								selectedTable.changeSelection(i, j, false, false);
								return;
							}
						}
					}
					
					switch (MainFrame.getInstance().getSelectedAdminCenterTab())
					{
						case MainFrame.USERS:
							if (!(selectedTableModel instanceof UserTableModel))
								return;
							
							ServerThread.sendPacket(new UserListSave(((UserTableModel) selectedTableModel).getInstances()));
							break;
						case MainFrame.EMPLOYEES:
							if (!(selectedTableModel instanceof EmployeeTableModel))
								return;
							
							ServerThread.sendPacket(new EmployeeListSave((((EmployeeTableModel) selectedTableModel).getInstances())));
							break;
						case MainFrame.PATIENTS:
							if (!(selectedTableModel instanceof PatientTableModel))
								return;
							
							ServerThread.sendPacket(new PatientListSave(((PatientTableModel) selectedTableModel).getInstances()));
							break;
					}
				}
			});
			
			if (DataLoader.UPDATE_IMAGE != null)
				_update.setIcon(DataLoader.UPDATE_IMAGE);
			_update.setPreferredSize(buttonSize);
			_update.setToolTipText("Оновити");
			_update.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (MainFrame.getInstance().getAccessLevel() != AccessLevel.ADMIN)
						return;
					
					MainFrame.getInstance().changeAdminWindowCondition();
				}
			});
			
			add(_plus);
			add(_minus);
			add(_trunc);
			add(_apply);
			add(_update);
		}
		
		@Override
		public void setEnabled(boolean enabled)
		{
			super.setEnabled(enabled);
			
			// Делаем активными/неактивными все кнопки в баре
			for (Component c : getComponents())
			{
				c.setEnabled(enabled);
			}
		}
	}
	
	private class Clock implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			final Date date = new Date();
			final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy   HH:mm:ss");
			_date.setText(sdf.format(date));
		}
	}
}