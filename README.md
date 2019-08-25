Explanation-Workbench-More-Readable-Extension
=====================

An extension of Matthew Horridge's Explanation Workbench for the Protégé Desktop Ontology Editor to have more readable explanations. In particular, it aims to provide ontology creators with a way to define an explanation or readable format for/of an axiom. When the axiom is then displayed upon an explanatory request, it displays the creator-defined explanation if it exists. If no annotation exists for that axiom, the keywords in the axiom (as proscribed by the Manchester Syntax) will be expanded to a more natural format.

Users can choose whether or not explanatory annotations should be displayed, as well as whether or not the keywords should be expanded. In order to have an annotation be displayed as an explanation, the annotation should be of the type 'exp:Explanation'. This format remains for now until such time that a specific format for explanation in OWL is declared and accepted as standard.

=====================

Installation:The source code can be downloaded from this github repository. It can then be compiled using the command mvn clean package. The compiled Explanation-Workbench-More-Readable-Extension-1.0.jar file (found in the target folder) should replace the explanation-workbench-3.0.jar file that is in the Protégé plugins folder. When Protégé is started afterwards, the plugin should be smoothly integrated.

Alternatively, a precompiled Explanation-Workbench-More-Readable-Extension-1.0.jar file can be downloaded by clicking [here](https://github.com/Pietersielie/Explanation-Workbench-More-Readable-Extension/Downloads/jars/Explanation-Workbench-More-Readable-Extension-1.0.jar). Similar to above, the new .jar file should replace the explanation-workbench-3.0.jar file that is in the Protégé plugins folder. When Protégé is started afterwards, the plugin should be smoothly integrated.