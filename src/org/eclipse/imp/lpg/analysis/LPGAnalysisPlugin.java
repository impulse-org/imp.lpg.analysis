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
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class LPGAnalysisPlugin extends PluginBase {
    public static final String kPluginID= "org.eclipse.imp.lpg.analysis";

    private static final String kLanguageID= "lpgAnalysis";

    /**
     * The unique instance of this plugin class
     */
    protected static LPGAnalysisPlugin sPlugin;

    protected static PreferencesService preferencesService = null;

    public static LPGAnalysisPlugin getInstance() {
        return sPlugin;
    }

    public LPGAnalysisPlugin() {
        super();
        sPlugin= this;
    }

    public String getLanguageID() {
        return kLanguageID;
    }

    public String getID() {
        return kPluginID;
    }

    public void start(BundleContext context) throws Exception {
        super.start(context);

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

    // Overriding method in IMPPluginBase because at that level we don't have
    // a preferences service to query dynamically, only a field set from this level
    // at the time of preference initialization
    public void maybeWriteInfoMsg(String msg) {
//        if (!preferencesService.getBooleanPreference(LPGPreferencesDialogConstants.P_EMITDIAGNOSTICS))
//            return;
        writeInfoMsg(msg);
    }
}
