package uk.ac.manchester.cs.owl.explanation;

import org.protege.editor.core.ui.list.MListButton;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.editor.OWLObjectEditor;
import org.protege.editor.owl.ui.frame.AbstractOWLFrameSectionRow;
import org.protege.editor.owl.ui.frame.OWLFrameSection;
import org.semanticweb.owl.explanation.api.Explanation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLAnnotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.List;

/**
 * Author: Matthew Horridge
 * Stanford University
 * Bio-Medical Informatics Research Group
 * Date: 19/03/2012
 */
public class JustificationFrameSectionRow extends AbstractOWLFrameSectionRow<Explanation<OWLAxiom>, OWLAxiom, OWLAxiom>{

    private int depth;

    private final static String explanationAnnotationProperty = "exp:Explanation";

    private WorkbenchSettings workbenchSettings;

    public JustificationFrameSectionRow(OWLEditorKit owlEditorKit, OWLFrameSection<Explanation<OWLAxiom>, OWLAxiom, OWLAxiom> section, Explanation<OWLAxiom> rootObject, OWLAxiom axiom, int depth) {
        super(owlEditorKit, section, getOntologyForAxiom(owlEditorKit, axiom), rootObject, axiom);
        this.depth = depth;
        this.workbenchSettings = ((JustificationFrame) section.getFrame()).getWorkbenchManager().getWorkbenchSettings();
    }

    public int getDepth() {
        return depth;
    }

    private static OWLOntology getOntologyForAxiom(OWLEditorKit editorKit, OWLAxiom axiom) {
        return editorKit.getOWLModelManager().getActiveOntologies().stream()
                .filter(ont -> ont.containsAxiom(axiom))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getRendering() {
        String rendering =  super.getRendering().replaceAll("\\s", " ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++) {
            sb.append("        ");
        }
	if(workbenchSettings.getUseAnnotatedExplanations()){
            Set<OWLAnnotation> annotations = getAxiom().getAnnotations();
            if (!annotations.isEmpty()) {
                OWLModelManager protegeManager = getOWLModelManager();
                for (OWLAnnotation annotation : annotations){
                    if(protegeManager.getRendering(annotation.getProperty()).equals(explanationAnnotationProperty)){
                        sb.append(rendering);
                        sb.append(" (").append(protegeManager.getRendering(annotation.getValue())).append(")");
                        return sb.toString();
                    }
                }
            }
        }

        if(workbenchSettings.getUseExpandedKeywords()){
            sb.append(OWLManchesterSyntaxExpandKeywords.expandKeywords(rendering));
            return sb.toString();
        }

        sb.append(rendering);
	return sb.toString();
    }

    @Override
    public List<MListButton> getAdditionalButtons() {
        return Collections.emptyList();
    }

    @Override
    protected OWLObjectEditor<OWLAxiom> getObjectEditor() {
        return null;
    }

    @Override
    protected OWLAxiom createAxiom(OWLAxiom editedObject) {
        return null;
    }

    @Override
    public List<? extends OWLObject> getManipulatableObjects() {
        return Arrays.asList(getAxiom());
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public boolean isDeleteable() {
        return true;
    }

    @Override
    public boolean isInferred() {
        return false;
    }


}
