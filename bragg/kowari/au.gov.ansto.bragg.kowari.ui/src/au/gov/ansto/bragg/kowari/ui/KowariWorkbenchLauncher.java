/*******************************************************************************
 * Copyright (c) 2007 Australian Nuclear Science and Technology Organisation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tony Lam (Bragg Institute) - initial API and implementation
 *******************************************************************************/

package au.gov.ansto.bragg.kowari.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.gumtree.ui.service.launcher.AbstractLauncher;
import org.gumtree.ui.service.launcher.LauncherException;
import org.gumtree.ui.service.multimonitor.IMultiMonitorManager;
import org.gumtree.ui.service.multimonitor.support.MultiMonitorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.gov.ansto.bragg.kowari.ui.internal.TCLRunnerPerspective;

public class KowariWorkbenchLauncher extends AbstractLauncher {

	
	private static final String ID_PERSPECTIVE_EXPERIMENT = "au.gov.ansto.bragg.kowari.ui.internal.TCLRunnerPerspective";
	private static final String ID_PERSPECTIVE_ANALYSIS = "au.gov.ansto.bragg.kowari.ui.internal.KowariAnalysisPerspective";
	private static final String ID_PERSPECTIVE_SICS = "au.gov.ansto.bragg.nbi.ui.SICSExperimentPerspective";
	
	private static Logger logger = LoggerFactory.getLogger(KowariWorkbenchLauncher.class);
	
//	private static boolean isCoolBarVisable = true;
	
	public KowariWorkbenchLauncher() {
	}

	public void launch() throws LauncherException {
		{			
			// TODO: move this logic to experiment UI manager service
			
			final IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			final IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
			for (IWorkbenchWindow window : windows){
				if (window != null && window != activeWorkbenchWindow) {
					window.close();
				}
			}
			if (activeWorkbenchWindow instanceof WorkbenchWindow) {
//				activeWorkbenchWindow.getActivePage().closeAllPerspectives(true, false);
				IWorkbenchPage[] pages = activeWorkbenchWindow.getPages();
				for (IWorkbenchPage page : pages) {
					try {
						if (!ID_PERSPECTIVE_EXPERIMENT.equals(page.getPerspective().getId())){
							activeWorkbenchWindow.getActivePage().closePerspective(page.getPerspective(), false, true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//			IMultiMonitorManager mmManager = ServiceUtils.getService(IMultiMonitorManager.class);
			IMultiMonitorManager mmManager = new MultiMonitorManager();
			// Prepare status in screen 1 (maximised)
//			mmManager.showPerspectiveOnOpenedWindow(ID_PERSPECTIVE_DEFAULT, 0, 0, true);
//			String configFile = System.getProperty(PROP_STATUS_DASHBOARD_CONFIG_FILE);
//			if (configFile != null) {
//				try {
//					IFileStore fileStore = EFS.getStore(new URI(configFile));
//					IDashboardConfig config = DashboardConfigUtils.loadDashboardConfig(fileStore.openInputStream(EFS.NONE, new NullProgressMonitor()));
//					DashboardUI.setStatusViewConfig(config);
//				} catch (Exception e) {
//					// Oops ... nothing I can do
//					logger.error("Cannot load dashboard config while setting to status monitor.", e);
//				}
//			}
			
			// Attempt to close intro
//			IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
//			PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);
			
//			InstrumentDashboardLauncher launcher = new InstrumentDashboardLauncher();
//			launcher.launch(0);
			
			// Open workflow in screen 2 (maximise only for multi system)
//			try {
//				Thread.currentThread().wait(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (PlatformUI.getWorkbench().getWorkbenchWindowCount() < 2) {
//				// open new window as editor buffer
//				mmManager.openWorkbenchWindow(ID_PERSPECTIVE_DEFAULT, 1, true);
//			}
////			// position it
//			mmManager.showPerspectiveOnOpenedWindow(ID_PERSPECTIVE_KAKADU, 1, 1, mmManager.isMultiMonitorSystem());

			String runMode = System.getProperty("gumtree.runtime.configEnv.mode");
			String perspectiveId = ID_PERSPECTIVE_EXPERIMENT;
			int windowId = 0;
			if (runMode != null && runMode.toLowerCase().equals("analysis")){
				perspectiveId = ID_PERSPECTIVE_ANALYSIS;
				windowId = 1;
			}
			if (windowId == 0) {
				mmManager.showPerspectiveOnOpenedWindow(ID_PERSPECTIVE_SICS, 0, windowId, false);
			}
			mmManager.showPerspectiveOnOpenedWindow(perspectiveId, 0, windowId, mmManager.isMultiMonitorSystem());
			activeWorkbenchWindow.getActivePage().setEditorAreaVisible(false);

			activeWorkbenchWindow.addPerspectiveListener(new IPerspectiveListener() {
				
				@Override
				public void perspectiveChanged(IWorkbenchPage page,
						IPerspectiveDescriptor perspective, String changeId) {
					if (perspective.getId().equals(ID_PERSPECTIVE_EXPERIMENT)) {
						activeWorkbenchWindow.getActivePage().setEditorAreaVisible(false);
					}
				}
				
				@Override
				public void perspectiveActivated(IWorkbenchPage page,
						IPerspectiveDescriptor perspective) {
					if (perspective.getId().equals(ID_PERSPECTIVE_EXPERIMENT)) {
						PlatformUI.getWorkbench().getThemeManager().setCurrentTheme(
								TCLRunnerPerspective.EXPERIMENT_PERSPECTIVE_THEME);
						activeWorkbenchWindow.getActivePage().setEditorAreaVisible(false);
					} else {
						PlatformUI.getWorkbench().getThemeManager().setCurrentTheme(
								TCLRunnerPerspective.DEFAULT_PERSPECTIVE_THEME);
						activeWorkbenchWindow.getActivePage().setEditorAreaVisible(true);
					}
				}
			});
			
			activeWorkbenchWindow.addPerspectiveListener(new PerspectiveAdapter() {
				@Override
				public void perspectiveActivated(final IWorkbenchPage page,
						final IPerspectiveDescriptor perspective) {
					super.perspectiveOpened(page, perspective);
					final PerspectiveAdapter adapter = this;
					Display.getDefault().asyncExec(new Runnable(){

						public void run() {
							try{
								if (perspective.getId().equals(ID_PERSPECTIVE_EXPERIMENT)){
									activeWorkbenchWindow.getActivePage().setEditorAreaVisible(false);
								} else {
									activeWorkbenchWindow.getActivePage().setEditorAreaVisible(true);
								}
								activeWorkbenchWindow.removePerspectiveListener(adapter);
							}catch (Exception e) {
							}
						}});
				}
				
			});
			
//			if (isCoolBarVisable) {
//				IWorkbench workbench = PlatformUI.getWorkbench();				
//				ActionFactory.IWorkbenchAction toggleToolbar = 
//					ActionFactory.TOGGLE_COOLBAR.create(
//							workbench.getActiveWorkbenchWindow());
//				toggleToolbar.run();
//				isCoolBarVisable = false;
//			}
			
//			PlatformUI.getWorkbench().getThemeManager().setCurrentTheme("au.gov.ansto.bragg.kowari.ui.theme");
		}
	}

}
