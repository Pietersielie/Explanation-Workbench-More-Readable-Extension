package uk.ac.manchester.cs.owl.explanation;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.frame.AbstractOWLFrame;
import org.semanticweb.owl.explanation.api.Explanation;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * Author: Matthew Horridge
 * Stanford University
 * Bio-Medical Informatics Research Group
 * Date: 19/03/2012
 * 
 * Edited in August 2019 by Cilliers Pretorius to allow for menu interactions with the more readable
 * explanations extension (https://github.com/Pietersielie/Explanation-Workbench-More-Readable-Extension)
 */
public class JustificationFrame extends AbstractOWLFrame<Explanation<OWLAxiom>> {

    private WorkbenchManager workbenchManager;
    
    public JustificationFrame(OWLEditorKit editorKit, WorkbenchManager workbenchManager) {
        super(editorKit.getOWLModelManager().getOWLOntologyManager());
        addSection(new JustificationFrameSection(editorKit, this));
        this.workbenchManager = workbenchManager;
    }
    
    public WorkbenchManager getWorkbenchManager(){
        return workbenchManager;
    } 
}
