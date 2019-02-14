package ua.onclinic.gui.model.west;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import ua.onclinic.Config;
import ua.onclinic.model.Departament;
import ua.onclinic.model.instance.EmployeeInstance;

/**
 * 
 * @author A. Karpenko
 * @date 16 дек. 2018 г. 18:52:59
 */
public class WestTreeModel implements TreeModel
{
	private final Set<TreeModelListener> _listeners = new HashSet<>();
	private List<Departament> _departaments;
	
	
	@Override
	public Object getRoot()
	{
		return Config.CLINIC_NAME;
	}
	
	public List<Departament> getDepartmanets()
	{
		return _departaments;
	}
	
	public void setDepartaments(List<Departament> departaments)
	{
		_departaments = departaments;
		
		EmployeeInstance e1 = new EmployeeInstance();
		EmployeeInstance e2 = new EmployeeInstance();
		EmployeeInstance e3 = new EmployeeInstance();
		EmployeeInstance e4 = new EmployeeInstance();
		EmployeeInstance e5 = new EmployeeInstance();
		EmployeeInstance e6 = new EmployeeInstance();
		EmployeeInstance e7 = new EmployeeInstance();
		EmployeeInstance e8 = new EmployeeInstance();
		EmployeeInstance e9 = new EmployeeInstance();
		EmployeeInstance e10 = new EmployeeInstance();
		EmployeeInstance e11 = new EmployeeInstance();
		
		e1.setId(1111);
		e1.setName("Коршун Андрій Степанович");
		
		e2.setId(1112);
		e2.setName("Коротаєва Анна Михайлівна");
		
		e3.setId(1113);
		e3.setName("Запорожець Альона Сергіївна");
		
		e4.setId(1114);
		e4.setName("Сар Олександр Миколайович");
		
		e5.setId(1115);
		e5.setName("Москаленко Валерій Федорович");
		
		e6.setId(1116);
		e6.setName("Паршин Павло Анатолійович");
		
		e7.setId(1117);
		e7.setName("Максименко Олександр Станіславович");
		
		e8.setId(1118);
		e8.setName("Скоробагатько Артем Володимирович");
		
		e9.setId(1119);
		e9.setName("Плющ Дмитро Сергійович");
		
		e10.setId(1120);
		e10.setName("Москаленко Констянтин Вячеславович");
		
		e11.setId(1121);
		e11.setName("Шруб Надія Вячеславівна");
		
		_departaments.get(0).addEmployee(e11);
		_departaments.get(0).addEmployee(e1);
		
		_departaments.get(1).addEmployee(e5);
		_departaments.get(1).addEmployee(e7);
		_departaments.get(1).addEmployee(e8);
		
		_departaments.get(2).addEmployee(e6);
		_departaments.get(2).addEmployee(e4);
		_departaments.get(2).addEmployee(e9);
		
		_departaments.get(3).addEmployee(e2);
		_departaments.get(3).addEmployee(e3);
		_departaments.get(3).addEmployee(e10);
	}
	
	private final int getRootChild(Object parent)
    {
		if (_departaments == null)
			return 0;
		
		int idx = -1;
        for (int i = 0; i < _departaments.size(); i++)
        {
            if (_departaments.get(i) == parent)
            {
                idx = i;
                break;
            }
        }
        return idx;
    }
	
	@Override
	public Object getChild(Object parent, int index)
	{
		int idx = getRootChild(parent);
		if (parent == getRoot())
			return _departaments.get(index);
		else if (parent == _departaments.get(idx))
			return _departaments.get(idx).getEmployee(index); 
		
		return null;
	}
	
	@Override
	public int getChildCount(Object parent)
	{
		if (_departaments == null)
			return 0;
		
		int idx = getRootChild(parent);
        if (parent == getRoot())
            return _departaments.size();
        else
        	return _departaments.get(idx).getEmployees().size();
	}
	
	@Override
	public boolean isLeaf(Object node)
	{
		if (node instanceof EmployeeInstance)
			return true;
		
		return false;
	}
	
	@Override
	public void valueForPathChanged(TreePath path, Object newValue)
	{
		
	}
	
	@Override
	public int getIndexOfChild(Object parent, Object child)
	{
		return 0;
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener l)
	{
		_listeners.add(l);
	}
	
	@Override
	public void removeTreeModelListener(TreeModelListener l)
	{
		_listeners.remove(l);
	}
}