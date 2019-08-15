/*
 * Copyright (C) 2019, University of Cape Town
 *
 * Modifications to the initial code base are copyright of their
 * respective authors, or their employers as appropriate.  Authorship
 * of the modifications may be determined from the ChangeLog placed at
 * the end of this file.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package uk.ac.manchester.cs.owl.explanation;

/**
 * Wrapper class for the expandKeywords(String axiom) function.
 * @author Cilliers Pretorius
 */
public class OWLManchesterSyntaxExpandKeywords {

    /**
     * Takes in an axiom and expands the keywords from the Manchester Syntax to
     * be more human friendly, particularly for those users who do not have a
     * very strong Description Logic background.
     * @param axiom The axiom whose keywords are to be expanded.
     * @return The axiom (in string form) with keywords expanded.
     */
    public static String expandKeywords(String axiom) {
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
                case "Range":
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
}
