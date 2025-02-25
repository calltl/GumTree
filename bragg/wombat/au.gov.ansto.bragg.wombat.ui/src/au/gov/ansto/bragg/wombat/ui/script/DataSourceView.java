/**
 * 
 */
package au.gov.ansto.bragg.wombat.ui.script;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import au.gov.ansto.bragg.wombat.ui.script.parts.ScriptDataSourceViewer;

/**
 * @author nxi
 *
 */
public class DataSourceView extends ViewPart {

	private ScriptDataSourceViewer viewer;
	/**
	 * 
	 */
	public DataSourceView() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new ScriptDataSourceViewer(parent, SWT.None);
		GridDataFactory.fillDefaults().applyTo(viewer);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public ScriptDataSourceViewer getViewer() {
		return viewer;
	}
}
