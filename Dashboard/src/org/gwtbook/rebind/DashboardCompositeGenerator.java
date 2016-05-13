package org.gwtbook.rebind;

import java.io.PrintWriter;

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.SelectionProperty;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class DashboardCompositeGenerator extends Generator {
	
	/*
	 * GWT requires us to overide the standard generate method to provide
	 * the functionality that we are after.
	 * 
	 * (non-Javadoc)
	 * @see com.google.gwt.core.ext.Generator#generate(com.google.gwt.core.ext.TreeLogger, com.google.gwt.core.ext.GeneratorContext, java.lang.String)
	 */
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
		
		try {
			// As well as getting details on the class, through the context we can get 
			// access to the properties that are set for this prermutation
			PropertyOracle properties = context.getPropertyOracle();
			// We'll just extract the value of the externalvisibility property, which could
			// either be intranet or internet.
			SelectionProperty version = properties.getSelectionProperty(logger, "externalvisibility");
			TypeOracle typeOracle = context.getTypeOracle();
			// Load the class/interface we are interested in through the typeOracle into the JClassType object 
			JClassType requestedClass = typeOracle.getType(typeName);
			// Get the package name of the class/interface
			String packageName = requestedClass.getPackage().getName();
			
			// Get the name of the class/interface
			String simpleClassName = requestedClass.getSimpleSourceName();
			
			// Our return class will have the same name as the original 
			// class with the word Proxy appended to it 
			String proxyClassName = simpleClassName + "Proxy" + version;
			// The new Qualified Class Name will indicate the new class will 
			// be in the same package as the original class with our new name
			String qualifiedProxyClassName = packageName + "." + proxyClassName;
			
			// Get the full qualified class name of the original class
			String qualifiedSourceName = requestedClass.getQualifiedSourceName();
			
			// Now we get a SourceWriter object for our new class
			SourceWriter writer = getSourceWriter(logger, context, packageName, proxyClassName, qualifiedSourceName);
						
			// And then write to the logger that we are starting the rewriting.
			logger.log(TreeLogger.INFO, "Starting rewriting using: "+this.getClass().getSimpleName()+" for "+version+" version", null);
			// If the SourceWriter object is null, then the new class that we are trying 
			// to create already exists, therefore we just need to return back 
			// the name of the new class
			if(writer==null){
				logger.log(TreeLogger.INFO, "Cancelled rewriting - class alredy exists", null);
				return qualifiedProxyClassName;
			} else {
				// The SourceWriter object was not null, therefore we have a 
				// reference to the new class that we will be building.
				
				// The first task is to override the old class' getName() method
				writeClassNameMethod(logger,simpleClassName, writer);
				
				// Then we override the old class' createOptionsMenu() method
				writeCreateOptionsMenu(logger,writer,simpleClassName, requestedClass, version);
								
				// If all went well, then we commit the changes to our new class
				writer.commit(logger);
				
				// and tell the logger that we are complete
				logger.log(TreeLogger.INFO, "Completed rewriting", null);
				
				// To finish, we return the name of the new class
				return qualifiedProxyClassName;
			}
		} catch(NotFoundException e) {
			// We cannot find the class that we are looking for, so we cannot rewrite it
			logger.log(TreeLogger.ERROR, "Class '" + typeName + "' Not Found", e);
			throw new UnableToCompleteException();
		} catch(BadPropertyValueException e) {
			// We cannot find the property value that we are looking for, so we cannot rewrite
			logger.log(TreeLogger.ERROR, "Could not find property value we looked for in the generator", e);
			throw new UnableToCompleteException();
		}
	}
	
	/**
	 * Returns a SourceWriter object for the new class to be built
	 * @param logger
	 * @param context
	 * @param packageName
	 * @param className
	 * @param superclassName
	 * @return null if the new class name already exists in the set of classes the GWT compiler will work on, 
	 * or a SourceWriter object representing the new class otherwise.
	 */
	protected SourceWriter getSourceWriter(TreeLogger logger, GeneratorContext context,String packageName, String className, String superclassName){
		// Get a new PrintWriter object from the current context
		PrintWriter printWriter = context.tryCreate(logger, packageName, className);
		// If this is null, then the class already exists in the context (either as an input provided by the programmer
		// or it has already been previously created by the Generator before).
		if (printWriter == null) return null;
		
		// All is well, so we now create our new class, which will look like
		// public packageName.className{}
		ClassSourceFileComposerFactory composerFactory =new ClassSourceFileComposerFactory(packageName, className);
		
		// Since we will be adding a menu and an alert box, we must import the necessary packages into the new class
		// It is highly likely that they are already, given our use of the DashboardComposite class, but we add them
		// again just to be on the safe side.
		// The class will now be:
		// import com.google.gwt.user.client.Command;
		// import com.google.gwt.user.client.Window;
		// public packageName.className{}
		composerFactory.addImport("com.google.gwt.user.client.Command");
		composerFactory.addImport("com.google.gwt.user.client.Window");
		composerFactory.addImport("com.google.gwt.user.client.ui.MenuItem");
		composerFactory.addImport("com.google.gwt.user.client.ui.HTML");
		composerFactory.addImport("org.gwtbook.client.ui.InfoDialogBox");
		composerFactory.addImport("org.gwtwidgets.client.ui.LightBox");
		
		// Set the parent class of our new class to be the input class to the Generator 
		// The class now becomes:
		// import com.google.gwt.user.client.Command;
		// import com.google.gwt.user.client.Window;
		// public packageName.className extends superclassName{}
		composerFactory.setSuperclass(superclassName);
		
		// Finally we create a SourceWriter object from our new class allowing us to write the necessary methods
		// and we return it to the calling method.
		return composerFactory.createSourceWriter(context, printWriter);
	}
	
	/**
	 * Writes the getName() method for out new class to the SourceWriter object.  The input DashboardComposite class
	 * will already have a getName() method which returns the text "GENERATOR FAILED"; this generator overrides that
	 * class so that we return the actual class name.
	 * @param logger
	 * @param className
	 * @param writer
	 */
	public void writeClassNameMethod(TreeLogger logger,String className, SourceWriter writer){
		logger.log(TreeLogger.INFO, "Writing getName() method for: "+className, null);
		// Use the println methods to "write" our new method definition to the SourceWriter object.
		writer.beginJavaDocComment();
		writer.println("Overides DashboardComposite getName() method");
		writer.endJavaDocComment();
		writer.println("public String getName()");
		writer.println("{");
			writer.indent();
			writer.println("return \"" + className + "\";");
		writer.outdent();
		writer.println("}");
	} 
	
	/**
	 * Writes the createOptionsMenu() method to the class represented by 
	 * the SourceWriter object.  Remember that this overrides the parents class
	 * method.  So, if one exists then the About menu item is tacked on to the 
	 * end of existing menu, otherwise it is the only menu item entry.
	 * The About functionality will create an About dialog which displays details of 
	 * the methods/fields in the class.  We use the value retrieved from the properties 
	 * to only print our public methods/fields if this is the internet version 
	 * (otherwise all details about methods/fields are added to the About dialog.
	 * @param logger
	 * @param writer
	 * @param name
	 * @param methods
	 * @param fields
	 */
	protected void writeCreateOptionsMenu(TreeLogger logger,SourceWriter writer, String name, JClassType requestedClass, SelectionProperty version){
		logger.log(TreeLogger.INFO, "Extending createOptionsMenu() method", null);
		// Get an array of all of the class/interface's methods
		JMethod[] methods = requestedClass.getMethods();
		// Get an array of all of the class/interface's fields
		JField[] fields = requestedClass.getFields();
		writer.println();
		writer.println("public void createOptionsMenu()");
		writer.println("{");
		writer.indent();
		writer.println("super.createOptionsMenu();");
		writer.println("optionsMenuBar.addStyleName(\"submenu\");");
		writer.println("optionsMenuBar.addItem(\"About\", new Command() {");
		writer.indent();
		writer.println("public void execute(){");
		writer.indent();
		writer.println("String aboutMessage = \"About the Dashboard "+name+" Widget ("+version.getCurrentValue()+" version)\\n\";");
		writer.println("aboutMessage +=\"Info Created by a GWT Generator\\n\\n\";");
		// Write out the number of fields in the class
		writer.println("aboutMessage +=\"Number Fields: "+fields.length+"\\n\";");
		// Loop around all the fields and add them to the message text 
		for(int loop=0;loop<fields.length;loop++){
			// Check if the field is public, or if the version we are generating is for the intranet
			if ((fields[loop].isPublic())||(version.equals("intranet")))
				// If the condition is true, then add details of the field to the About dialog box.
			   writer.println("aboutMessage+=\""+fields[loop].toString()+"\\n\";");
		}
		// Write out the number of methods in the class
		writer.println("aboutMessage +=\"\\nNumber Methods: "+methods.length+"\\n\";");
		// Loop around all the methods and add them to the message text
		for(int loop=0;loop<methods.length;loop++){
			// Check if the method is public, or if the version we are generating is for the intranet
			if ((methods[loop].isPublic())||(version.equals("intranet")))
				// If the condition is true, then add details of the method to the About dialog box.
			   writer.println("aboutMessage+=\""+methods[loop].toString()+"\\n\";");
		}
		// Display our alert box
		writer.println("Window.alert(aboutMessage);");
		writer.outdent();
		writer.println("}");
		writer.outdent();
		writer.println("});");
		writer.outdent();
		writer.println("optionsMenuBar.addItem(\"This App. Demonstrates...\", new Command() {");
		writer.indent();
		writer.println("public void execute(){");
		writer.indent();
		String[][] metaData = requestedClass.getMetaData("dashboard.description");
		String newAppDescription = "";
		if (metaData.length >= 1){
			int lastTagIndex = metaData.length -1;
			for(int loop=0; loop < metaData[lastTagIndex].length;loop++){
				newAppDescription += metaData[lastTagIndex][loop]+ " ";
			}
		} else {
			newAppDescription = "No Class Meta Data set for this Dashboard application";
		}
		writer.println("String applicationDescription = \""+newAppDescription+"\";");
		writer.println("InfoDialogBox splash = new InfoDialogBox();");
		writer.println("HTML splashHTML = new HTML(applicationDescription);");
		writer.println("splashHTML.setStyleName(\"componentInfo-Label\");");
		writer.println("splash.setWidget(splashHTML);");
		writer.println("splash.setText(\"Information on the "+name+" component\");");
		writer.println("LightBox startSplash = new LightBox(splash);");
		writer.println("startSplash.show();");
		writer.outdent();
		writer.println("}");
		writer.outdent();
		writer.println("});");
		writer.outdent();
		writer.println("}");
	}

}
