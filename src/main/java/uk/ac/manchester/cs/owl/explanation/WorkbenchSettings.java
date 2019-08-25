package uk.ac.manchester.cs.owl.explanation;

/**
 * Author: Matthew Horridge
 * Stanford University
 * Bio-Medical Informatics Research Group
 * Date: 20/03/2012
 * 
 * Edited in August 2019 by Cilliers Pretorius to allow for menu interactions with the more readable
 * explanations extension (https://github.com/Pietersielie/Explanation-Workbench-More-Readable-Extension)
 */
public class WorkbenchSettings {

    private JustificationType justificationType = JustificationType.REGULAR;
    
    private int limit = 2;

    private boolean findAll = true;
    
    private boolean useAnnotatedExplanations = true;
    private boolean useExpandedKeywords = true;

    public void setUseAnnotatedExplanations(boolean b) { useAnnotatedExplanations = b;}
    
    public void setUseExpandedKeywords(boolean b) { useExpandedKeywords = b;}
    
    public boolean getUseAnnotatedExplanations() { return useAnnotatedExplanations;}
    
    public boolean getUseExpandedKeywords() { return useExpandedKeywords;}

    public JustificationType getJustificationType() {
        return justificationType;
    }

    public void setJustificationType(JustificationType justificationType) {
        this.justificationType = justificationType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isFindAllExplanations() {
        return findAll;
    }

    public void setFindAllExplanations(boolean findAllExplanations) {
        findAll = findAllExplanations;
    }
}
