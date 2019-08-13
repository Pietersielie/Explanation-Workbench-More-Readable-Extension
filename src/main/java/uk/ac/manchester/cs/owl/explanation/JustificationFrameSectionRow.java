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
                    if(protegeManager.getRendering(annotation.getProperty())).equals(explanationAnnotationProperty){
                        sb.append(rendering);
                        sb.append(" (").append(protegeManager.getRendering(annotation.getValue())).append(")");
                        return sb.toString();
                    }
                }
            }
        }

        if(workbenchSettings.getUseExpandedKeywords()){
            sb.append(expandKeywords(rendering));
            return sb.toString();
        }

        sb.append(rendering);
	return sb.toString();
    }

    public String expandKeywords(String axiom) {
	String[] s = axiom.split(" ");
	String result = "";
        boolean disjoint = false;
        OUTER:
        for (int i = 0; i < s.length; ++i) {
            switch (s[i]) {
                case "SubClassOf":
                    s[i] = "is a subclass of";
                    break;
                case "SubPropertyOf:":
                    s[i] = "is a subproperty of";
                    break;
                case "Type":
                    s[i] = "is of the type";
                    break;
                case "some":
                    s[i] = "at least one";
                    break;
                case "EquivalentTo":
                    s[i] = "is equivalent to";
                    break;
                case "SameAs":
                    s[i] = "is the same as";
                    break;
                case "value":
                    s[i] = "with the value";
                    break;
                case "min":
                    s[i] = "no less than";
                    break;
                case "max":
                    s[i] = "no more than";
                    break;
                case "Domain":
                    result = result.substring(0, result.length() - (s[i - 1].length() + 1));
                    result += "The property " + s[i - 1] + " has the ";
                    break;
                case "DisjointClasses:":
                    disjoint = true;
                    break OUTER;
                case "Transitive:":
                    result += s[i + 1] + " is a transitive property.";
                    break OUTER;
                default:
                    break;
            }
            if(s[i] != s[s.length - 1])
                result += s[i] + " ";
            else
                result += s[i];
        }
        if(disjoint){
            disjoint = false;
            result += "The classes ";
            int j = 2;
            for(int i = 2; i < s.length - 2; ++i){
                result += s[i] + " ";
                ++j;
            }
            result += s[j].substring(0, s[j].length() - 1) + " and ";
            result += s[j + 1] + " are disjoint classes.";
        }
	return result;
    }

    /*public String colourString(String s, String colour){
	StringBuilder sb = new StringBuilder();
	for(Character c : s.toCharArray()){
	    sb.append("<font color=" + colour + ">");
	    sb.append(c);
	    sb.append("</font>");

	return sb.toString();
    }*/

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
