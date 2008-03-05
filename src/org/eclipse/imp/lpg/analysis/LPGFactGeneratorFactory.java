package org.eclipse.imp.lpg.analysis;

import org.eclipse.imp.pdb.analysis.IFactGenerator;
import org.eclipse.imp.pdb.analysis.IFactGeneratorFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;

public class LPGFactGeneratorFactory implements IFactGeneratorFactory {
    public IFactGenerator create(Type type) {
        if (type.equals(LPGAnalysisTypes.LPGCallGraphType)) {
            return new LPGCallGraphGenerator();
        }
        throw new IllegalArgumentException("LPG Fact Generator: don't know how to produce fact " + type);
    }

    public String getName() {
        return "LPG Fact Generator Factory";
    }

    public void declareTypes(TypeFactory factory) {
        // Force static initializers on LPGAnalysisTypes to run
        Type dummy= LPGAnalysisTypes.LPGCallGraphType;
    }
}
