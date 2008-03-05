package org.eclipse.imp.lpg.analysis;

import org.eclipse.imp.pdb.facts.type.NamedType;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;

public class LPGAnalysisTypes {
    public static final TypeFactory tf= TypeFactory.getInstance();

    public static final NamedType LPGNonTerminalType= tf.namedType("org.lpg.nonTerminal", tf.stringType());
    public static final NamedType LPGCallGraphType= tf.namedType("org.lpg.callGraphType", tf.relTypeOf(LPGNonTerminalType, LPGNonTerminalType));
}
