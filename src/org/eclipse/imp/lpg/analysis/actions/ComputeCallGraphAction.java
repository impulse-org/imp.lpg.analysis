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

package org.eclipse.imp.lpg.analysis.actions;

import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.lpg.analysis.LPGAnalysisPlugin;
import org.eclipse.imp.lpg.analysis.LPGAnalysisTypes;
import org.eclipse.imp.model.ICompilationUnit;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.pdb.analysis.AnalysisException;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.FactKey;
import org.eclipse.imp.pdb.facts.db.context.CompilationUnitContext;
import org.eclipse.imp.pdb.facts.impl.reference.ValueFactory;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.eclipse.ui.texteditor.TextEditorAction;

public class ComputeCallGraphAction extends TextEditorAction {
    public ComputeCallGraphAction(UniversalEditor editor) {
        super(AnalysisActionContributor.ResBundle, "computeCallGraph.", editor);
    }

    public void run() {
        UniversalEditor ue= (UniversalEditor) getTextEditor();
        IParseController parseController= ue.getParseController();
        ICompilationUnit unit= ModelFactory.open(parseController.getPath(), parseController.getProject());
        CompilationUnitContext unitContext= new CompilationUnitContext(unit);

        try {
            FactBase.getInstance().getRelation(new FactKey(LPGAnalysisTypes.LPGCallGraphType, unitContext));
        } catch (AnalysisException e) {
            FactBase.getInstance().defineFact(new FactKey(TypeFactory.getInstance().stringType(), unitContext), ValueFactory.getInstance().string(e.getMessage()));
            LPGAnalysisPlugin.getInstance().logException("Unable to produce call graph", e);
        }
    }
}
