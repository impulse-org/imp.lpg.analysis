/*******************************************************************************
* Copyright (c) 2008 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.lpg.analysis;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.imp.utils.ExtensionFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class LPGAnalysisPlugin extends PluginBase {
    public static final String kPluginID= "org.eclipse.imp.lpg.analysis";

    /**
     * The unique instance of this plugin class
     */
    protected static LPGAnalysisPlugin sPlugin;

    protected static PreferencesService preferencesService = null;

    private static String kLanguageID;

    public static LPGAnalysisPlugin getInstance() {
        return sPlugin;
    }

    public LPGAnalysisPlugin() {
        super();
        sPlugin= this;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);

        kLanguageID= ExtensionFactory.retrieveLanguageIdFromPlugin(kPluginID);

        // Initialize the LPGPreferences fields with the preference store data.
        if (preferencesService == null) {
        	preferencesService = getPreferencesService();
        }
    }

    public static final IPath ICONS_PATH= new Path("icons/"); //$NON-NLS-1$

    protected void initializeImageRegistry(ImageRegistry reg) {
//        path= ICONS_PATH.append("grammarfile-error.jpg");//$NON-NLS-1$
//        imageDescriptor= createImageDescriptor(getInstance().getBundle(), path);
//        reg.put(ILPGResources.GRAMMAR_FILE_ERROR, imageDescriptor);
    }

    public static ImageDescriptor createImageDescriptor(Bundle bundle, IPath path) {
        URL url= FileLocator.find(bundle, path, null);
        if (url != null) {
            return ImageDescriptor.createFromURL(url);
        }
        return null;
    }

    public static String getLanguageID() {
        return kLanguageID;
    }

    public String getID() {
        return kPluginID;
    }
    
    public static PreferencesService getPreferencesService() {
    	if (preferencesService == null) {
        	preferencesService = new PreferencesService();
        	preferencesService.setLanguageName(kLanguageID);
        	
    		// To trigger the automatic invocation of the preferences initializer:
    		try {
    			new DefaultScope().getNode(kPluginID);
    		} catch (Exception e) {
    			// If this ever happens, it will probably be because the preferences
    			// and their initializer haven't been defined yet.  In that situation
    			// there's not really anything to do--you can't initialize preferences
    			// that don't exist.  So swallow the exception and continue ...
    		}
    	}
    	return preferencesService;
    }

    // Overwriting method in IMPPluginBase because at that level we don't have
    // a preferences service to query dynamically, only a field set from this level
    // at the time of preference initialization
    public void maybeWriteInfoMsg(String msg) {
//        if (!preferencesService.getBooleanPreference(LPGPreferencesDialogConstants.P_EMITDIAGNOSTICS))
//            return;
        writeInfoMsg(msg);
    }
}
