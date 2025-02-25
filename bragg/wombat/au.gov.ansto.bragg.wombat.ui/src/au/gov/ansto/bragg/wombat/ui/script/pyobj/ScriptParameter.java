/**
 * 
 */
package au.gov.ansto.bragg.wombat.ui.script.pyobj;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;



/**
 * @author nxi
 *
 */
public class ScriptParameter implements IPyObject{

	public enum PType{
		STRING,
		INT,
		FLOAT,
		BOOL,
		FILE,
		DEFAULT
	};
	
	public ScriptParameter() {
	}
	
	private PType type = PType.STRING;
	private Object value;
	private String name;
	private List<Object> options;
	private String command;
	private PropertyChangeSupport changeListener = new PropertyChangeSupport(this);
	
	private void firePropertyChange(String name, Object oldValue, Object newValue) {
		changeListener.firePropertyChange(name, oldValue, newValue);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeListener.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeListener.removePropertyChangeListener(listener);
	}

	public PType getType() {
		return type;
	}

	public void setType(PType type) {
		this.type = type;
	}

	public void setTypeName(String typeString) {
		try {
			type = PType.valueOf(typeString.toUpperCase());
		} catch (Exception e) {
			type = PType.DEFAULT;
		}
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		Object oldValue = this.value;
		switch (type) {
		case INT:
			this.value = Integer.valueOf(String.valueOf(value));
			break;
		case FLOAT:
			this.value = Float.valueOf(String.valueOf(value));
			break;
		case BOOL:
			this.value = Boolean.valueOf(String.valueOf(value));
			break;
		default:
			this.value = value;
			break;
		}
		if (oldValue != this.value) {
			firePropertyChange("value", oldValue, value);
		}
	}

	public List<Object> getOptions() {
		return options;
	}
	
	public void setOptions(List<Object> options) {
		List<Object> oldValue = this.options;
		this.options = options;
		firePropertyChange("options", oldValue, options);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	
}
