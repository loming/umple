/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.16.0.2388 modeling language!*/

package cruise.umple.compiler;
import java.io.*;
import java.util.*;
import cruise.umple.util.*;
import cruise.umple.compiler.exceptions.*;

// line 110 "../../../../src/Generator.ump"
// line 18 "../../../../src/Generator_CodeGvClassDiagram.ump"
public class GvClassDiagramGenerator implements CodeGenerator
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GvClassDiagramGenerator Attributes
  private UmpleModel model;
  private String output;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GvClassDiagramGenerator()
  {
    model = null;
    output = "";
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(UmpleModel aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setOutput(String aOutput)
  {
    boolean wasSet = false;
    output = aOutput;
    wasSet = true;
    return wasSet;
  }

  /**
   * Contains various aspects from an Umple file (.ump), such as classes, attributes, associations and methods.  Generated output is based
   * off of what's contained in here.
   */
  public UmpleModel getModel()
  {
    return model;
  }

  public String getOutput()
  {
    return output;
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 21 ../../../../src/Generator_CodeGvClassDiagram.ump
  public void generate()
  {
    StringBuilder code = new StringBuilder();
    StringBuilder generalizations = new StringBuilder();
    StringBuilder associations = new StringBuilder();
    String className;

    // Output basic gv file header
    code.append("// Code generated by Umple\n\n");
    code.append("digraph \""+model.getUmpleFile().getSimpleFileName()+"\" {\n");
    code.append("  size=\"80,10\"\n");
    code.append("  rankdir=\"BT\"\n");

    // Set of classes we are visiting
    // We always visit from the top of the hierarchy first
    // This set ensures we track what we have visited
    HashSet visitedClasses = new HashSet();

    // Iterate through each class. 
    for (UmpleClass uClass : model.getUmpleClasses())
    {
      visitClass(uClass, visitedClasses, code, associations);
    } // End iteration through classes
    
    terminateCode(code, generalizations, associations);
  }

  // Actually output the class contents  
  private void visitClass(UmpleClass uClass, Set visitedClasses, StringBuilder code, StringBuilder associations)
  {
    // Ensure we only visit once
    if(visitedClasses.contains(uClass)) {
      return;
    }
    
    // Ensure  output parents in the hierarchy first
    UmpleClass parent = uClass.getExtendsClass();
    if(parent != null) {
      visitClass(parent, visitedClasses, code, associations);
    }
    visitedClasses.add(uClass);
    
    // TODO if isAbstract() mark it as such
      
    String className = uClass.getName();
    code.append("\n  // Class: "+className+"\n");

    code.append("  "+className+" [shape=record, label=\"{"+className);
      
    // Iterate through attributes of the class
    boolean isFirst = true;
    for (Attribute uAttribute : uClass.getAttributes()) {
      if(isFirst) code.append("|"); // attribute block starter
      else code.append("\n"); // separator between attributes
      code.append(""+uAttribute.getName()+"\\ :\\ "+uAttribute.getType());
      isFirst = false;
    }

    // Terminate outputting the class
    code.append("}\"];\n");

    // Add any generalization now to parents
    // We know that parents have been output first
    UmpleClass parentClass = uClass.getExtendsClass();
    if(parentClass!= null) {
      code.append("  "+className+" -> "+parentClass.getName());
      code.append(" [arrowhead=\"empty\"; samehead=\"gen\"];\n\n");
    }

    // Add any interface implementations so they are output at the end
    for(UmpleInterface implementedInterface : uClass.getParentInterface()) {
      // TO DO
    }

    // Add any associations so they are output at the end
    // TO DO eliminate double counting of role names
    for(Association uAssoc : uClass.getAssociations()) {
      AssociationEnd leftEnd = uAssoc.getEnd(0);
      AssociationEnd rightEnd = uAssoc.getEnd(1);
      String arrows;

      // Only output them when visiting one class
      if(leftEnd.getClassName().equals(className)) {
        if(uAssoc.getIsLeftNavigable()) {
          if(uAssoc.getIsRightNavigable()) arrows = "dir=\"none\"";
          else arrows = "dir=\"back\", arrowtail=\"open\"";
        }
        else if(uAssoc.getIsRightNavigable()) {
          arrows = "dir=\"forward\", arrowhead=\"open\"";
        }
        else {
          arrows = "dir=\"none\"";
        }

        associations.append("  "+leftEnd.getClassName()+" -> "+
          rightEnd.getClassName()+" ["+arrows+
          ", taillabel=\""+leftEnd.toSimpleString()+
          " "+leftEnd.getDisplayRoleName()+"\""+
          ", headlabel=\""+rightEnd.toSimpleString()+
          " "+rightEnd.getDisplayRoleName()+"\""+
          "];\n");
      }
    }
  }
  
  private void terminateCode(StringBuilder code, StringBuilder generalizations, StringBuilder associations) {
    code.append("\n  // All generalizations\n");
    code.append(generalizations);

    code.append("\n  // All associations\n");
    code.append(associations);

    code.append("}\n");

    model.setCode(code.toString());
    writeModel();
  } 

  // Used to indent code
  private void appendSpaces(StringBuilder code, int numSpaces) {
    for(int i=0; i<numSpaces; i++) {
      code.append(" ");
    }
  }

  // Output the graphviz file to a file with the .gv suffix
  private void writeModel()
  {
    try
    {
      String path = model.getUmpleFile().getPath();
      File file = new File(path);
      file.mkdirs();
      String modelFilename = path + File.separator + model.getUmpleFile().getSimpleFileName() + "cd.gv";
      BufferedWriter bw = new BufferedWriter(new FileWriter(modelFilename));
      bw.write(model.getCode());
      bw.flush();
      bw.close();
    }
    catch (Exception e)
    {
      throw new UmpleCompilerException("There was a problem with generating GraphViz Class Diagram code." + e, e);
    }
  }
}